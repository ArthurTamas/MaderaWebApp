package com.dao;

import java.util.List;

import com.beans.Commande;

public interface CommandeDao {
    void creer( Commande commande ) throws DAOException;

    Commande trouver( long id ) throws DAOException;

    List<Commande> lister() throws DAOException;

    void supprimer( Commande commande ) throws DAOException;
}