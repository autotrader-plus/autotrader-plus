package packages.backendlogic;
import java.util.ArrayList;

/**
 * This class creates a CarList Object that is an ArrayList of Car Objects
 * @param <Car> Car Object from Car.java
 */
public class CarList<Car> {
    private final ArrayList<Car> carList;

    /**
     * Creates empty CarList
     */
    public CarList(){
        this.carList = new ArrayList<>();
    }

    /**
     * Will be used in the future
     * Create a CarList Object given an ArrayList of Car Objects
     * @param carList The ArrayList of Car objects
     */
    public CarList(ArrayList<Car> carList){
        this.carList = carList;
    }

    /**
     * Adds a Car Object into the CarList Object
     * @param car Car Object
     */
    public void AddtoList(Car car){
        carList.add(car);
    }

    /**
     * the following are getter methods
     * @return return CarList Object info when called
     */
    /**
     * Gets the size of the carList
     * @return An integer representing the size of the carList
     */
    public int size(){
        return carList.size();
    }

    /**
     * Gets the Car Object from carList at a specific index
     * @param index The index of the Car Object
     * @return A Car Object representing the Car Object at the given index in carList
     */
    public Car getCar(int index){
        return carList.get(index);
    }
}
