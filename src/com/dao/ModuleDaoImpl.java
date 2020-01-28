package com.dao;

import com.beans.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static com.dao.DAOUtilitaire.fermeturesSilencieuses;

public class ModuleDaoImpl implements ModuleDao{

    private static final String SQL_SELECT        = "SELECT id_module, libelle_module, CODE_MODULE, PRIX_HT, PRIX_TTC, ID_GAMME FROM Module_Maison ORDER BY id_module";
    private static final String SQL_SELECT_PAR_ID = "SELECT id_module, nom, prenom, adresse, telephone, email FROM Module_Maison WHERE id_module = ?";
    private static final String SQL_INSERT        = "INSERT INTO Module_Maison (user_group, nom, prenom, adresse, telephone, email, password) VALUES ('module', ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Module_Maison WHERE id_module = ?";

    private DAOFactory daoFactory;

    ModuleDaoImpl(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void creer(Module module) throws Exception {

    }

    @Override
    public Module trouver(long id) throws DAOException {
        return null;
    }

    @Override
    public List<Module> lister() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Module> modules = new ArrayList<Module>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                modules.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return modules;
    }

    @Override
    public void supprimer(Module module) throws DAOException {

    }

    private Module map(ResultSet resultSet ) throws SQLException {
        Module module = new Module();

        module.setId( resultSet.getLong( "id_module" ) );
        module.setLibelle( resultSet.getString( "libelle_module" ) );
        module.setCode( resultSet.getString( "code_module" ) );
        module.setPrix_ht( resultSet.getString( "prix_ht" ) );
        GammeDao gammeDao= daoFactory.getGammeDao();
        module.setGamme( gammeDao.trouver( resultSet.getLong( "id_gamme" ) ) );
        return module;
    }

}
