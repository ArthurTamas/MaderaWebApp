package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListeProjets extends HttpServlet {
    public static final String ATT_CLIENT = "projet";
    public static final String ATT_FORM   = "form";

    public static final String VUE        = "/WEB-INF/jsp/listerProjets.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des projets */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }


}
