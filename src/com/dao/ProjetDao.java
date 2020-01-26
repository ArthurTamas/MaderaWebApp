package com.dao;

import com.beans.Projet;

import java.util.List;

public interface ProjetDao {
    void creer( Projet projet ) throws Exception;

    Projet trouver(long id ) throws DAOException;

    List<Projet> lister() throws DAOException;

    void supprimer( Projet projet ) throws DAOException;
}
