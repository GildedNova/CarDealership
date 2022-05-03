/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ara.cardealership.dao;

import ara.cardealership.dto.ContactDto;
import java.util.List;

/**
 *
 * @author Rich
 */
public interface ContactDao {

    List<ContactDto> getAllContacts();

    ContactDto addContact(ContactDto contacts);
}
