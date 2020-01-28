package com.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.beans.Client;
import com.beans.Gamme;
import com.beans.Module;
import com.beans.Projet;
import com.dao.*;

public class PrechargementFilter implements Filter {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_SESSION_CLIENTS = "clients";
    public static final String ATT_SESSION_COMMANDES = "commandes";
    public static final String ATT_SESSION_GAMMES = "gammes";
    public static final String ATT_SESSION_PROJETS = "projets";
    public static final String ATT_SESSION_MODULES = "modules";


    private ClientDao clientDao;
    private GammeDao gammeDao;
    private ProjetDao projetDao;
    private ModuleDao moduleDao;


    public void init(FilterConfig config) throws ServletException {
        /* Récupération d'une instance de nos DAO Client et Commande */
        this.clientDao = ((DAOFactory) config.getServletContext().getAttribute(CONF_DAO_FACTORY))
                .getClientDao();
        this.gammeDao = ((DAOFactory) config.getServletContext().getAttribute(CONF_DAO_FACTORY))
                .getGammeDao();
        this.projetDao = ((DAOFactory) config.getServletContext().getAttribute(CONF_DAO_FACTORY))
                .getProjetDao();
        this.moduleDao = ((DAOFactory) config.getServletContext().getAttribute(CONF_DAO_FACTORY))
                .getModuleDao();
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        /* Cast de l'objet request */
        HttpServletRequest request = (HttpServletRequest) req;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /*
         * Si la map des clients n'existe pas en session, alors l'utilisateur se
         * connecte pour la première fois et nous devons précharger en session
         * les infos contenues dans la BDD.
         */
        if (session.getAttribute(ATT_SESSION_CLIENTS) == null) {
            /*
             * Récupération de la liste des clients existants, et enregistrement
             * en session
             */
            List<Client> listeClients = clientDao.lister();
            Map<Long, Client> mapClients = new HashMap<Long, Client>();
            for (Client client : listeClients) {
                mapClients.put(client.getId(), client);
            }
            session.setAttribute(ATT_SESSION_CLIENTS, mapClients);
        }

        /*
         * De même pour la map des commandes
         */
        if (session.getAttribute(ATT_SESSION_PROJETS) == null) {
            /*
             * Récupération de la liste des projets existants, et
             * enregistrement en session
             */
            List<Projet> listeProjets = projetDao.lister();
            Map<Long, Projet> mapProjets = new HashMap<Long, Projet>();
            for (Projet projet : listeProjets) {
                mapProjets.put((long) projet.getId(), projet);
            }
            session.setAttribute(ATT_SESSION_PROJETS, mapProjets);
        }

        if (session.getAttribute(ATT_SESSION_GAMMES) == null) {
            /*
             * Récupération de la liste des clients existants, et enregistrement
             * en session
             */
            List<Gamme> listGammes = gammeDao.lister();
            Map<Long, Gamme> mapGammes = new HashMap<Long, Gamme>();
            for (Gamme gamme : listGammes) {
                mapGammes.put(gamme.getId(), gamme);
            }
            session.setAttribute(ATT_SESSION_GAMMES, mapGammes);
        }

        if (session.getAttribute(ATT_SESSION_MODULES) == null) {
            /*
             * Récupération de la liste des clients existants, et enregistrement
             * en session
             */
            List<Module> listModules = moduleDao.lister();
            Map<Long, Module> mapModules = new HashMap<Long, Module>();
            for (Module module : listModules) {
                mapModules.put(module.getId(), module);
            }
            session.setAttribute(ATT_SESSION_MODULES, mapModules);
        }

        /* Pour terminer, poursuite de la requête en cours */
        chain.doFilter(request, res);


    }

    public void destroy() {
    }
}