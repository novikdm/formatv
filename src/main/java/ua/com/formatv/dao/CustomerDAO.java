package ua.com.formatv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.formatv.entity.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {

    Customer findCustomerByUsername(String username);


}
