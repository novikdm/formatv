package ua.com.formatv.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.formatv.dao.CustomerDetailsDAO;
import ua.com.formatv.entity.CustomerDetails;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
@Transactional
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    @Autowired
    CustomerDetailsDAO customerDetailsDAO;

    public void save(CustomerDetails customerDetails) {
        customerDetailsDAO.save(customerDetails);
    }

    public CustomerDetails find(int id) {
        return customerDetailsDAO.findById(id);
    }

    @Override
    public CustomerDetails updateAvatar(CustomerDetails customerDetails, MultipartFile file) throws IOException {
        if (file != null){
            int prefix = (int) (Math.random() * 10000);
            String sufix = "";
            char[] fileNameChars = file.getOriginalFilename().toCharArray();
            for(int i =fileNameChars.length-1; i>0; i--){
                if(fileNameChars[i] != '.'){
                    sufix += fileNameChars[i];
                }
                else {
                    sufix += ".";
                    break;
                }
            }
            sufix = new StringBuffer(sufix).reverse().toString();
            String fileName = new String(prefix + "_" + new Date().getTime()  + sufix);

            file.transferTo(new File(
                    System.getProperty("user.home")
                            + File.separator
                            + "avatars"
                            + File.separator
                            + fileName
            ));
            customerDetails.setAvatar("/prefixAvatar/"+fileName);
        }
        return customerDetails;
    }
}
