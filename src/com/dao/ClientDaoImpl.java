package com.dao;

import static com.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.beans.Client;

public class ClientDaoImpl implements ClientDao {

    private static final String SQL_SELECT        = "SELECT id_utilisateur, nom, prenom, adresse, telephone, email FROM Utilisateur WHERE user_group = 'client' ORDER BY id_utilisateur";
    private static final String SQL_UPDATE        = "UPDATE Utilisateur SET nom = ?, prenom = ?, adresse = ?, telephone = ?, email = ?, password = ? WHERE id_utilisateur = ?";
    private static final String SQL_SELECTIDWITHROWID = "SELECT id_utilisateur, nom, prenom, adresse, telephone, email FROM Utilisateur WHERE rowid = ?";
    private static final String SQL_SELECT_PAR_ID = "SELECT id_utilisateur, nom, prenom, adresse, telephone, email FROM Utilisateur WHERE id_utilisateur = ?";
    private static final String SQL_INSERT        = "INSERT INTO Utilisateur (user_group, nom, prenom, adresse, telephone, email, password) VALUES ('client', ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Utilisateur WHERE id_utilisateur = ?";

    private DAOFactory daoFactory;

    ClientDaoImpl(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    @Override
    public Client trouver(long id ) throws DAOException {
        return trouver( SQL_SELECT_PAR_ID, id );
    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    @Override
    public void creer( Client client ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                    client.getNom(), client.getPrenom(),
                    client.getAdresse(), client.getTelephone(),
                    client.getEmail(), client.getPassword());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du client, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                String ROWID = valeursAutoGenerees.getString(1);
                Long id = trouverWithRowID(ROWID);
                client.setId(id);
            } else {
                throw new DAOException( "Échec de la création du client en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    private Long trouverWithRowID(String rowid) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECTIDWITHROWID, true, rowid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong(1);

            } else {
                throw new DAOException("Échec de la recuperation de l'ID client de la ligne insérée");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
        return id;
    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    @Override
    public List<Client> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Client> clients = new ArrayList<Client>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                clients.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return clients;
    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    @Override
    public void supprimer( Client client ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, client.getId() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la suppression du client, aucune ligne supprimée de la table." );
            } else {
                client.setId( null );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    /*
     * Méthode générique utilisée pour retourner un client depuis la base de
     * données, correspondant à la requête SQL donnée prenant en paramètres les
     * objets passés en argument.
     */
    private Client trouver(String sql, Object objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement un id) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
                client = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return client;
    }

    @Override
    public void maj( Client client ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, false,
                    client.getNom(), client.getPrenom(),
                    client.getAdresse(), client.getTelephone(),
                    client.getEmail(), client.getPassword(), client.getId());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la mise a jour du client." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des clients (un ResultSet) et
     * un bean Client.
     */
    private static Client map(ResultSet resultSet ) throws SQLException {
        Client client = new Client();
        client.setId( resultSet.getLong( "id_utilisateur" ) );
        client.setNom( resultSet.getString( "nom" ) );
        client.setPrenom( resultSet.getString( "prenom" ) );
        client.setAdresse( resultSet.getString( "adresse" ) );
        client.setTelephone( resultSet.getString( "telephone" ) );
        client.setEmail( resultSet.getString( "email" ) );
        //client.setPassword( resultSet.getString( "password" ) );
        //client.setImage( resultSet.getString( "image" ) );
        return client;
    }

}