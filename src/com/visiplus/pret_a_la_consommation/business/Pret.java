package com.visiplus.pret_a_la_consommation.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pret {
    private static Long compteur = 0L;

    private Long id;
    private double montantDemande;
    private double montantMensualite;
    private LocalDate dateEffet;
    private Client client;
    private List<Mensualite> mensualites;

    public Pret(Client client, double montantDemande, double tauxAnnuel, int dureeEnMois, LocalDate dateEffet) {
        this.id = ++compteur;
        this.client = client;
        this.montantDemande = montantDemande;
        this.dateEffet = dateEffet;
        this.mensualites = new ArrayList<>();

        // Calcul du taux mensuel
        double tauxMensuel = tauxAnnuel / 12;
        // Calcul de la mensualité
        this.montantMensualite = montantDemande * tauxMensuel / (1 - Math.pow(1 + tauxMensuel, -dureeEnMois));

        // Génération des mensualités
        double capitalRestant = montantDemande;
        for (int i = 0; i < dureeEnMois; i++) {
            double partInterets = capitalRestant * tauxMensuel;
            double partCapital = montantMensualite - partInterets;
            capitalRestant -= partCapital;
            LocalDate datePrelevement = dateEffet.plusMonths(i);
            Mensualite mensualite = new Mensualite(datePrelevement, partInterets, partCapital);
            this.mensualites.add(mensualite);
        }
    }

    public Long getId() {
        return id;
    }

    public double getMontantDemande() {
        return montantDemande;
    }

    public double getMontantMensualite() {
        return montantMensualite;
    }

    public LocalDate getDateEffet() {
        return dateEffet;
    }

    public Client getClient() {
        return client;
    }

    public List<Mensualite> getMensualites() {
        return mensualites;
    }
}