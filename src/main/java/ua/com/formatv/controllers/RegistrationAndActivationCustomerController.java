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
public class RegistrationAndActivationCustomerController {

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

    //    Registration new User(customer) of our site
    @PostMapping("/RegisterNewUser")
    public String saveNewCastomer(Customer customer, CustomerDetails customerDetails, @RequestParam("avatarpic")MultipartFile file) throws IOException {
        customer.setCustomerDetails(customerDetails);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        if (file != null){
            int prefix = (int) (Math.random() * 10000);
            String sufix = "";
            char[] fileNameChars = file.getOriginalFilename().toCharArray();
            for(int i =fileNameChars.length-1; i>0; i--){
                if(fileNameChars[i] != '.'){
                    sufix += fileNameChars[i];
                }
                else {
                    sufix += ".";
                    break;
                }
            }
            sufix = new StringBuffer(sufix).reverse().toString();
            String fileName = new String(prefix + "_" + new Date().getTime()  + sufix);

            file.transferTo(new File(
                    System.getProperty("user.home")
                            + File.separator
                            + "avatars"
                            + File.separator
                            + fileName
            ));
            customerDetails.setAvatar("/prefixAvatar/"+fileName);
        }
        try {
            customer.setActivationLink(customerService.hashLink(customer));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        customerService.save(customer);
        System.out.println("TimeStamp in RegisterNewUser: " + customer.getTimestamp());
        try {
            mailService.sendActivationEmail(customer);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    //Activation of Account
    @RequestMapping(value = "/activationAccount/{userId}/{userHash}", method = RequestMethod.GET)
    public String activationAccount(@PathVariable("userId") String id, @PathVariable("userHash") String hash) throws NoSuchAlgorithmException {
        long currentDate = new Date().getTime();
        Customer customer = customerDAO.findCustomerById(Integer.parseInt(id));
        String linkUrl = customer.getActivationLink();
        if(linkUrl.equals(hash) && (currentDate - customer.getTimestamp().getTime())<605000000) {
            customer.setEnable(true);
            customer.setActivationLink(null);
            customerDAO.save(customer);
        }
        else System.out.println("SOMTHING BAD in activation Accaunt");
        System.out.println("Timestamp in activation: " + customer.getTimestamp());


        return "redirect:/";
    }
}
