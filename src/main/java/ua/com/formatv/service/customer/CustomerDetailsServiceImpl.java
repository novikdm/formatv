package ua.com.formatv.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.formatv.dao.CustomerDetailsDAO;
import ua.com.formatv.entity.CustomerDetails;

public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    @Autowired
    CustomerDetailsDAO customerDetailsDAO;

    public void save(CustomerDetails customerDetails) {
        customerDetailsDAO.save(customerDetails);
    }

    public CustomerDetails find(int id) {
        return customerDetailsDAO.findById(id);
    }
}
