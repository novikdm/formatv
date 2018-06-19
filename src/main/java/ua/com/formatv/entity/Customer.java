package ua.com.formatv.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private FormaTRole role=FormaTRole.ROLE_USER;
    private boolean accNonExp = true;
    private boolean accNonLock = true;
    private boolean credNonExp = true;
    private boolean enable = true;
    private String activationToken = null;

    @OneToOne(

            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private CustomerDetails customerDetails;


    public Customer() {
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return accNonExp;
    }

    public boolean isAccountNonLocked() {
        return accNonLock;
    }

    public boolean isCredentialsNonExpired() {
        return credNonExp;
    }

    public boolean isEnabled() {
        return enable;
    }

    public CustomerDetails getCustomerDetails() { return customerDetails; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(FormaTRole role) {
        this.role = role;
    }

    public void setAccNonExp(boolean accNonExp) {
        this.accNonExp = accNonExp;
    }

    public void setAccNonLock(boolean accNonLock) {
        this.accNonLock = accNonLock;
    }

    public void setCredNonExp(boolean credNonExp) {
        this.credNonExp = credNonExp;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) { this.customerDetails = customerDetails; }

    public String getActivationToken() {return activationToken;}

    public void setActivationToken(String activationToken) {this.activationToken = activationToken;}
}
