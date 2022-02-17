package com.amigoscode.car;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fake")
public class FakeCarDataAccessService implements CarDAO {

    private List<Car> db;

    public FakeCarDataAccessService() {
        this.db = new ArrayList<>();
    }

    @Override
    public Car selectCarById(Integer id) {
        for(Car c : db){
            if(id == c.getId()){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Car> selectAllCars() {
        return db;
    }

    @Override
    public int insertCar(Car car) {
        db.add(car);
        return 1;
    }

    @Override
    public int deleteCar(Integer id) {
        this.db.remove(selectCarById(id));
        return 1;
    }


    @Override
    public int updateCar(Integer id, Car updated) {
        Car car = selectCarById(id);
        car.setBrand(updated.getBrand());
        car.setPrice(updated.getPrice());
        car.setRegNumber(updated.getRegNumber());
        return 1;
    }
}
