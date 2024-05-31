package com.visiplus.consumer_loan.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PretService {
    private List<Loan> loans = new ArrayList<>();

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public List<Loan> getLoanByAmount() {
        return loans.stream()
                .sorted(Comparator.comparingDouble(Loan::getMontantDemande).reversed())
                .collect(Collectors.toList());
    }

    public List<Loan> getLoanByRate() {
        return loans.stream()
                .sorted(Comparator.comparingDouble(Loan::getMontantMensualite).reversed())
                .collect(Collectors.toList());
    }

    public List<Loan> getLoanByDateRange(LocalDate startDate, LocalDate endDate) {
        return loans.stream()
                .filter(loan -> loan.getDateEffet().isAfter(startDate) && loan.getDateEffet().isBefore(endDate))
                .collect(Collectors.toList());
    }
}
