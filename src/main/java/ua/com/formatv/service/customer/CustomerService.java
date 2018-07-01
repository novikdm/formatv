package ua.com.formatv.service.customer;



import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.formatv.entity.Customer;

import java.security.NoSuchAlgorithmException;

public interface CustomerService extends UserDetailsService {

    void save(Customer customer);
    void deleteCustomer(int id);
    String hashLink(Customer customer) throws NoSuchAlgorithmException;
}
