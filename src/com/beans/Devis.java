package com.beans;

import java.util.List;

public class Devis {

    private String numeroDevis;
    private String status;
    private List<List> ligneDevis;
    private String prixHT;
    private String prixTTC;

    public String getNumeroDevis() {
        return numeroDevis;
    }

    public void setNumeroDevis(String numeroDevis) {
        this.numeroDevis = numeroDevis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<List> getLigneDevis() {
        return ligneDevis;
    }

    public void setLigneDevis(List<List> ligneDevis) {
        this.ligneDevis = ligneDevis;
    }

    public String getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(String prixHT) {
        this.prixHT = prixHT;
    }

    public String getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(String prixTTC) {
        this.prixTTC = prixTTC;
    }
}
