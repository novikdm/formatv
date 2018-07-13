package ua.com.formatv.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.formatv.dao.CustomerDAO;
import ua.com.formatv.dao.CustomerDetailsDAO;
import ua.com.formatv.entity.Customer;
import ua.com.formatv.entity.CustomerDetails;
import ua.com.formatv.service.MailService;
import ua.com.formatv.service.customer.CustomerService;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


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
    public String index(){
        return "index";
    }

    @GetMapping("/Registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/signedIn")
    public String logined(){
        return "redirect:/";
    }

    @PostMapping("/failureLogIn")
    public String failureLogIn(){
        return "failureLogIn";
    }









//    Testing method
    @GetMapping("/testing")
    public String testing(@RequestParam("ava")MultipartFile file) throws IOException {
        Customer customer = customerDAO.findCustomerById(33);
        System.out.println("test");
        return "redirect;/";

    }




}
