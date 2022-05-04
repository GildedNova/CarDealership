/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.CarDto;
import ara.cardealership.dto.MakeDto;
import ara.cardealership.dto.ModelDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rich
 */
@Repository
public class CarDaoDB implements CarDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public CarDto getCarById(int id) {
        try {
            final String GET_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
            return jdbc.queryForObject(GET_CAR_BY_ID, new CarMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<CarDto> getAllCars() {
        final String GET_ALL_CARS = "SELECT *, make.name as makeName, model.name as modelName FROM car inner join make on car.makeId = make.id inner join model on car.modelId = model.id";
        return jdbc.query(GET_ALL_CARS, new CarMapper());
    }

    @Override
    @Transactional
    public CarDto addCar(CarDto car) {
        final String INSERT_CAR = "INSERT INTO car(VIN, MakeID, ModelID, Type, BodyStyle, Year, Transmission, Color, InteriorColor, Milage, "
                + "MSRP, SalePrice, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbc.update(INSERT_CAR,
                car.getVinNum(),
                car.getMakeId(),
                car.getModelId(),
                car.getType(),
                car.getBodyStyle(),
                car.getYear(),
                car.getTransmission(),
                car.getExteriorColor(),
                car.getInteriorColor(),
                car.getMileage(),
                car.getMsrp(),
                car.getSalePrice(),
                car.getDescription());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        car.setCarId(newId);
        return car;
    }

    @Override
    public void updateCar(CarDto car) {
        final String UPDATE_CAR = "UPDATE car SET VIN = ?, MakeID = ?, ModelID = ?, Type = ?, BodyStyle = ?, Year = ?, Transmission = ?, Color = ?, InteriorColor = ?, Milage = ?, "
                + "MSRP = ?, SalePrice = ?, Description = ?, isFeatured = ?, isSold = ?  WHERE id = ?";

        jdbc.update(UPDATE_CAR,
                car.getVinNum(),
                car.getMakeId(),
                car.getModelId(),
                car.getType(),
                car.getBodyStyle(),
                car.getYear(),
                car.getTransmission(),
                car.getExteriorColor(),
                car.getInteriorColor(),
                car.getMileage(),
                car.getMsrp(),
                car.getSalePrice(),
                car.getDescription(),
                car.isFeatured(),
                car.isSold(),
                car.getCarId());
    }

    @Override
    @Transactional
    public void deleteCarById(int id) {
        final String DELETE_CAR = "DELETE FROM Car WHERE id = ?";
        jdbc.update(DELETE_CAR, id);
    }

    @Override
    public List<String> getAllTransmissions() {
        final String GET_ALL_TRANSMISSIONS = "SELECT Transmission FROM Transmission";
        return jdbc.queryForList(GET_ALL_TRANSMISSIONS, String.class);
    }

    @Override
    public List<String> getAllBodyStyles() {
        final String GET_ALL_BODYSTYLES = "SELECT Style FROM BodyStyle";
        return jdbc.queryForList(GET_ALL_BODYSTYLES, String.class);
    }

    @Override
    public List<String> getAllColors() {
        final String GET_ALL_COLORS = "SELECT Color FROM Color";
        return jdbc.queryForList(GET_ALL_COLORS, String.class);
    }

    @Override
    public List<MakeDto> getAllMakes() {
        final String GET_ALL_MAKES = "SELECT * FROM Make";
        return jdbc.query(GET_ALL_MAKES, new MakeMapper());
    }

    @Override
    public List<ModelDto> getAllModels() {
        final String GET_ALL_MODELS = "SELECT * FROM Model";
        return jdbc.query(GET_ALL_MODELS, new ModelMapper());
    }

    @Override
    public MakeDto addMake(MakeDto make) {
        final String INSERT_MAKE = "INSERT INTO make(name, EmployeeId)"
                + " VALUES (?, ?)";

        jdbc.update(INSERT_MAKE,
                make.getName(),
                make.getEmployeeId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setMakeId(newId);
        return make;
    }

    @Override
    public ModelDto addModel(ModelDto model) {
        final String INSERT_MODEL = "INSERT INTO model(name, MakeId)"
                + " VALUES (?, ?)";

        jdbc.update(INSERT_MODEL,
                model.getName(),
                model.getMakeId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setModelId(newId);
        return model;
    }

    public static final class CarMapper implements RowMapper<CarDto> {

        @Override
        public CarDto mapRow(ResultSet rs, int index) throws SQLException {
            CarDto car = new CarDto();
            car.setCarId(rs.getInt("id"));
            car.setVinNum(rs.getString("VIN"));
            car.setMakeId(rs.getInt("MakeID"));
            car.setModelId(rs.getInt("ModelID"));
            car.setType(rs.getString("Type"));
            car.setBodyStyle(rs.getString("BodyStyle"));
            car.setMileage(rs.getInt("Milage"));
            car.setYear(rs.getInt("Year"));
            car.setTransmission(rs.getString("Transmission"));
            car.setExteriorColor(rs.getString("Color"));
            car.setInteriorColor(rs.getString("InteriorColor"));
            car.setMsrp(rs.getFloat("MSRP"));
            car.setSalePrice(rs.getFloat("SalePrice"));
            car.setDescription(rs.getString("Description"));
            car.setFeatured(rs.getBoolean("isFeatured"));
            car.setSold(rs.getBoolean("isSold"));
            car.setMakeName(rs.getString("makename"));
            car.setModelName(rs.getString("modelname"));

            return car;
        }
    }

    public static final class MakeMapper implements RowMapper<MakeDto> {

        @Override
        public MakeDto mapRow(ResultSet rs, int index) throws SQLException {
            MakeDto make = new MakeDto();
            make.setMakeId(rs.getInt("id"));
            make.setName(rs.getString("Name"));
            make.setEmployeeId(rs.getInt("id"));
            make.setDateAdded(rs.getString("DateAdded"));
            return make;
        }
    }

    public static final class ModelMapper implements RowMapper<ModelDto> {

        @Override
        public ModelDto mapRow(ResultSet rs, int index) throws SQLException {
            ModelDto model = new ModelDto();
            model.setName(rs.getString("name"));
            model.setModelId(rs.getInt("id"));
            return model;
        }
    }
}
