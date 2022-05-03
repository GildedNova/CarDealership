/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.ContactDto;
import ara.cardealership.dto.SaleDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Rich
 */
public class ContactDaoDB implements ContactDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<ContactDto> getAllContacts() {
        final String GET_ALL_CONTACTS = "SELECT * FROM contact";
        return jdbc.query(GET_ALL_CONTACTS, new ContactMapper());
    }

    @Override
    public ContactDto addContact(ContactDto contact) {
        final String INSERT_CONTACT = "INSERT INTO contact(name, message, phone,"
                + "email, vid) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(INSERT_CONTACT,
                contact.getName(),
                contact.getMessage(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getVinNum());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setContactId(newId);
        return contact;
    }

    public static final class ContactMapper implements RowMapper<ContactDto> {

        @Override
        public ContactDto mapRow(ResultSet rs, int index) throws SQLException {
            ContactDto contact = new ContactDto();
            contact.setContactId(rs.getInt("id"));
            contact.setName(rs.getString("Name"));
            contact.setMessage(rs.getString("message"));
            contact.setPhone(rs.getString("phone"));
            contact.setEmail(rs.getString("email"));
            contact.setVinNum(rs.getString("VIN"));
            return contact;
        }
    }

}
