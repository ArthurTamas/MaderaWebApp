package com.servlets;

import com.beans.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DetailsClient extends HttpServlet {
    public static final String PARAM_ID_CLIENT = "idClient";
    public static final String SESSION_CLIENTS = "clients";

    public static final String VUE              = "/WEB-INF/jsp/listerClients.jsp";
    public static final String VUE_DETAILS      = "/WEB-INF/jsp/detailsClient.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        String idClient = request.getParameter(PARAM_ID_CLIENT);
        Long id = Long.parseLong(idClient);

        /* Récupération de la Map des clients enregistrés en session */
        HttpSession session = request.getSession();
        Map<Long, Client> clients = (HashMap<Long, Client>) session.getAttribute( SESSION_CLIENTS );

        if ( id != null && clients != null) {

            request.setAttribute("client", clients.get(id));
            //response.sendRedirect( request.getContextPath() + VUE_DETAILS );
            this.getServletContext().getRequestDispatcher(VUE_DETAILS).forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}
