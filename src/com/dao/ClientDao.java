package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.beans.Client;

public interface ClientDao {
    void creer( Client client ) throws DAOException;

    Client trouver(long id ) throws DAOException;

    List<Client> lister() throws DAOException;

    void supprimer( Client client ) throws DAOException, SQLException;

    void maj(Client client) throws DAOException;
}