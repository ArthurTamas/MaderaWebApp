package com.beans;

import java.io.Serializable;

import org.joda.time.DateTime;


public class Projet implements Serializable {

    private Integer id;
    private String numero_projet;
    private String avancement;
    private DateTime date_creation;
    private String modalite_paiement;
    private DateTime date_debut_prestation;
    private DateTime date_fin_prestation;
    private String adresse;
    private Commercial commercial;
    private Client client;
    private Maison maison;
    private Gamme gamme;
    private Module module;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero_projet() {
        return numero_projet;
    }

    public void setNumero_projet(String numero_projet) {
        this.numero_projet = numero_projet;
    }

    public String getAvancement() {
        return avancement;
    }

    public void setAvancement(String avancement) {
        this.avancement = avancement;
    }

    public DateTime getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(DateTime date_creation) { this.date_creation = date_creation;
    }

    public String getModalite_paiement() {
        return modalite_paiement;
    }

    public void setModalite_paiement(String modalite_paiement) {
        this.modalite_paiement = modalite_paiement;
    }

    public DateTime getDate_debut_prestation() {
        return date_debut_prestation;
    }

    public void setDate_debut_prestation(DateTime date_debut_prestation) {
        this.date_debut_prestation = date_debut_prestation;
    }

    public DateTime getDate_fin_prestation() {
        return date_fin_prestation;
    }

    public void setDate_fin_prestation(DateTime date_fin_prestation) {
        this.date_fin_prestation = date_fin_prestation;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Commercial getCommercial() {
        return commercial;
    }

    public void setCommercial(Commercial commercial) {
        this.commercial = commercial;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    public Gamme getGamme() {
        return gamme;
    }

    public void setGamme(Gamme gamme) {
        this.gamme = gamme;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}