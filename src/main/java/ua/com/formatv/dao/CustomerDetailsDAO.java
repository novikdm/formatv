package ua.com.formatv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import ua.com.formatv.entity.CustomerDetails;

public interface CustomerDetailsDAO extends JpaRepository<CustomerDetails, Integer> {

        CustomerDetails findById(int id);
}
