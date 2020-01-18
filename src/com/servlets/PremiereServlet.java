package com.servlets;

import com.beans.Coyote;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PremiereServlet extends HttpServlet {

    //Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(req, resp);
        String message = "Transmission de variables : OK !";
        request.setAttribute( "test1", message );

        String paramAuteur = request.getParameter( "auteur" );
        String messageAuteur = "Transmission de variables : OK ! " + paramAuteur;
        request.setAttribute( "test2", messageAuteur );

        /* Création du bean */
        Coyote premierBean = new Coyote();
        /* Initialisation de ses propriétés */
        premierBean.setNom( "Coyote" );
        premierBean.setPrenom( "Wile E." );
        /* Stockage du message et du bean dans l'objet request */
        request.setAttribute( "test", message );
        request.setAttribute( "coyote", premierBean );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/PremiereJSP.jsp").forward( request, response );
    }
}
