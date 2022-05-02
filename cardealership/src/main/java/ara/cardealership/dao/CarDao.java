/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.CarDto;
import ara.cardealership.dto.MakeDto;
import java.util.List;

/**
 *
 * @author Rich
 */
public interface CarDao {

    CarDto getCarById(int id);

    List<CarDto> getAllCars();

    CarDto addCar(CarDto car);

    void updateCourse(CarDto car);

    void deleteCarById(int id);

    List<String> getTransmissions();

    List<String> getBodyStyles();

    List<String> getColors();

    List<MakeDto> getMakes();

    List<String> getModels();

    MakeDto addMake(MakeDto make);
    
    void addModel(String model);
}
