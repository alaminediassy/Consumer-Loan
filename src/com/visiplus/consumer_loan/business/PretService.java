package com.visiplus.consumer_loan.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PretService {
    private List<Pret> loans = new ArrayList<>();

    public void addLoan(Pret loan) {
        loans.add(loan);
    }

    public List<Pret> getLoanByAmount() {
        return loans.stream()
                .sorted(Comparator.comparingDouble(Pret::getMontantDemande).reversed())
                .collect(Collectors.toList());
    }

    public List<Pret> getLoanByRate() {
        return loans.stream()
                .sorted(Comparator.comparingDouble(Pret::getMontantMensualite).reversed())
                .collect(Collectors.toList());
    }

    public List<Pret> getLoanByDateRange(LocalDate startDate, LocalDate endDate) {
        return loans.stream()
                .filter(loan -> loan.getDateEffet().isAfter(startDate) && loan.getDateEffet().isBefore(endDate))
                .collect(Collectors.toList());
    }
}
