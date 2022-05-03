/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.SpecialDto;
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
public class SpecialDaoDB implements SpecialDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public SpecialDto getSpecialById(int id) {
        try {
            final String GET_SPECIAL_BY_ID = "SELECT * FROM special WHERE id = ?";
            return jdbc.queryForObject(GET_SPECIAL_BY_ID, new SpecialMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SpecialDto> getAllSpecials() {
        final String GET_ALL_SPECIALS = "SELECT * FROM Special";
        return jdbc.query(GET_ALL_SPECIALS, new SpecialMapper());
    }

    @Override
    public SpecialDto addSpecial(SpecialDto special) {
        final String INSERT_SPECIAL = "INSERT INTO special(Title, Body)"
                + " VALUES (?, ?)";
        jdbc.update(INSERT_SPECIAL,
                special.getTitle(),
                special.getBody());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setSpecialId(newId);
        return special;
    }

    @Override
    public void updateSpecial(SpecialDto special) {
        final String INSERT_SPECIAL = "UPDATE special SET Title = ?, Body = ?"
                + " WHERE id = ?";
        jdbc.update(INSERT_SPECIAL,
                special.getTitle(),
                special.getBody(),
                special.getSpecialId());
    }

    @Override
    public void deleteSpecialById(int id) {
        final String UPDATE_SPECIAL = "UPDATE special SET isActive = 0 WHERE id = ?";
        jdbc.update(UPDATE_SPECIAL, id);
    }

    public static final class SpecialMapper implements RowMapper<SpecialDto> {

        @Override
        public SpecialDto mapRow(ResultSet rs, int index) throws SQLException {
            SpecialDto special = new SpecialDto();
            special.setSpecialId(rs.getInt("id"));
            special.setTitle(rs.getString("Title"));
            special.setBody(rs.getString("Body"));
            special.setIsActive(rs.getBoolean("active"));
            return special;
        }
    }

}
