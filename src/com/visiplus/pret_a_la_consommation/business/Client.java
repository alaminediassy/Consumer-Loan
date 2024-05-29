package com.visiplus.pret_a_la_consommation.business;



public class Client {
    private Long id;
    private String nom;
    private String prenom;
    private static Long compteur = 0L;

    public Client(String nom, String prenom) {
        this.id = ++compteur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
