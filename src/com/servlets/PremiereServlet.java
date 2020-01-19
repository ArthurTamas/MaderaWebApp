package com.servlets;

import com.beans.Coyote;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PremiereServlet extends HttpServlet {

    //Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(req, resp);
        String message = "Transmission de variables : OK !";
        request.setAttribute("test1", message);

        String paramAuteur = request.getParameter("auteur");
        String messageAuteur = "Transmission de variables : OK ! " + paramAuteur;
        request.setAttribute("test2", messageAuteur);

        /* Création du bean */
        Coyote premierBean = new Coyote();
        /* Initialisation de ses propriétés */
        premierBean.setNom("Coyote");
        premierBean.setPrenom("Wile E.");

        /* Création de la liste et insertion de quatre éléments */
        List<Integer> premiereListe = new ArrayList<>();
        premiereListe.add(27);
        premiereListe.add(12);
        premiereListe.add(138);
        premiereListe.add(6);

        /* On utilise ici la libraire Joda pour manipuler les dates, pour
         deux raisons :
         * - c'est tellement plus simple et limpide que de travailler avec
         les objets Date ou Calendar !
         * - c'est (probablement) un futur standard de l'API Java.
         */
        DateTime dt = new DateTime();
        Integer jourDuMois = dt.getDayOfMonth();

        /* Stockage du message et du bean dans l'objet request */
        request.setAttribute("test", message);
        request.setAttribute( "liste", premiereListe );
        request.setAttribute( "coyote", premierBean );
        request.setAttribute( "jour", jourDuMois );




        this.getServletContext().getRequestDispatcher("/WEB-INF/PremiereJSP.jsp").forward(request, response);
    }
}
