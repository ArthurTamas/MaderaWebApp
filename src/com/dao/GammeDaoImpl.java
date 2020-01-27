package com.dao;

import com.beans.Gamme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.dao.DAOUtilitaire.fermeturesSilencieuses;

public class GammeDaoImpl implements GammeDao{

    private static final String SQL_SELECT        = "SELECT id_gamme, libelle_gamme, code FROM Gamme ORDER BY id_gamme";
    private static final String SQL_SELECT_PAR_ID = "SELECT id_gamme, nom, prenom, adresse, telephone, email FROM Gamme WHERE id_gamme = ?";
    private static final String SQL_INSERT        = "INSERT INTO Gamme (user_group, nom, prenom, adresse, telephone, email, password) VALUES ('gamme', ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Gamme WHERE id_gamme = ?";

    private DAOFactory daoFactory;

    GammeDaoImpl(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void creer(Gamme gammeDao) throws Exception {

    }

    @Override
    public Gamme trouver(long id) throws DAOException {
        return null;
    }

    @Override
    public List<Gamme> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Gamme> gammes = new ArrayList<Gamme>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                gammes.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return gammes;
    }

    @Override
    public void supprimer(Gamme gamme) throws DAOException {

    }

    private static Gamme map(ResultSet resultSet ) throws SQLException {
        Gamme gamme = new Gamme();
        gamme.setId( resultSet.getLong( "id_gamme" ) );
        gamme.setLibelle( resultSet.getString( "libelle_gamme" ) );
        gamme.setCode( resultSet.getString( "code" ) );
        return gamme;
    }
    
}
