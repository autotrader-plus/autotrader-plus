package packages.backend_logic;

/**
 * Represents a Car Object
 */
public class Car {

    private String year;
    private String brand;
    private String KMS;
    private String carType;
    private String ID;
    private String cost;
    private String dealership;

    /**
     * Creates Car Object
     * @param year Car year
     * @param brand Car brand
     * @param KMS Car KMS
     * @param carType Car type
     * @param ID Car ID
     * @param cost Car cost/price
     * @param dealership Dealership associated with the Car
     */
    public Car(String year, String brand, String KMS, String carType, String ID,
               String cost, String dealership) {
        this.year = year;
        this.brand = brand;
        this.KMS = KMS;
        this.carType = carType;
        this.ID = ID;
        this.cost = cost;
        this.dealership = dealership;
    }

    /**
     * Gets the Car's price
     * @return An integer representing the Car's price
     */
    public int getPrice(){
        return Integer.parseInt(this.cost);
    }

    /**
     * Gets the Car's brand
     * @return A string representing the Car's brand
     */
    public String getBrand(){
        return this.brand;
    }

    /**
     * Gets the Car's year
     * @return A string representing the Car's year
     */
    public String getYear(){
        return this.year;
    }

    /**
     * Gets the Car's KMS driven
     * @return A string representing the Car's KMS driven
     */
    public String getKMS(){
        return this.KMS;
    }

    /**
     * Gets the Car's ID number
     * @return A string representing the Car's ID number
     */
    public String getID(){
        return this.ID;
    }
}
