package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Utilisateur;
import com.forms.ConnexionForm;

public class Connexion extends HttpServlet {
    public static final String ATT_USER_GROUP = "userGroup";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE_CREERCLIENT = "/WEB-INF/jsp/creerClient.jsp";
    public static final String VUE_FAILLOGIN = "/connexion.jsp";
    public static final String VUE_ACCEUILCLIENT = "/WEB-INF/jsp/acceuil.jsp";
    public static final String VUE_LOGIN = "/connexion.jsp";
    public static String VUE;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur(request);

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */

        if (form.getErreurs().isEmpty() && utilisateur != null) {
            session.setAttribute(ATT_SESSION_USER, utilisateur);
            String userGroup = utilisateur.getUser_group();
            session.setAttribute(ATT_USER_GROUP, userGroup);
            if (userGroup.equals("admin") || userGroup.equals("commercial")) {
                VUE = VUE_CREERCLIENT;
            } else if (userGroup.equals("client")) {
                VUE = VUE_ACCEUILCLIENT;
            }
        } else {
            session.setAttribute(ATT_SESSION_USER, null);
            VUE = VUE_FAILLOGIN;
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}