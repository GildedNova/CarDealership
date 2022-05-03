/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.CarDto;
import ara.cardealership.dto.MakeDto;
import ara.cardealership.dto.ModelDto;
import java.util.List;

/**
 *
 * @author Rich
 */
public interface CarDao {

    CarDto getCarById(int id);

    List<CarDto> getAllCars();

    CarDto addCar(CarDto car);

    void updateCar(CarDto car);

    void deleteCarById(int id);

    List<String> getAllTransmissions();

    List<String> getAllBodyStyles();

    List<String> getAllColors();

    List<MakeDto> getAllMakes();

    List<ModelDto> getAllModels();

    MakeDto addMake(MakeDto make);
    
    ModelDto addModel(ModelDto model);
}
