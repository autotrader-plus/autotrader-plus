package com.example.backendlogic;
import java.util.ArrayList;

public class CarList<Car> {
    private final ArrayList<Car> carlist;

    public CarList(){
        this.carlist = new ArrayList<>();
    }

    // will be used in the future
    public CarList(ArrayList<Car> carlist){
        this.carlist = carlist;
    }

    public void AddtoList(Car car){
        carlist.add(car);
    }

    public int size(){
        return carlist.size();
    }

    public Car getCar(int index){
        return carlist.get(index);
    }
}
