package com.servlets;

import com.beans.Client;
import com.beans.Devis;
import com.beans.Module;
import com.beans.Projet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public static final String VUE_DETAILS      = "/WEB-INF/jsp/detailsProjet.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, affichage de la liste des projets */
       String idProjet = request.getParameter(PARAM_ID_PROJET);
       Long id = Long.parseLong(idProjet);

        /* Récupération de la Map des projets enregistrés en session */
        HttpSession session = request.getSession();
        Map<Long, Projet> projets = (HashMap<Long, Projet>) session.getAttribute( SESSION_PROJETS );

        if ( id != null && projets != null) {

            Projet projet = projets.get(id);

            Devis devis = new Devis();
            devis.setStatus("En attente d'acceptation");
            devis.setNumeroDevis(projet.getNumero_projet() + 'D');


            Module module = projet.getModule();
            List<List> listLigne = new ArrayList<List>();

            String mainOeuvre = "Main d\'oeuvre";
            String prixMainOeuvre = "20000";
            String traitementTerrain = "Traitement du terrain";
            String prixTraitementTerrain = "10000";
            String fraisVehicule = "Frais de vehicule de chantier";
            String prixFraisVehicule = "3000";

            List<String> tabMainOeuvre = new ArrayList<String>();
            tabMainOeuvre.add(mainOeuvre);
            tabMainOeuvre.add(prixMainOeuvre);

            List<String> tabTraitementTerrain = new ArrayList<String>();
            tabTraitementTerrain.add(traitementTerrain);
            tabTraitementTerrain.add(prixTraitementTerrain);

            List<String> tabVehicule = new ArrayList<String>();
            tabVehicule.add(fraisVehicule);
            tabVehicule.add(prixFraisVehicule);

            Integer prixHtModule = 0;
            List<String> ligneModule = new ArrayList<>();
            if(module != null) {
                ligneModule.add(module.getLibelle());
                ligneModule.add(module.getPrix_ht());
                prixHtModule = Integer.parseInt(module.getPrix_ht());
            }
            List<String> tabTVA = new ArrayList<String>();
            String TVA = "TVA";
            String prixTVA = "20%";
            tabTVA.add(TVA);
            tabTVA.add(prixTVA);

            listLigne.add(tabMainOeuvre);
            listLigne.add(tabTraitementTerrain);
            listLigne.add(tabVehicule);
            if(module != null) {
                listLigne.add(ligneModule);
            }
            listLigne.add(tabTVA);

            devis.setLigneDevis(listLigne);

            Integer prixHTDevis = prixHtModule + 33000;
            devis.setPrixHT(prixHTDevis.toString());
            Double prixTTC = prixHTDevis * 1.20;
            devis.setPrixTTC(prixTTC.toString());
            projet.setDevis(devis);

            request.setAttribute("projet", projet);
            //response.sendRedirect( request.getContextPath() + VUE_DETAILS );
            this.getServletContext().getRequestDispatcher(VUE_DETAILS).forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}
