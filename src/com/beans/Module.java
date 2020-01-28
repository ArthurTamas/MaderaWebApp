package com.beans;

import java.io.Serializable;
import java.util.List;

public class Module implements Serializable {
    private Long   id;
    private String code;
    private String libelle;
    private String prix_ht;
    private List<Composant> composant;
    private Gamme gamme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPrix_ht() {
        return prix_ht;
    }

    public void setPrix_ht(String prix_ht) {
        this.prix_ht = prix_ht;
    }

    public List<Composant> getComposant() {
        return composant;
    }

    public void setComposant(List<Composant> composant) {
        this.composant = composant;
    }

    public Gamme getGamme() {
        return gamme;
    }

    public void setGamme(Gamme gamme) {
        this.gamme = gamme;
    }
}
