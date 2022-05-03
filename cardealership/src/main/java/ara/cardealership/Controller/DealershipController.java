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
        //model.addAttribute("teachers", employees);
        // model.addAttribute("students", sales);
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
        //model.addAttribute("teachers", employees);
        // model.addAttribute("students", sales);
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
        //model.addAttribute("teachers", employees);
        // model.addAttribute("students", sales);
        return "inventory/used.html";
    }

    @PostMapping("details")
    public String carDetails(CarDto carEather, HttpServletRequest request, Model model) {

        String carID = request.getParameter("carId");

        carEather = carDao.getCarById(Integer.parseInt(carID));

        model.addAttribute("car", carEather);

        return "redirect:/details";
    }
}
