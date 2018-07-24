package ua.com.formatv.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.formatv.dao.CustomerDAO;
import ua.com.formatv.entity.Customer;
import ua.com.formatv.service.customer.CustomerService;


@Controller
public class AdministrationController {


    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    PasswordEncoder encoder;



    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String userInfo(@PathVariable int userId, Model model){
        model.addAttribute("user", customerDAO.findCustomerById(userId));
        model.addAttribute("userID", userId);

        return "userInfo";
    }

    @PostMapping("/changePassword")
    public String changePassword(
            @RequestParam("oldPassword") String oldPassword,
            String newPassword,
            int userId,
            Model model)

    {
        Customer customer = customerDAO.findCustomerById(userId);
        model.addAttribute("user", customer);


        if(encoder.matches(oldPassword, customer.getPassword())){
            customer.setPassword(encoder.encode(newPassword));
            customerDAO.save(customer);
            model.addAttribute("message", "Password changing");
        }
        else{
            model.addAttribute("message", "Old password wrong");
        }
        model.addAttribute("userID", userId);

        return "redirect:/user/"+userId;
    }

}
