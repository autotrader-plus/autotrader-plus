package packages.backendlogic;
import java.util.ArrayList;

/**
 * This class created a CarList Object that is an ArrayList of Car Objects
 * @param <Car> Car Object from Car.java
 */
public class CarList<Car> {
    private final ArrayList<Car> carlist;

    /**
     * Creates empty CarList
     */
    public CarList(){
        this.carlist = new ArrayList<>();
    }

    // will be used in the future
    public CarList(ArrayList<Car> carlist){
        this.carlist = carlist;
    }

    /**
     * Adds a Car Object into the CarList Object
     * @param car Car Object
     */
    public void AddtoList(Car car){
        carlist.add(car);
    }

    /**
     * the following are getter methods
     * @return return CarList Object info when called
     */
    public int size(){
        return carlist.size();
    }

    public Car getCar(int index){
        return carlist.get(index);
    }
}
