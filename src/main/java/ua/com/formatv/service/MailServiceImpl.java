package ua.com.formatv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.formatv.entity.Customer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService{


    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationEmail(Customer customer) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setTo(customer.getUsername());
            helper.setText("<h3>Hello dear user!</h3> You are registered on service FormaTV.<br> Click to next link for activation accaun: <a href = 'localhost:8080/'> Activation </a>", true);
            helper.setFrom("novikdmitry.c.h@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);

    }
}
