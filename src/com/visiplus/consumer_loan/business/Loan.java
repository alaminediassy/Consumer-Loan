package com.visiplus.consumer_loan.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    private static Long counter = 0L;

    private final Long id;
    private final double amountRequested;
    private final double monthlyAmount;
    private final LocalDate dateEffect;
    private final Client client;
    private final List<Mensualite> monthlyPayments;

    public Loan(Client client, double requestedAmounted, double annualRate, int dureeEnMois, LocalDate dateEffet) {
        this.id = ++counter;
        this.client = client;
        this.amountRequested = requestedAmounted;
        this.dateEffect = dateEffet;
        this.monthlyPayments = new ArrayList<>();

        // Calculation of the monthly rate
        double monthlyRate = annualRate / 12;
        // Calculation of the monthly payment
        this.monthlyAmount = requestedAmounted * monthlyRate / (1 - Math.pow(1 + monthlyRate, -dureeEnMois));

        // Generation of monthly payments
        double capitalRestant = requestedAmounted;
        for (int i = 0; i < dureeEnMois; i++) {
            double interestPortion = capitalRestant * monthlyRate;
            double partCapital = monthlyAmount - interestPortion;
            capitalRestant -= partCapital;
            LocalDate datePrelevement = dateEffet.plusMonths(i);
            Mensualite mensualite = new Mensualite(datePrelevement, interestPortion, partCapital);
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