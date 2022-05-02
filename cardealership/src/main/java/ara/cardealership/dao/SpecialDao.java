/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.SpecialDto;
import java.util.List;

/**
 *
 * @author Rich
 */
public interface SpecialDao {
    
    SpecialDto getSpecialById(int id);

    List<SpecialDto> getAllSpecials();

    SpecialDto addSpecial(SpecialDto Special);

    void updateSpecial(SpecialDto Special);

    void deleteSpecialById(int id);
}
