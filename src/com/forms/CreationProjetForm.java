package com.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.beans.Client;
import com.beans.Gamme;
import com.beans.Module;
import com.beans.Projet;
import com.dao.*;
import org.joda.time.DateTime;


public final class CreationProjetForm {
    private static final String CHAMP_CHOIX_CLIENT = "choixNouveauClient";
    private static final String CHAMP_LISTE_CLIENTS = "listeClients";
    private static final String CHAMP_LISTE_GAMMES = "listeGammes";
    private static final String CHAMP_LISTE_MODULES = "listeModules";
    private static final String CHAMP_DATE = "dateProjet";
    private static final String CHAMP_NUMERO = "numeroProjet";
    private static final String CHAMP_MODE_PAIEMENT = "modePaiementProjet";
    private static final String CHAMP_DATE_DEBUT = "dateDebutProjet";
    private static final String CHAMP_DATE_FIN = "dateFinProjet";
    private static final String CHAMP_ADRESSE = "adresseProjet";
    private static final String CHAMP_AVANCEMENT = "avancementProjet";


    private static final String ANCIEN_CLIENT = "ancienClient";
    private static final String SESSION_CLIENTS = "clients";
    private static final String FORMAT_DATE = "dd/MM/yyyy";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private ClientDao clientDao;
    private ProjetDao projetDao;
    private GammeDao gammeDao;
    private ModuleDao moduleDao;


    public CreationProjetForm(ClientDao clientDao, ProjetDao projetDao) {
        this.clientDao = clientDao;
        this.projetDao = projetDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Projet creerProjet(HttpServletRequest request, String chemin) {
        Client client;
        /*
         * Si l'utilisateur choisit un client déjà existant, pas de validation à
         * effectuer
         */
        String choixNouveauClient = getValeurChamp(request, CHAMP_CHOIX_CLIENT);
        if (ANCIEN_CLIENT.equals(choixNouveauClient)) {
            /* Récupération de l'id du client choisi */
            String idAncienClient = getValeurChamp(request, CHAMP_LISTE_CLIENTS);
            Long id = null;
            try {
                id = Long.parseLong(idAncienClient);
            } catch (NumberFormatException e) {
                setErreur(CHAMP_CHOIX_CLIENT, "Client inconnu, merci d'utiliser le formulaire prévu à cet effet.");
                id = 0L;
            }
            /* Récupération de l'objet client correspondant dans la session */
            HttpSession session = request.getSession();
            client = ((Map<Long, Client>) session.getAttribute(SESSION_CLIENTS)).get(id);
        } else {
            /*
             * Sinon on garde l'ancien mode, pour la validation des champs.
             *
             * L'objet métier pour valider la création d'un client existe déjà,
             * il est donc déconseillé de dupliquer ici son contenu ! À la
             * place, il suffit de passer la requête courante à l'objet métier
             * existant et de récupérer l'objet Client créé.
             */
            CreationClientForm clientForm = new CreationClientForm(clientDao);
            client = clientForm.creerClient(request, chemin);

            /*
             * Et très important, il ne faut pas oublier de récupérer le contenu
             * de la map d'erreur créée par l'objet métier CreationClientForm
             * dans la map d'erreurs courante, actuellement vide.
             */
            erreurs = clientForm.getErreurs();
        }


        /*
         * Ensuite, il suffit de procéder normalement avec le reste des champs
         * spécifiques à une commande.
         */

        /* Récupération de la date dans un DateTime Joda. */
        DateTime dateTime = new DateTime();

        String numerProjet = getValeurChamp(request, CHAMP_NUMERO);
        String modePaiement = getValeurChamp(request, CHAMP_MODE_PAIEMENT);
        String dateDebut = getValeurChamp(request, CHAMP_DATE_DEBUT);
        String dateFin = getValeurChamp(request, CHAMP_DATE_FIN);
        String adresse = getValeurChamp(request, CHAMP_ADRESSE);
        String avancement = getValeurChamp(request, CHAMP_AVANCEMENT);
        String strGamme = getValeurChamp(request, CHAMP_LISTE_GAMMES);
        String strModule = getValeurChamp(request, CHAMP_LISTE_MODULES);

        Projet projet = new Projet();
        projet.setDate_creation(dateTime);

        try {
            traiterClient(client, projet);
            traiterNumeroProjet(numerProjet, projet);
            traiterModePaiement(modePaiement, projet);
            traiterAvancement(avancement, projet);
            traiterDateDebut(dateDebut, projet);
            traiterDateFin(dateFin, projet);
            traiterAdresse(adresse, projet);
            //traiterGamme(gamme, projet );

            if (strGamme != null && strModule != null) {
                Long idGamme = Long.parseLong(strGamme);
                Gamme gamme = gammeDao.trouver(idGamme);
                projet.setGamme(gamme);
                Long idModule = Long.parseLong(strModule);
                Module module = moduleDao.trouver(idModule);
                traiterModule(module, projet, gamme);
            } else {
                if (strGamme == null)
                    setErreur(CHAMP_LISTE_GAMMES, "Veuillez renseigner une gamme");
                if(strModule == null)
                    setErreur(CHAMP_LISTE_MODULES, "Veuillez renseigner un module");
            }

            if (erreurs.isEmpty()) {
                projetDao.creer(projet);
                resultat = "Succès de la création de la commande.";
            } else {
                resultat = "Échec de la création de la commande.";
            }
        } catch (DAOException | ParseException e) {
            setErreur("imprévu", "Erreur imprévue lors de la création.");
            resultat = "Échec de la création de la commande : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            setErreur("imprévu", "Échec de la création du projet, il existe un projet possedant le même numéro.");
            resultat = "Échec de la création du projet, il existe un projet possedant le même numéro.";
        }

        return projet;
    }

    private void traiterModule(Module module, Projet projet, Gamme gamme) {
        try {
            validationModule(module, gamme);
        } catch (FormValidationException e) {
            setErreur(CHAMP_LISTE_MODULES, e.getMessage());
        }
        projet.setModule(module);
    }

    private void validationModule(Module module, Gamme gamme) throws FormValidationException {
        if (module != null) {
            if (gamme.getId() != module.getGamme().getId()) {
                throw new FormValidationException("Le module selectionné ne fait pas partie de la gamme.");
            }
        } else {
            throw new FormValidationException("Merci de renseigner un module.");
        }
    }

    private void traiterAvancement(String avancement, Projet projet) {
        try {
            validationAvancement(avancement);
        } catch (FormValidationException e) {
            setErreur(CHAMP_AVANCEMENT, e.getMessage());
        }
        projet.setAvancement(avancement);
    }

    private void validationAvancement(String avancement) throws FormValidationException {
        if (avancement != null) {
            if (avancement.length() < 2) {
                throw new FormValidationException("Le champ \"Avancement\" doit contenir au moins 2 caractères.");
            }
        } else {
            throw new FormValidationException("Merci d'entrer une valeur pour le champ \"Avancement\".");
        }
    }

    private void traiterAdresse(String adresse, Projet projet) {
        try {
            validationAdresse(adresse);
        } catch (FormValidationException e) {
            setErreur(CHAMP_ADRESSE, e.getMessage());
        }
        projet.setAdresse(adresse);
    }

    private void validationAdresse(String adresse) throws FormValidationException {
        if (adresse != null) {
            if (adresse.length() < 2) {
                throw new FormValidationException("L'adresse doit contenir au moins 2 caractères.");
            }
        } else {
            throw new FormValidationException("Merci d'entrer une adresse.");
        }
    }

    private void traiterDateFin(String dateFin, Projet projet) throws ParseException {
        try {
            dateFin = validationDateFin(dateFin);
        } catch (FormValidationException e) {
            setErreur(CHAMP_DATE_FIN, e.getMessage());
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateTmp = formatter.parse(dateFin);
        DateTime dt = new DateTime(dateTmp);
        projet.setDate_fin_prestation(dt);
    }

    private String validationDateFin(String dateFin) throws FormValidationException {
        if (dateFin != null) {
            if (dateFin.length() != 10) {
                throw new FormValidationException("La date de fin doit être au format jj-mm-aaaa.");
            }
            dateFin = dateFin.replace("-", "/");
        } else {
            throw new FormValidationException("Merci d'entrer une date de fin.");
        }
        return dateFin;
    }

    private void traiterDateDebut(String dateDebut, Projet projet) throws ParseException {
        try {
            dateDebut = validationDateDebut(dateDebut);
        } catch (FormValidationException e) {
            setErreur(CHAMP_DATE_DEBUT, e.getMessage());
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateTmp = formatter.parse(dateDebut);
        DateTime dt = new DateTime(dateTmp);
        projet.setDate_debut_prestation(dt);
    }

    private String validationDateDebut(String dateDebut) throws FormValidationException {
        if (dateDebut != null) {
            if (dateDebut.length() != 10) {
                throw new FormValidationException("La date de début doit etre au format jj/mm/aaaa.");
            }
            dateDebut = dateDebut.replace("-", "/");
        } else {
            throw new FormValidationException("Merci d'entrer une date de début.");
        }
        return dateDebut;
    }

    private void traiterModePaiement(String modePaiement, Projet projet) {
        try {
            validationModePaiement(modePaiement);
        } catch (FormValidationException e) {
            setErreur(CHAMP_MODE_PAIEMENT, e.getMessage());
        }
        projet.setModalite_paiement(modePaiement);
    }

    private void validationModePaiement(String modePaiement) throws FormValidationException {
        if (modePaiement != null) {
            if (modePaiement.length() < 2) {
                throw new FormValidationException("Le mode de paiement doit contenir au moins 2 caractères.");
            }
        } else {
            throw new FormValidationException("Merci d'entrer un mode de paiement.");
        }
    }

    private void traiterNumeroProjet(String numeroProjet, Projet projet) {
        try {
            validationNumero(numeroProjet);
        } catch (FormValidationException e) {
            setErreur(CHAMP_NUMERO, e.getMessage());
        }
        projet.setNumero_projet(numeroProjet);
    }

    private void validationNumero(String numeroProjet) throws FormValidationException {
        if (numeroProjet == null) {
            throw new FormValidationException("Merci d'entrer un numéro de projet.");
        }
    }

    private void traiterClient(Client client, Projet projet) {
        if (client == null) {
            setErreur(CHAMP_CHOIX_CLIENT, "Client inconnu, merci d'utiliser le formulaire prévu à cet effet.");
        }
        projet.setClient(client);
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}