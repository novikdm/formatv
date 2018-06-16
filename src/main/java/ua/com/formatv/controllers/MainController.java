package ua.com.formatv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.formatv.dao.CustomerDAO;
import ua.com.formatv.dao.CustomerDetailsDAO;
import ua.com.formatv.entity.Customer;
import ua.com.formatv.entity.CustomerDetails;
import ua.com.formatv.service.customer.CustomerService;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    CustomerDetailsDAO customerDetailsDAO;

    @GetMapping("/")
    public String index(Model model, Principal principal, Authentication authentication){
//        System.out.println(authentication);
//        System.out.println("-------");
//        System.out.println(authentication.getDetails());


        Customer user = customerDAO.findOne(4);
        CustomerDetails userCustomerDetails = user.getCustomerDetails();

        model.addAttribute("user", userCustomerDetails);

        return "index";
    }

    @GetMapping("/Registration")
    public String registrationPage(){
        return "registration";
    }


    @PostMapping("/RegisterNewUser")
    public String saveNewCastomer(Customer customer, CustomerDetails customerDetails){
        customer.setCustomerDetails(customerDetails);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));




        customerService.save(customer);

//        customerDAO.findCustomerByUsername(customer.getUsername()).setCustomerDetails(customerDetails);



        return "redirect:/";
    }

}
