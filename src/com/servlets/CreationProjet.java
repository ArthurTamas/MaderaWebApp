package com.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Client;
import com.beans.Projet;
import com.dao.*;
import com.forms.CreationProjetForm;

public class CreationProjet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String CHEMIN = "chemin";
    public static final String ATT_PROJET = "projet";
    public static final String ATT_FORM = "form";
    public static final String SESSION_CLIENTS = "clients";
    public static final String APPLICATION_CLIENTS = "initClients";
    public static final String SESSION_PROJETS = "projets";
    public static final String APPLICATION_COMMANDES = "initCommandes";

    public static final String VUE_SUCCES = "/WEB-INF/jsp/afficherProjet.jsp";
    public static final String VUE_FORM = "/WEB-INF/jsp/creerProjet.jsp";

    private ClientDao clientDao;
    private ProjetDao projetDao;
    private GammeDao gammeDao;
    private ModuleDao moduleDao;


    public void init() throws ServletException {
        /* Récupération d'une instance de nos DAO Client et Commande */
        this.clientDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getClientDao();
        //this.commandeDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommandeDao();
        this.projetDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProjetDao();
        this.gammeDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getGammeDao();
        this.moduleDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getModuleDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* À la réception d'une requête GET, simple affichage du formulaire */
        this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
         * dans le web.xml
         */
        String chemin = this.getServletConfig().getInitParameter(CHEMIN);

        /* Préparation de l'objet formulaire */
        CreationProjetForm form = new CreationProjetForm(clientDao, projetDao, gammeDao, moduleDao );

        /* Traitement de la requête et récupération du bean en résultant */
        Projet projet = form.creerProjet(request, chemin);

        /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute(ATT_PROJET, projet);
        request.setAttribute(ATT_FORM, form);

        /* Si aucune erreur */
        if (form.getErreurs().isEmpty()) {
            /* Alors récupération de la map des clients dans la session */
            HttpSession session = request.getSession();
            Map<Long, Client> clients = (HashMap<Long, Client>) session.getAttribute(SESSION_CLIENTS);
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if (clients == null) {
                clients = new HashMap<Long, Client>();
            }
            /* Puis ajout du client de la commande courante dans la map */
            clients.put(projet.getClient().getId(), projet.getClient());
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute(SESSION_CLIENTS, clients);

            /* Ensuite récupération de la map des commandes dans la session */
            Map<Long, Projet> projets = (HashMap<Long, Projet>) session.getAttribute(SESSION_PROJETS);
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if (projets == null) {
                projets = new HashMap<Long, Projet>();
            }
            /* Puis ajout de la commande courante dans la map */
            projets.put(projet.getId(), projet);
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute(SESSION_PROJETS, projets);

            /* Affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
        }
    }
}