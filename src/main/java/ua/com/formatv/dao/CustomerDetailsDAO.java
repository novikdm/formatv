package ua.com.formatv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.formatv.entity.CustomerDetails;

public interface CustomerDetailsDAO extends JpaRepository<CustomerDetails, Integer> {

        CustomerDetails findById(int id);

}
