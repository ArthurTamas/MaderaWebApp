package com.beans;

import java.io.Serializable;

public class Commercial extends Utilisateur implements Serializable {
    private String numero_commercial;

    public String getNumero_commercial() {
        return numero_commercial;
    }

    public void setNumero_commercial(String numero_commercial) {
        this.numero_commercial = numero_commercial;
    }
}
