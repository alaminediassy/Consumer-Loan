package com.visiplus.consumer_loan.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pret {
    private static Long counter = 0L;

    private Long id;
    private double amountRequested;
    private double monthlyAmount;
    private LocalDate dateEffect;
    private Client client;
    private List<Mensualite> monthlyPayments;

    public Pret(Client client, double montantDemande, double tauxAnnuel, int dureeEnMois, LocalDate dateEffet) {
        this.id = ++counter;
        this.client = client;
        this.amountRequested = montantDemande;
        this.dateEffect = dateEffet;
        this.monthlyPayments = new ArrayList<>();

        // Calcul du taux mensuel
        double tauxMensuel = tauxAnnuel / 12;
        // Calcul de la mensualité
        this.monthlyAmount = montantDemande * tauxMensuel / (1 - Math.pow(1 + tauxMensuel, -dureeEnMois));

        // Génération des mensualités
        double capitalRestant = montantDemande;
        for (int i = 0; i < dureeEnMois; i++) {
            double partInterets = capitalRestant * tauxMensuel;
            double partCapital = monthlyAmount - partInterets;
            capitalRestant -= partCapital;
            LocalDate datePrelevement = dateEffet.plusMonths(i);
            Mensualite mensualite = new Mensualite(datePrelevement, partInterets, partCapital);
            this.monthlyPayments.add(mensualite);
        }
    }

    public Long getId() {
        return id;
    }

    public double getMontantDemande() {
        return amountRequested;
    }

    public double getMontantMensualite() {
        return monthlyAmount;
    }

    public LocalDate getDateEffet() {
        return dateEffect;
    }

    public Client getClient() {
        return client;
    }

    public List<Mensualite> getMensualites() {
        return monthlyPayments;
    }
}