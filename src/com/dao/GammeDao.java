package com.dao;

import com.beans.Gamme;

import java.util.List;

public interface GammeDao {
    void creer( Gamme gamme ) throws Exception;

    Gamme trouver(long id ) throws DAOException;

    List<Gamme> lister() throws DAOException;

    void supprimer( Gamme gamme ) throws DAOException;
}
