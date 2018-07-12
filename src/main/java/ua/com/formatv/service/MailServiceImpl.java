package ua.com.formatv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.formatv.entity.Customer;
import ua.com.formatv.service.customer.CustomerService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@Service
public class MailServiceImpl implements MailService{


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    CustomerService customerService;

    public void sendActivationEmail(Customer customer) throws NoSuchAlgorithmException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String linkURL = customer.getActivationLink();
        String linkBody = "http://localhost:8080/activationAccount/"+ customer.getId() + "/" + linkURL;
        System.out.println("--------------");
        System.out.println(linkBody);


        try {
            helper.setTo(customer.getUsername());
            helper.setText("<h3>Hello dear user!</h3> You are registered on service FormaTV.<br> Click to next link for activation account: <a href=\"" + linkBody + "\">Activation</a>", true);
            helper.setFrom("dmitro.formatv@gmail.com");
            helper.setSubject("FormaTV. You are registered! Please confirm your email-address.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);

    }
}
