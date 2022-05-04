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

    @Autowired//adding by magic all the constructors through Springtm
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
    
    public List<CarDto> avcars()//get available cars
    {
        List<CarDto> carsaq = carDao.getAllCars();

        List<CarDto> carsShown = new ArrayList();

        for (CarDto cartemp : carsaq) {
            if (cartemp.isSold() == false) {
                carsShown.add(cartemp);
            }
        }
        
      return carsShown;

    }
    
    
    public List<CarDto> socars()//get all sold cars
    {
        List<CarDto> carsaq = carDao.getAllCars();

        List<CarDto> carsShown = new ArrayList();

        for (CarDto cartemp : carsaq) {
            if (cartemp.isSold() == true) {
                carsShown.add(cartemp);
            }
        }
        
      return carsShown;

    }
      
        
 //--------------------------------------------------------------------------------         
       

    @GetMapping("/")
    public String displayfeatured(Model model) {
        List<CarDto> carsaq = avcars();
        List<CarDto> carsShown = new ArrayList();

        for (CarDto cartemp : carsaq) {
            if (cartemp.isFeatured()) {
                carsShown.add(cartemp);
            }
        }

        model.addAttribute("cars", carsShown);
      
        return "index";
    }

    //--------------------------------------------------------------------------------  
    
    
    @GetMapping("inventory/new")
    public String displaynew(Model model) {
        List<CarDto> carsaq = avcars();
        List<CarDto> carsShown = new ArrayList();

        for (CarDto cartemp : carsaq) {
            if (cartemp.getMileage() <= 1000) {
                carsShown.add(cartemp);
            }
        }

        model.addAttribute("cars", carsShown);
        
        return "inventory/new.html";
    }

    @GetMapping("inventory/used")
    public String displayUsed(Model model) {
        List<CarDto> carsaq = avcars();
        List<CarDto> carsShown = new ArrayList();

        for (CarDto cartemp : carsaq) {
            if (cartemp.getMileage() > 1000) {
                carsShown.add(cartemp);
            }
        }

        model.addAttribute("cars", carsShown);
       
        return "inventory/used.html";
    }

    @GetMapping("details")//a weird page that shows the details of a list that I'm not sure works
    public String carDetails(CarDto carEather, HttpServletRequest request, Model model) {

        String carID = request.getParameter("carID");

        carEather = carDao.getCarById(Integer.parseInt(carID));

        model.addAttribute("car", carEather);

        return "redirect:/details";
    }
    
    
    
   //--------------------------------------------------------------------------------   
    @GetMapping("specials")//page with specials
    public String specials (Model model) {
        
        List<SpecialDto> specials = specialDao.getAllSpecials();
        
        model.addAttribute("specials", specials);


        return "special/specials";
    }
    
    
    
    //--------------------------------------------------------------------------------  contacts
    
    
    @GetMapping("contact")//page to add a contact 
    public String contact (Model model) {
        
        List<ContactDto> listcontacts = contactdao.getAllContacts();
        model.addAttribute("Contacts", listcontacts);//this is only useful to check if a contact already exists not fully necessary


        return "contact/contact.html";
    }
    
    
    
    @PostMapping("addcontact")//posting the information for the contact 
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


        return "redirect:contact/contact.html";
    }
    
    
    
    //-------------------------------------------------------------------------
   
    
    @GetMapping("sales/index")//show all cars to purchase
    public String showsales(Model model) {
        List<CarDto> carsav = avcars();
       


        model.addAttribute("cars", carsav);
     
        return "redirect:/sales/sales.html";
    }
    
    
    @GetMapping("sales/purchase")//show purchase page
    public String showsale(CarDto car, HttpServletRequest request, Model model) {
        
        String carID = request.getParameter("CarID");
         
        return "redirect:/sales/purchase.html";
    }
    
    
    
    @PostMapping("sales/purchase")//do a purchase request or "post"
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
    

    
  //--------------------------------------------------------------------------------  
    
    
    @GetMapping("admin/vehicles")//show vehicles for admin to change
    public String showvehiclestoedit(CarDto car, HttpServletRequest request, Model model) {
        
        List<CarDto> carsav = avcars();
        
        model.addAttribute("cars", carsav);
         
        return "admin/vehicles.html";
    }  
    
    @GetMapping("admin/addVehicle")
    public String addVehicle(){
        return "admin/addVehicle.html";
    }
    
    @GetMapping("admin/editvehicle")//show a certain car page for admin to edit
    public String editvehicle(CarDto car, HttpServletRequest request, Model model) {
        
        String carID = request.getParameter("carID");
        
        CarDto tempcar = carDao.getCarById(Integer.parseInt(carID));
        
        model.addAttribute("cars", tempcar);
         
        return "redirect:/admin/editVehicle.html";
    }     
    
    
    @PostMapping("admin/editvehicle")//make those edit changes
    public String posteditvehicle(CarDto car, HttpServletRequest request, Model model) {
        
        int carID = Integer.parseInt(request.getParameter("year"));
        
        String make = request.getParameter("make");
        
        String carmodel = request.getParameter("carmodel");
        
        String Type = request.getParameter("Type");
        
        String bodyStyle = request.getParameter("bodyStyle");
        
        int year = Integer.parseInt(request.getParameter("year"));
        
        String transmission = request.getParameter("transmission");
        
        String color = request.getParameter("color");//exterior color
        
        String interiorcolor = request.getParameter("interiorcolor");
        
        int mileage =Integer.parseInt(request.getParameter("Mileage"));
        
        String VIN = request.getParameter("VIN");
        
        int MSRP =Integer.parseInt(request.getParameter("MSRP"));
        
        int salePrice = Integer.parseInt(request.getParameter("saleprice"));
        
        car.setCarId(carID);
        car.setMakeName(make);//add all the info to the object
        car.setModelName(carmodel);
        car.setType(Type);
        car.setBodyStyle(bodyStyle);
        car.setYear(year);
        car.setTransmission(transmission);
        car.setExteriorColor(color);
        car.setInteriorColor(interiorcolor);
        car.setMileage(mileage);
        car.setVinNum(VIN);
        car.setMsrp(MSRP);
        car.setSalePrice(salePrice);
        
        carDao.addCar(car);
        
        model.addAttribute("car", car);
         
        return "redirect:/admin/editVehicle.html";
    }  
    
    
    @GetMapping("admin/addvehicle")//page to add a new vehicle
    public String addvehicle(CarDto car, HttpServletRequest request, Model model) {
        
        
     
    return "redirect:/admin/addVehicle.html";
    }     
    
    
    @PostMapping("admin/addvehicle")//page to send those changes
    public String postaddvehicle(CarDto car, HttpServletRequest request, Model model) {
        
        
        String make = request.getParameter("make");
        
        String carmodel = request.getParameter("carmodel");
        
        String Type = request.getParameter("Type");
        
        String bodyStyle = request.getParameter("bodyStyle");
        
        int year = Integer.parseInt(request.getParameter("year"));
        
        String transmission = request.getParameter("transmission");
        
        String color = request.getParameter("color");//exterior color
        
        String interiorcolor = request.getParameter("interiorcolor");
        
        int mileage =Integer.parseInt(request.getParameter("Mileage"));
        
        String VIN = request.getParameter("VIN");
        
        int MSRP =Integer.parseInt(request.getParameter("MSRP"));
        
        int salePrice = Integer.parseInt(request.getParameter("saleprice"));
        
        car.setMakeName(make);//add all the info to the object
        car.setModelName(carmodel);
        car.setType(Type);
        car.setBodyStyle(bodyStyle);
        car.setYear(year);
        car.setTransmission(transmission);
        car.setExteriorColor(color);
        car.setInteriorColor(interiorcolor);
        car.setMileage(mileage);
        car.setVinNum(VIN);
        car.setMsrp(MSRP);
        car.setSalePrice(salePrice);
        
        carDao.addCar(car);
        
        model.addAttribute("car", car);
         
        return "redirect:/admin/addVehicle.html";
    }  
    
//--------------------------------------------------------------------------------      
    
    
    
    
    
}
