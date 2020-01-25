package com.dao;

import com.beans.Utilisateur;

import java.util.List;

public interface UtilisateurDao {
    void creer( Utilisateur client ) throws DAOException;

    Utilisateur trouver( long id ) throws DAOException;
    
    Utilisateur trouver(String email, String password) throws DAOException;

    List<Utilisateur> lister() throws DAOException;

    void supprimer( Utilisateur client ) throws DAOException;
}
