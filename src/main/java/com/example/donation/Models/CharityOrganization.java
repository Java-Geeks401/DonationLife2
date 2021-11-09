package com.example.donation.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class CharityOrganization implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String email;
    private String facebookPage;
    private String number;
    private String address;
    private String authority = "ROLE_CHARITY";
    // relation with donation
    @OneToMany(mappedBy = "charityItems")
    private List<Donation> charityList;
    @OneToMany(mappedBy="charityOrganization")
    private List<DonationReq> Request;



    public CharityOrganization() {
    }

    public CharityOrganization(String username,
                               String password,
                               String name,
                               String email,
                               String facebookPage,
                               String number,
                               String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.facebookPage = facebookPage;
        this.number = number;
        this.address = address;
    }

    public List<Donation> getCharityList() {
        return charityList;
    }

    public void setCharityList(List<Donation> charityList) {
        this.charityList = charityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CharityOrganization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", facebookPage='" + facebookPage + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.authority);
        List<SimpleGrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(simpleGrantedAuthority);
        return userAuthorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<DonationReq> getRequest() {
        return Request;
    }

    public void setRequest(List<DonationReq> request) {
        Request = request;
    }
}