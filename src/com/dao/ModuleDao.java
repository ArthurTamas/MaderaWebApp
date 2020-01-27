package com.dao;

import com.beans.Module;

import java.util.List;

public interface ModuleDao {
    void creer( Module module ) throws Exception;

    Module trouver(long id ) throws DAOException;

    List<Module> lister() throws DAOException;

    void supprimer( Module module ) throws DAOException;
}
