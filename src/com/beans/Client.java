package com.beans;

import java.io.Serializable;

public class Client implements Serializable {

    private Long   id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String image;

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAdresse( String adresse ) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setTelephone( String telephone ) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}