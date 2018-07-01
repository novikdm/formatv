package ua.com.formatv.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.formatv.dao.CustomerDAO;
import ua.com.formatv.entity.Customer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDAO customerDAO;


    public void save(Customer customer) {

        customerDAO.save(customer);
    }

    public void deleteCustomer(int id) {
        customerDAO.delete(id);
    }

    @Override
    public String hashLink(Customer customer) throws NoSuchAlgorithmException {
        int randomnumber = (int)(Math.random()*100000000);
        String linkText = new String(customer.getId() + customer.getUsername() + randomnumber);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] linkArray = digest.digest(linkText.getBytes(StandardCharsets.UTF_8));
        String linkURL = "";
        for (byte c:
                linkArray) {
            linkURL+= c;
        }
        return linkURL;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDAO.findCustomerByUsername(username);
    }




}
