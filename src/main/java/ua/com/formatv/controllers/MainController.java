package ua.com.formatv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.com.formatv.dao.CustomerDAO;
import ua.com.formatv.dao.CustomerDetailsDAO;
import ua.com.formatv.entity.Customer;
import ua.com.formatv.entity.CustomerDetails;
import ua.com.formatv.service.MailService;
import ua.com.formatv.service.customer.CustomerService;

import java.io.File;
import java.io.IOException;
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
    @Autowired
    MailService mailService;

    @GetMapping("/")
    public String index(Model model, Principal principal, Authentication authentication){
//        System.out.println(authentication.getDetails());

        return "index";
    }

    @GetMapping("/Registration")
    public String registrationPage(){
        return "registration";
    }


    @PostMapping("/RegisterNewUser")
    public String saveNewCastomer(Customer customer, CustomerDetails customerDetails, @RequestParam("avatarpic")MultipartFile file){
        customer.setCustomerDetails(customerDetails);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        try {
            file.transferTo(new File(
                    System.getProperty("user.home")
                            + File.separator +
                            "pics"
                            + File.separator +
                            file.getOriginalFilename()
            ));
        } catch (IOException e) {
            System.out.println("Somthing bad in saving avatar");
            e.printStackTrace();
        }
        customerDetails.setAvatar("/prefixAvatar/"+file.getOriginalFilename());


        customerService.save(customer);
        mailService.sendActivationEmail(customer);

        return "redirect:/";
    }

}
