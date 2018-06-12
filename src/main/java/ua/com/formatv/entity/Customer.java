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


    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;
    public String getUsername() {
        return username;
    }

    private String password;
    public String getPassword() {
        return password;
    }

    @Enumerated(EnumType.STRING)
    private FormaTRole role=FormaTRole.ROLE_USER;
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    private boolean accNonExp = true;
    public boolean isAccountNonExpired() {
        return accNonExp;
    }

    private boolean accNonLock = true;
    public boolean isAccountNonLocked() {
        return accNonLock;
    }

    private boolean credNonExp = true;
    public boolean isCredentialsNonExpired() {
        return credNonExp;
    }

    private boolean enable = true;
    public boolean isEnabled() {
        return enable;
    }

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


}
