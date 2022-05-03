/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.SaleDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rich
 */
@Repository
public class SaleDaoDB implements SaleDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public SaleDto getSaleById(int id) {
        try {
            final String GET_SALE_BY_ID = "SELECT * FROM sale WHERE id = ?";
            return jdbc.queryForObject(GET_SALE_BY_ID, new SaleMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SaleDto> getAllSales() {
        final String GET_ALL_SALES = "SELECT * FROM sale";
        return jdbc.query(GET_ALL_SALES, new SaleMapper());
    }

    @Override
    public SaleDto addSale(SaleDto sale) {
        final String INSERT_SALE = "INSERT INTO sale(Name, Phone, Email,"
                + "Street1, Street2, City, State, Zip, PurchasePrice, PurchaseType,"
                + " EmployeeId, CarId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(INSERT_SALE,
            sale.getName(),
            sale.getPhone(),
            sale.getEmail(),
            sale.getStreet1(),
            sale.getStreet2(),
            sale.getCity(),
            sale.getState(),
            sale.getZipCode(),
            sale.getPurchasePrice(),
            sale.getPurchaseType(),
            sale.getEmployeeId(),
            sale.getCarId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sale.setSaleId(newId);
        return sale;
    }

    public static final class SaleMapper implements RowMapper<SaleDto> {

        @Override
        public SaleDto mapRow(ResultSet rs, int index) throws SQLException {
            SaleDto sale = new SaleDto();

            sale.setSaleId(rs.getInt("id"));
            sale.setDateAdded(rs.getString("DateAdded"));
            sale.setName(rs.getString("Name"));
            sale.setPhone(rs.getString("Phone"));
            sale.setEmail(rs.getString("email"));
            sale.setStreet1(rs.getString("Street1"));
            sale.setStreet2(rs.getString("Street2"));
            sale.setCity(rs.getString("city"));
            sale.setState(rs.getString("State"));
            sale.setZipCode(rs.getString("Zip"));
            sale.setPurchasePrice(rs.getFloat("PurchasePrice"));
            sale.setPurchaseType(rs.getString("PurchaseType"));
            sale.setEmployeeId(rs.getInt("employeeId"));
            sale.setCarId(rs.getInt("CarId"));
            return sale;
        }
    }
}
