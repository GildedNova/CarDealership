/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.SaleDto;
import java.util.List;

/**
 *
 * @author Rich
 */
public interface SaleDao {

    SaleDto getSaleById(int id);

    List<SaleDto> getAllSales();

    SaleDto addSale(SaleDto sale);

}
