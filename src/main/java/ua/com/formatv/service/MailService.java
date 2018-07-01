package ua.com.formatv.service;

import ua.com.formatv.entity.Customer;

import java.security.NoSuchAlgorithmException;

public interface MailService {
    void sendActivationEmail(Customer customer) throws NoSuchAlgorithmException;
}
