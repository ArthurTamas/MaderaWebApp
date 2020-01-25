package com.dao;

import com.beans.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.dao.DAOUtilitaire.initialisationRequetePreparee;

public class UtilisateurDaoImpl implements UtilisateurDao {

    private static final String SQL_SELECT = "SELECT id, nom, prenom, adresse, telephone, email, image FROM Utilisateur ORDER BY id";
    private static final String SQL_SELECT_PAR_ID = "SELECT id, nom, prenom, adresse, telephone, email, image FROM Utilisateur WHERE id = ?";
    private static final String SQL_SELECT_PAR_EMAIL_AND_MDP = "SELECT id, nom, email FROM Utilisateur WHERE email = ? AND password = ?";

    private static final String SQL_INSERT = "INSERT INTO `Utilisateur` (nom, prenom, adresse, telephone, email, image) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Utilisateur WHERE id = ?";

    private DAOFactory daoFactory;

    UtilisateurDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void creer(Utilisateur client) throws DAOException {

    }

    @Override
    public Utilisateur trouver(long id) throws DAOException {
        return null;
    }

    @Override
    public Utilisateur trouver(String email, String password) throws DAOException {
        return trouver(SQL_SELECT_PAR_EMAIL_AND_MDP, email, password);
    }

    @Override
    public List<Utilisateur> lister() throws DAOException {
        return null;
    }

    @Override
    public void supprimer(Utilisateur client) throws DAOException {

    }

    private Utilisateur trouver(String sql, String email, String password) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement un id) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, email, password);

            resultSet = preparedStatement.executeQuery();

            /* Parcours de la ligne de données retournée dans le ResultSet */
            if (resultSet.next()) {

                utilisateur = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return utilisateur;
    }

    private static Utilisateur map(ResultSet resultSet) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(resultSet.getLong("id"));
        utilisateur.setNom(resultSet.getString("nom"));
        utilisateur.setEmail(resultSet.getString("email"));
        //utilisateur.setPrenom( resultSet.getString( "prenom" ) );
        //utilisateur.setAdresse( resultSet.getString( "adresse" ) );
        //utilisateur.setTelephone( resultSet.getString( "telephone" ) );
        //utilisateur.setImage( resultSet.getString( "image" ) );

        return utilisateur;
    }
}
