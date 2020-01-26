package com.beans;

import java.io.Serializable;
import java.util.List;

public class Maison implements Serializable {
    private Long   id;
    private Boolean is_maison_modele;
    private String libelle_modele;
    private String date_creation;
    private List<Module> modules;
    private Gamme gamme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIs_maison_modele() {
        return is_maison_modele;
    }

    public void setIs_maison_modele(Boolean is_maison_modele) {
        this.is_maison_modele = is_maison_modele;
    }

    public String getLibelle_modele() {
        return libelle_modele;
    }

    public void setLibelle_modele(String libelle_modele) {
        this.libelle_modele = libelle_modele;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        date_creation = date_creation;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Gamme getGamme() {
        return gamme;
    }

    public void setGamme(Gamme gamme) {
        this.gamme = gamme;
    }
}
