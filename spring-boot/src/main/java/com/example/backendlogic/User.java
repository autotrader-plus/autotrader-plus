package com.example.backendlogic;

public class User {
    private final String name;
    private final int creditscore;
    private final int monthlybudget;
    private final int downpayment;
    private String zipcode;
    private int monthlyincome;
    private boolean employed;
    private boolean homeowner;
    private int monthlydebt;

    public User(String name, int creditscore, int monthlybudget, int downpayment, String zipcode){
        this.name = name;
        this.creditscore = creditscore;
        this.monthlybudget = monthlybudget;
        this.downpayment = downpayment;
        this.zipcode = zipcode;
    }

    public User(String name, int creditscore, int monthlybudget, int downpayment, String zipcode, int monthlyincome,
                 boolean employed, boolean homeowner, int monthlydebt){
        this.name = name;
        this.creditscore = creditscore;
        this.monthlybudget = monthlybudget;
        this.downpayment = downpayment;
        this.zipcode = zipcode;
        this.monthlyincome = monthlyincome;
        this.employed = employed;
        this.homeowner = homeowner;
        this.monthlydebt = monthlydebt;
    }

    public int getPriceRange(){
        return (36 * this.monthlybudget) + this.downpayment;
    }

    public int getPriceRange(int term){
        return (term * this.monthlybudget) + this.downpayment;
    }

    public int getMonthlybudget(){
        return this.monthlybudget;
    }

    public int getCreditscore(){
        return this.creditscore;
    }

    public String getLocation(){
        return this.zipcode;
    }

    public int getDownpayment() {
        return this.downpayment;
    }

    public int getMonthlyincome() {
        return this.monthlyincome;
    }

    public boolean isEmployed() {
        return this.employed;
    }

    public boolean isHomeowner() {
        return this.homeowner;
    }

    public int getMonthlydebt() {
        return this.monthlydebt;
    }

    public String getName(){
        return this.name;
    }
}
