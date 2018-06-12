package ua.com.formatv.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.formatv.entity.Customer;

public interface CustomerService extends UserDetailsService {

    void save(Customer customer);
}
