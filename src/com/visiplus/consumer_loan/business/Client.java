package com.visiplus.consumer_loan.business;



public class Client {
    private final Long id;
    private final String lastname;
    private final String firstname;
    private static Long counter = 0L;

    public Client(String nom, String prenom) {
        this.id = ++counter;
        this.lastname = nom;
        this.firstname = prenom;
    }

    public Long getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
