/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ara.cardealership.Controller;



import ara.cardealership.dao.CarDao;
import ara.cardealership.dao.ContactDao;
import ara.cardealership.dao.EmployeeDao;
import ara.cardealership.dao.SaleDao;
import ara.cardealership.dao.SpecialDao;
import ara.cardealership.dto.CarDto;
import ara.cardealership.dto.ContactDto;
import ara.cardealership.dto.SaleDto;
import ara.cardealership.dto.SpecialDto;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




/**
 *
 * @author alexg
 */

@Controller
public class DealershipController {

    @Autowired
    CarDao carDao;
    
    @Autowired
    ContactDao contactDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    SaleDao saleDao;

    @Autowired
    SpecialDao specialDao;
    
    @Autowired
    ContactDao contactdao;
    

    @GetMapping("index")
    public String displayfeatured(Model model) {
        List<CarDto> carsaq = carDao.getAllCars();
        List<CarDto> carsShown = new ArrayList();

        for (CarDto cartemp : carsaq) {
            if (cartemp.isFeatured()) {
                carsShown.add(cartemp);
            }
        }

        model.addAttribute("cars", carsShown);
      
        return "index.html";
    }

    @GetMapping("Inventory/New")
    public String displaynew(Model model) {
        List<CarDto> carsaq = carDao.getAllCars();
        List<CarDto> carsShown = new ArrayList();

        for (CarDto cartemp : carsaq) {
            if (cartemp.getMileage() <= 1000) {
                carsShown.add(cartemp);
            }
        }

        model.addAttribute("cars", carsShown);
        
        return "redirect:/inventory/new.html";
    }

    @GetMapping("Inventory/Used")
    public String displayUsed(Model model) {
        List<CarDto> carsaq = carDao.getAllCars();
        List<CarDto> carsShown = new ArrayList();

        for (CarDto cartemp : carsaq) {
            if (cartemp.getMileage() > 1000) {
                carsShown.add(cartemp);
            }
        }

        model.addAttribute("cars", carsShown);
       
        return "inventory/used.html";
    }

    @PostMapping("details")
    public String carDetails(CarDto carEather, HttpServletRequest request, Model model) {

        String carID = request.getParameter("carID");

        carEather = carDao.getCarById(Integer.parseInt(carID));

        model.addAttribute("car", carEather);

        return "redirect:/details";
    }
    
    
    @GetMapping("specials")
    public String specials (Model model) {
        
        List<SpecialDto> specials = specialDao.getAllSpecials();
        
        model.addAttribute("specials", specials);


        return "redirect:/specials.html";
    }
    
    
    @GetMapping("contact")
    public String contact (Model model) {
        
        List<ContactDto> listcontacts = contactdao.getAllContacts();
        model.addAttribute("Contacts", listcontacts);


        return "redirect:/contact.html";
    }
    
    
    
    @PostMapping("addcontact")
    public String addcontact (ContactDto contact, HttpServletRequest request, Model model) {
        
        //check if contact exists for later
        String name = request.getParameter("Name");
        
        String Email = request.getParameter("Email");
        
        String Phone = request.getParameter("Phone");
        
        String Message = request.getParameter("Message");
        
        contact.setName(name);
        contact.setEmail(Email);
        contact.setPhone(Email);
        contact.setMessage(Email);

        contactdao.addContact(contact);


        return "redirect:/contact.html";
    }
    
    @GetMapping("sales/index")//show all cars to purchase
    public String showsales(Model model) {
        List<CarDto> carsaq = carDao.getAllCars();
        List<CarDto> carsShown = new ArrayList();


        model.addAttribute("cars", carsShown);
     
        return "redirect:/sales/sales.html";
    }
    
    
    @GetMapping("sales/purchase")//show purchase page
    public String showsale(CarDto car, HttpServletRequest request, Model model) {
        
        String carID = request.getParameter("CarID");
         
        return "redirect:/sales/purchase.html";
    }
    
    
    
    @PostMapping("sales/purchase")//do a purchase request
    public String dosale(SaleDto sale, HttpServletRequest request, Model model) {
    
     String name = request.getParameter("Name");
    
     String Phone = request.getParameter("Phone");
    
     String Street1 = request.getParameter("Street1");
    
     String Street2 = request.getParameter("Street2");
    
     String City = request.getParameter("City");
    
     String Zipcode = request.getParameter("Zipcode");
    
     String State = request.getParameter("State");
    
     String price = request.getParameter("Price");
    
     float pprice  = Float.parseFloat(price);
    
     String ptype = request.getParameter("purchasetype");
    
     sale.setName(name);
     
     sale.setPhone(Phone);
     
     sale.setStreet1(Street1);
     
     sale.setStreet2(Street2);
     
     sale.setCity(City);
     
     sale.setZipCode(Zipcode);
     
     sale.setState(State);
     
     sale.setPurchasePrice(pprice);
     
     sale.setPurchaseType(ptype);
     
     //setemployee once we have security
        
     saleDao.addSale(sale);   
   
    
    return "redirect:/sales/sales.html";
    
    }
    
  
    
}
