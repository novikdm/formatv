package ua.com.formatv.service;

import ua.com.formatv.entity.Customer;

public interface MailService {
    void sendActivationEmail(Customer customer);
}
