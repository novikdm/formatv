package ua.com.formatv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.formatv.entity.Customer;
import ua.com.formatv.service.CustomerService;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model, Principal principal, Authentication authentication){
//        System.out.println(authentication);
//        System.out.println("-------");
//        System.out.println(authentication.getDetails());


        Customer user = new Customer();
        user.setUsername("MAMAMIA");

        model.addAttribute("user", user);

        return "index";
    }

    @GetMapping("/Registration")
    public String registrationPage(){
        return "registration";
    }


    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerService customerService;

    @PostMapping("/RegisterNewUser")
    public String saveNewCastomer(Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        customerService.save(customer);


        return "redirect:/";
    }

}
