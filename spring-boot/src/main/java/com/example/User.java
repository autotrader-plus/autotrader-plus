package com.example.backendlogic;

public class User {
    private int creditscore;
    private int monthlybudget;
    private int downpayment;
    private String zipcode;
    private int monthlyincome;
    private boolean employed;
    private boolean homeowner;
    private int monthlydebt;

    public User(int creditscore, int monthlybudget, int downpayment, String zipcode){
        this.creditscore = creditscore;
        this.monthlybudget = monthlybudget;
        this.downpayment = downpayment;
        this.zipcode = zipcode;
    }

    public User(int creditscore, int monthlybudget, int downpayment, String zipcode, int monthlyincome,
                 boolean employed, boolean homeowner, int monthlydebt){
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
}
