package com.visiplus.pret_a_la_consommation.business;

import java.time.LocalDate;

public class Mensualite {
    private LocalDate datePrelevement;
    private double partInteretsRembourses;
    private double partCapitalRembourse;

    public Mensualite(LocalDate datePrelevement, double partInteretsRembourses, double partCapitalRembourse) {
        this.datePrelevement = datePrelevement;
        this.partInteretsRembourses = partInteretsRembourses;
        this.partCapitalRembourse = partCapitalRembourse;
    }

    public LocalDate getDatePrelevement() {
        return datePrelevement;
    }

    public double getPartInteretsRembourses() {
        return partInteretsRembourses;
    }

    public double getPartCapitalRembourse() {
        return partCapitalRembourse;
    }
}
