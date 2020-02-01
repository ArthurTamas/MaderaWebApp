package com.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.beans.Client;
import com.dao.ClientDao;
import com.dao.DAOException;

public final class CreationClientForm {
    private static final String CHAMP_NOM = "nomClient";
    private static final String CHAMP_PRENOM = "prenomClient";
    private static final String CHAMP_ADRESSE = "adresseClient";
    private static final String CHAMP_TELEPHONE = "telephoneClient";
    private static final String CHAMP_EMAIL = "emailClient";
    private static final String CHAMP_PASSWORD = "passwordClient";
    //private static final String CHAMP_IMAGE     = "imageClient";

    private static final int TAILLE_TAMPON = 10240;                        // 10ko

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private ClientDao clientDao;

    public CreationClientForm(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Client creerClient(HttpServletRequest request, String chemin) {
        String nom = getValeurChamp(request, CHAMP_NOM);
        String prenom = getValeurChamp(request, CHAMP_PRENOM);
        String adresse = getValeurChamp(request, CHAMP_ADRESSE);
        String telephone = getValeurChamp(request, CHAMP_TELEPHONE);
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String password = getValeurChamp(request, CHAMP_PASSWORD);

        Client client = new Client();

        traiterNom(nom, client);
        traiterPrenom(prenom, client);
        traiterAdresse(adresse, client);
        traiterTelephone(telephone, client);
        traiterEmail(email, client);
        traiterPassword(password, client);
        //traiterImage( client, request, chemin );

        try {
            if (erreurs.isEmpty()) {
                clientDao.creer(client);
                resultat = "Succès de la création du client.";
            } else {
                resultat = "Échec de la création du client.";
            }
        } catch (DAOException e) {
            setErreur("imprévu", "Erreur imprévue lors de la création.");
            resultat = "Échec de la création du client : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return client;
    }

    public Client modifierClient(HttpServletRequest request, String chemin,Client client) {
        String nom = getValeurChamp(request, CHAMP_NOM);
        String prenom = getValeurChamp(request, CHAMP_PRENOM);
        String adresse = getValeurChamp(request, CHAMP_ADRESSE);
        String telephone = getValeurChamp(request, CHAMP_TELEPHONE);
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String password = getValeurChamp(request, CHAMP_PASSWORD);

        traiterNom(nom, client);
        traiterPrenom(prenom, client);
        traiterAdresse(adresse, client);
        traiterTelephone(telephone, client);
        traiterEmail(email, client);
        traiterPassword(password, client);
        //traiterImage( client, request, chemin );

        try {
            if (erreurs.isEmpty()) {
                clientDao.maj(client);
                resultat = "Succès de la modification du client.";
            } else {
                resultat = "Échec de la modification du client.";
            }
        } catch (DAOException e) {
            setErreur("imprévu", "Erreur imprévue lors de la modification.");
            resultat = "Échec de la modificatiion du client : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return client;
    }

    private void traiterNom(String nom, Client client) {
        try {
            validationNom(nom);
        } catch (FormValidationException e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        client.setNom(nom);
    }

    private void traiterPrenom(String prenom, Client client) {
        try {
            validationPrenom(prenom);
        } catch (FormValidationException e) {
            setErreur(CHAMP_PRENOM, e.getMessage());
        }
        client.setPrenom(prenom);
    }

    private void traiterAdresse(String adresse, Client client) {
        try {
            validationAdresse(adresse);
        } catch (FormValidationException e) {
            setErreur(CHAMP_ADRESSE, e.getMessage());
        }
        client.setAdresse(adresse);
    }

    private void traiterTelephone(String telephone, Client client) {
        try {
            validationTelephone(telephone);
        } catch (FormValidationException e) {
            setErreur(CHAMP_TELEPHONE, e.getMessage());
        }
        client.setTelephone(telephone);
    }

    private void traiterEmail(String email, Client client) {
        try {
            validationEmail(email);
        } catch (FormValidationException e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        client.setEmail(email);
    }
    private void traiterPassword(String password, Client client) {
        try {
            validationPassword(password);
        } catch (FormValidationException e) {
            setErreur(CHAMP_PASSWORD, e.getMessage());
        }
        client.setPassword(password);
    }

    private void validationNom(String nom) throws FormValidationException {
        if (nom != null) {
            if (nom.length() < 2) {
                throw new FormValidationException("Le nom d'utilisateur doit contenir au moins 2 caractères.");
            }
        } else {
            throw new FormValidationException("Merci d'entrer un nom d'utilisateur.");
        }
    }

    private void validationPrenom(String prenom) throws FormValidationException {
        if (prenom != null && prenom.length() < 2) {
            throw new FormValidationException("Le prénom d'utilisateur doit contenir au moins 2 caractères.");
        }
    }

    private void validationPassword(String password) throws FormValidationException {
        if (password != null && password.length() < 2) {
            throw new FormValidationException("Le mot de passe doit contenir au moins 2 caractères.");
        }
    }

    private void validationAdresse(String adresse) throws FormValidationException {
        if (adresse != null) {
            if (adresse.length() < 10) {
                throw new FormValidationException("L'adresse doit contenir au moins 10 caractères.");
            }
        } else {
            throw new FormValidationException("Merci d'entrer une adresse.");
        }
    }

    private void validationTelephone(String telephone) throws FormValidationException {
        if (telephone != null) {
            if (!telephone.matches("^\\d+$")) {
                throw new FormValidationException("Le numéro de téléphone doit uniquement contenir des chiffres.");
            } else if (telephone.length() < 4) {
                throw new FormValidationException("Le numéro de téléphone doit contenir au moins 4 chiffres.");
            }
        } else {
            throw new FormValidationException("Merci d'entrer un numéro de téléphone.");
        }
    }

    private void validationEmail(String email) throws FormValidationException {
        if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new FormValidationException("Merci de saisir une adresse mail valide.");
        }
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