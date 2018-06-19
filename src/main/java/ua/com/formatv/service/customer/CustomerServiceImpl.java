package ua.com.formatv.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.formatv.dao.CustomerDAO;
import ua.com.formatv.entity.Customer;

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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDAO.findCustomerByUsername(username);
    }




}
