package ua.com.formatv.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.File;
import java.util.Properties;


@Configuration
@EnableWebMvc
@ComponentScan("ua.com.formatv.*")
public class WebConfig extends WebMvcConfigurerAdapter {



    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");

        return resolver;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        Hendler for Styles(CSS)
        registry.addResourceHandler("/style/**").
                addResourceLocations("/static/styles/");

//      Hendler for Avatars pics
        registry.addResourceHandler("/prefixAvatar/**").
                addResourceLocations("file:" + System.getProperty("user.home") + File.separator
                + "avatars" + File.separator);
    }


    @Bean
    public JavaMailSenderImpl MailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("novikdmitry.c.h@gmail.com");
        mailSender.setPassword("");

        Properties javaMailProperties = mailSender.getJavaMailProperties();

        javaMailProperties.setProperty("mail.transport.protocol", "smtp");
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        javaMailProperties.setProperty("mail.debug", "true");

        return mailSender;

    }


}
