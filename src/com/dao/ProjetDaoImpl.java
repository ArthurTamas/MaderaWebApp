package com.dao;

import com.beans.Gamme;
import com.beans.Projet;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.dao.DAOUtilitaire.initialisationRequetePreparee;

public class ProjetDaoImpl implements ProjetDao {

    private static final String SQL_SELECT = "SELECT ID_PROJET, NUMERO_PROJET,AVANCEMENT,DATE_CREATION,MODALITE_PAIEMENT,DATE_DEBUT_PRESTATION,DATE_FIN_PRESTATION,ADRESSE,ID_MAISON,ID_CLIENT,ID_COMMERCIAL FROM Projet ORDER BY ID_PROJET";
    private static final String SQL_SELECT_PAR_ID = "SELECT ID_PROJET, NUMERO_PROJET,AVANCEMENT,DATE_CREATION,MODALITE_PAIEMENT,DATE_DEBUT_PRESTATION,DATE_FIN_PRESTATION,ADRESSE,ID_MAISON,ID_CLIENT,ID_COMMERCIAL FROM Projet WHERE ID_PROJET = ?";

    //private static final String SQL_INSERT = "INSERT INTO Projet (NUMERO_PROJET,AVANCEMENT,DATE_CREATION,MODALITE_PAIEMENT,DATE_DEBUT_PRESTATION,DATE_FIN_PRESTATION,ADRESSE,ID_MAISON,ID_CLIENT,ID_COMMERCIAL) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT = "INSERT INTO Projet (NUMERO_PROJET,AVANCEMENT,MODALITE_PAIEMENT,ADRESSE,ID_MAISON,ID_CLIENT,ID_COMMERCIAL, DATE_CREATION, DATE_DEBUT_PRESTATION, DATE_FIN_PRESTATION) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECTIDWITHROWID = "SELECT ID_PROJET FROM Projet WHERE ROWID = ?";
    private static final String SQL_SELECTIDWITNUMERO = "SELECT ID_PROJET FROM Projet WHERE NUMERO_PROJET = ?";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Projet WHERE id_projet = ?";

    private DAOFactory daoFactory;

    ProjetDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void creer(Projet projet) throws Exception {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();

            Integer id = trouverWithNumero(projet.getNumero_projet());
            if(id != null){
                throw new Exception("Échec de la création du projet, il existe un projet possedant le même numéro.");
            }
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true,
                    projet.getNumero_projet(),
                    projet.getAvancement(),
                    projet.getModalite_paiement(),
                    projet.getAdresse(),
                    null,
                    null,
                    null,
                    projet.getDate_creation().toLocalDate().toString(),
                    projet.getDate_debut_prestation().toLocalDate().toString(),
                    projet.getDate_fin_prestation().toLocalDate().toString()
            );
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création du projet, aucune ligne ajoutée dans la table.");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                String ROWID = valeursAutoGenerees.getString(1);
                Integer Id = trouverWithRowID(ROWID);
                projet.setId(Id);
            } else {
                throw new DAOException("Échec de la création du projet en base, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public Projet trouver(long id) throws DAOException {
        return null;
    }

    public Integer trouverWithRowID(String ROWID) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer id = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECTIDWITHROWID, true, ROWID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);

            } else {
                throw new DAOException("Échec de la recuperation de l'ID projet de la ligne insérée");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
        return id;
    }
    public Integer trouverWithNumero(String numero) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer id = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECTIDWITNUMERO, true, numero);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
        return id;
    }


    @Override
    public List<Projet> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Projet> projets = new ArrayList<Projet>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                projets.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return projets;
    }

    @Override
    public void supprimer(Projet projet) throws DAOException {

    }

    private Projet trouver(String sql, String email, String password) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Projet projet = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement un id) et exécution.
             */
            preparedStatement = initialisationRequetePreparee(connexion, sql, false, email, password);

            resultSet = preparedStatement.executeQuery();

            /* Parcours de la ligne de données retournée dans le ResultSet */
            if (resultSet.next()) {

                projet = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return projet;
    }

    private Projet map(ResultSet resultSet) throws SQLException {
        Projet projet = new Projet();
        projet.setId(resultSet.getInt("id_projet"));
        projet.setNumero_projet(resultSet.getString("numero_projet"));
        projet.setAvancement(resultSet.getString("avancement"));
        projet.setDate_creation(new DateTime(resultSet.getDate("date_creation")));
        projet.setModalite_paiement(resultSet.getString("modalite_paiement"));
        projet.setDate_debut_prestation(new DateTime(resultSet.getDate("date_debut_prestation")));
        projet.setDate_fin_prestation(new DateTime(resultSet.getDate("date_fin_prestation")));
        projet.setAdresse(resultSet.getString("adresse"));


        ClientDao clientDao = daoFactory.getClientDao();
        projet.setClient(clientDao.trouver(resultSet.getLong("id_client")));

        return projet;
    }
}
