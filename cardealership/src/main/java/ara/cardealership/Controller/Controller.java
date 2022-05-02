/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ara.cardealership.Controller;

import ara.cardealership.dao.EmployeeDao;
import ara.cardealership.dto.CarDto;
import ara.cardealership.dto.EmployeeDto;
import ara.cardealership.dto.SaleDto;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author alexg
 */
@Controller
public class Controller {
    
    
    @Autowired
    CarDao carDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    SaleDao saleDao;

    @GetMapping("index")
    public String displayfeatured(Model model) {
        List<CarDto> carsaq = carDao.getAllcars();
        List<CarDto> carsShown = new ArrayList();
        
        for(CarDto cartemp : carsaq)
        {
            if (cartemp.getfeatured())
            {
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
        List<CarDto> carsaq = carDao.getAllcars();
        List<CarDto> carsShown = new ArrayList();
        
        for(CarDto cartemp : carsaq)
        {
            if (cartemp.getMileage()<=1000)
            {
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
        List<CarDto> carsaq = carDao.getAllcars();
        List<CarDto> carsShown = new ArrayList();
        
        for(CarDto cartemp : carsaq)
        {
            if (cartemp.getMileage()>1000)
            {
                carsShown.add(cartemp);
            }
        }
        
        model.addAttribute("cars", carsShown);
        //model.addAttribute("teachers", employees);
        // model.addAttribute("students", sales);
        return "redirect:/inventory/used.html";
    }    
    
    
    
    
    @PostMapping("details")
    public String carDetails(CarDto carEather, HttpServletRequest request, Model model) {
        
        String carID = request.getParameter("carId");
        
        carEather = carDao.get(Integer.parseInt(carID));
        
        model.addAttribute("car", carEather);


        return "redirect:/details";
    }
}
