package com.amigoscode.car;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarDAO carDAO;

    public CarService(@Qualifier("fake") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void registerNewCar(Car car) {
        // business logic. check if reg number is valid and not taken
        if (car.getPrice() <= 0) {
            throw new IllegalStateException("Car price cannot be 0 or less");
        }
        int result = carDAO.insertCar(car);

        if (result != 1) {
            throw new IllegalStateException("Could not save car.");
        }
    }

    private Car getCarOrThrow(Integer id){
        Car car = carDAO.selectCarById(id);
        if (car == null){
            throw new IllegalStateException("This car does not exist");
        }
        return car;
    }

    public List<Car> getCars() {
        return carDAO.selectAllCars();
    }

    public Car carById(Integer id) {
        Car car = carDAO.selectCarById(id);
        // calling on the carDAO, then the method within the implementation class,
        // parenthesis calling on the placeholder
        if (car == null) {
            throw new IllegalStateException("This car does not exist");
        }
        return car;
    }

    public void deleteCar(Integer deleteId) {
//       find if car exists
//        Car exists = carDAO.selectCarById(deleteId);
//        if (exists == null) {
//            throw new IllegalStateException("This car does not exist");
//       OR
// if(carDAO.selectCarById(deleteId) == null){
        // throw new IllegalStateException("car does not exist")

        Car carExist = getCarOrThrow(deleteId);
        int deleted = carDAO.deleteCar(deleteId);

        if (deleted != 1) {
            throw new IllegalStateException("Could not delete car.");
        }
    }

    public void updateCar(Integer id, Car updatedCar) {
//       car exists?
//        Car car = carDAO.selectCarById(id);
//        if (car == null) {
//            throw new IllegalStateException("This car does not exist");
//        }
        Car carExist = getCarOrThrow(id);
        int result = carDAO.updateCar(id, updatedCar);
        if (result != 1) {
            throw new IllegalStateException("Could not update car.");
        }
    }

}
