package com.example.backendlogic;

public class User {
    private final int creditscore;
    private final int monthlybudget;
    private final int downpayment;
    private String zipcode;
    private String name;
    private int monthlyincome;
    private boolean employed;
    private boolean homeowner;
    private int monthlydebt;

    public User(int creditscore, int monthlybudget, int downpayment, String zipcode, String name){
        this.creditscore = creditscore;
        this.monthlybudget = monthlybudget;
        this.downpayment = downpayment;
        this.zipcode = zipcode;
        this.name = name;
    }

    public User(int creditscore, int monthlybudget, int downpayment, String zipcode, String name, int monthlyincome,
                boolean employed, boolean homeowner, int monthlydebt){
        this.creditscore = creditscore;
        this.monthlybudget = monthlybudget;
        this.downpayment = downpayment;
        this.zipcode = zipcode;
        this.name = name;
        this.monthlyincome = monthlyincome;
        this.employed = employed;
        this.homeowner = homeowner;
        this.monthlydebt = monthlydebt;
    }

    public String getPriceRange(){
        return Integer.toString((36 * this.monthlybudget) + this.downpayment);
    }

    public String getPriceRange(int term){
        return Integer.toString((term * this.monthlybudget) + this.downpayment);
    }

    public String getMonthlybudget(){
        return Integer.toString(this.monthlybudget);
    }

    public String getCreditscore(){
        return Integer.toString(this.creditscore);
    }

    public String getLocation(){
        return this.zipcode;
    }

    public String getName() {  return this.name;
    }

    public String getDownpayment() { return Integer.toString(this.downpayment);
    }

    public String getMonthlyincome() { return Integer.toString(this.monthlyincome);
    }

    public String isEmployed() {
        if (this.employed) {
            return "Employed";
        }
        return "not Employed";
    }

    public String isHomeowner() {
        if (this.homeowner){
            return "Homeowner";
        }
        return "not Homeowner";
    }

    public String getMonthlydebt() { return Integer.toString(this.monthlydebt);
    }
}
