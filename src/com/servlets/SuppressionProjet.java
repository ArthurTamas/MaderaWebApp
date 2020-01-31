package com.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Projet;
import com.dao.ProjetDao;
import com.dao.DAOException;
import com.dao.DAOFactory;

public class SuppressionProjet extends HttpServlet {
    public static final String CONF_DAO_FACTORY  = "daofactory";
    public static final String PARAM_ID_PROJET = "idProjet";
    public static final String SESSION_PROJET = "projets";

    public static final String VUE = "/listeProjets";

    private ProjetDao        commandeDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.commandeDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getProjetDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération du paramètre */
        String idProjet = getValeurParametre( request, PARAM_ID_PROJET );
        Long id = Long.parseLong( idProjet );

        /* Récupération de la Map des projets enregistrées en session */
        HttpSession session = request.getSession();
        Map<Long, Projet> projets = (HashMap<Long, Projet>) session.getAttribute( SESSION_PROJET );

        /* Si l'id de la projet et la Map des projets ne sont pas vides */
        if ( id != null && projets != null ) {
            try {
                /* Alors suppression de la projet de la BDD */
                commandeDao.supprimer( projets.get( id ) );
                /* Puis suppression de la projet de la Map */
                projets.remove( id );
            } catch ( DAOException e ) {
                e.printStackTrace();
            }
            /* Et remplacement de l'ancienne Map en session par la nouvelle */
            session.setAttribute( SESSION_PROJET, projets );
        }

        /* Redirection vers la fiche récapitulative */
        response.sendRedirect( request.getContextPath() + VUE );
    }

    /*
     * Méthode utilitaire qui retourne null si un paramètre est vide, et son
     * contenu sinon.
     */
    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}