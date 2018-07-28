package ua.com.formatv.service.customer;

import org.springframework.web.multipart.MultipartFile;
import ua.com.formatv.entity.CustomerDetails;

import java.io.IOException;

public interface CustomerDetailsService  {

    void save(CustomerDetails customerDetails);
    CustomerDetails find(int id);
    CustomerDetails updateAvatar(CustomerDetails customerDetails, MultipartFile file) throws IOException;

}
