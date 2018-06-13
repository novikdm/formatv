package ua.com.formatv.service.customer;

import ua.com.formatv.entity.CustomerDetails;

public interface CustomerDetailsService  {

    void save(CustomerDetails customerDetails);
    CustomerDetails find(int id);

}
