package com.example.backendlogic;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarList<Car> {
    private ArrayList<Car> carlist;

    public CarList(){
        this.carlist = new ArrayList<Car>();
    }

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
