package ua.com.formatv.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.formatv.dao.CustomerDAO;
import ua.com.formatv.dao.CustomerDetailsDAO;
import ua.com.formatv.entity.Customer;
import ua.com.formatv.entity.CustomerDetails;
import ua.com.formatv.service.customer.CustomerDetailsService;
import ua.com.formatv.service.customer.CustomerService;

import java.io.File;
import java.io.IOException;
import java.util.Date;


@Controller
public class AdministrationController {


    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    CustomerDetailsDAO customerDetailsDAO;

    @Autowired
    CustomerDetailsService customerDetailsService;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String userInfo(@PathVariable int userId, Model model, ModelMap modelMap){
        model.addAttribute("user", customerDAO.findCustomerById(userId));
        if(!model.containsAttribute("userID")){
            model.addAttribute("userID", userId);
        }
        if(!model.containsAttribute("message")){
            model.addAttribute("message", "Message Err");
        }


        return "userInfo";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
    public String userInfo2(
            @PathVariable int userId,
            @RequestParam("oldPassword") String oldPassword,
            String newPassword,
            int userPrincipalId,
            Model model){
        if(userPrincipalId == userId){
            Customer customer = customerDAO.findCustomerById(userId);
            model.addAttribute("user", customer);
            model.addAttribute("userID", userId);
            if(encoder.matches(oldPassword, customer.getPassword())){
                customer.setPassword(encoder.encode(newPassword));
                customerDAO.save(customer);
                String message = "Password changing";
                model.addAttribute("message", message);
            }
            else{
                String message = "Old password wrong";
                model.addAttribute("message", message);
            }
        }
        return "userInfo";
    }


    @RequestMapping(value = "/user/{userId}/changeAvatar", method = RequestMethod.POST)
    public String changeAvatar(@PathVariable int userId,
                               @RequestParam("avatarImg") MultipartFile file,
                               Model model) throws IOException {

        CustomerDetails customerDetails =
                customerDetailsService.updateAvatar(
                        customerDAO.findCustomerById(userId).getCustomerDetails(),
                        file);

        customerDetailsService.save(customerDetails);


        return "redirect:/user/"+userId;
    }


}
