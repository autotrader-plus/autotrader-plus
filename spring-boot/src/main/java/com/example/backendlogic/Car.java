package com.example.backendlogic;

public class Car {

    private String year;
    private String brand;
    private String KMS;
    private String cartype;
    private String ID;
    private String cost;
    private String Dealership;

    public Car(String year, String brand, String KMS, String cartype, String ID,
               String cost, String Dealership) {
        this.year = year;
        this.brand = brand;
        this.KMS = KMS;
        this.cartype = cartype;
        this.ID = ID;
        this.cost = cost;
        this.Dealership = Dealership;
    }

    public boolean CanAfford(int budget){
        int price = Integer.parseInt(this.cost);
        return price <= budget;
    }

    public int getPrice(){
        return Integer.parseInt(this.cost);
    }

    public String getBrand(){
        return this.brand;
    }

    public String getYear(){
        return this.year;
    }

    public String getKMS(){
        return this.KMS;
    }

    public String returnID(){
        return this.ID;
    }

    public String show(){
        return this.year + " " + this.brand + " " +  this.KMS + " " +
                this.ID + " " + this.cost;
    }


}
