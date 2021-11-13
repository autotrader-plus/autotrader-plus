package com.example.backendlogic;

/**
 * This class creates a Car Object for the provided cars in carlist from database
 *
 */
public class Car {

    private final String year;
    private final String brand;
    private final String KMS;
    private final String cartype; //will be used in future
    private final String ID;
    private final String cost;
    private final String Dealership; //will be used in future

    /**
     * Creates Car Object
     * @param year Car year
     * @param brand Car brand
     * @param KMS Car KMS
     * @param cartype Car cartype
     * @param ID Car ID
     * @param cost Car cost/price
     * @param Dealership Dealership associated with the Car
     */
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
    /**
     * will be used in future
     * @param budget User Object budget
     * @return returns whether this Car can be afforded by User
     */
    public boolean CanAfford(int budget){
        int price = Integer.parseInt(this.cost);
        return price <= budget;
    }

    /**
     * The following are the getter methods for Car Objects.
     * @return returns Car Object info when called
     */
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

    //will be used in future
    public String returnID(){
        return this.ID;
    }

    //will be used in future/used for testing
    public String show(){
        return this.year + " " + this.brand + " " +  this.KMS + " " +
                this.ID + " " + this.cost;
    }
}
