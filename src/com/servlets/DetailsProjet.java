package com.servlets;

import com.beans.Client;
import com.beans.Projet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DetailsProjet extends HttpServlet {
    public static final String ATT_CLIENT = "projet";
    public static final String ATT_FORM   = "form";
    public static final String PARAM_ID_PROJET = "idProjet";
    public static final String SESSION_PROJETS = "projets";

    public static final String VUE              = "/WEB-INF/jsp/listerProjets.jsp";
    public static final String VUE_DETAILS      = "/detailsProjet";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des projets */
       String idProjet =  getValeurParametre( request, PARAM_ID_PROJET);
       Long id = Long.parseLong(idProjet);

        /* Récupération de la Map des projets enregistrés en session */
        HttpSession session = request.getSession();
        Map<Long, Projet> projets = (HashMap<Long, Projet>) session.getAttribute( SESSION_PROJETS );

        if ( id != null && projets != null) {
            request.setAttribute("projet", projets.get(id));
            response.sendRedirect( request.getContextPath() + VUE_DETAILS );
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
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
