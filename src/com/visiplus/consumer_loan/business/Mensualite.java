package com.visiplus.consumer_loan.business;

import java.time.LocalDate;

public class Mensualite {
    private final LocalDate dueDate;
    private final double partialInterestRepayments;
    private final double partialRepaymentCapital;

    public Mensualite(LocalDate datePrelevement, double partInteretsRembourses, double partCapitalRembourse) {
        this.dueDate = datePrelevement;
        this.partialInterestRepayments = partInteretsRembourses;
        this.partialRepaymentCapital = partCapitalRembourse;
    }

    public LocalDate getDatePrelevement() {
        return dueDate;
    }

    public double getPartInteretsRembourses() {
        return partialInterestRepayments;
    }

    public double getPartCapitalRembourse() {
        return partialRepaymentCapital;
    }
}
