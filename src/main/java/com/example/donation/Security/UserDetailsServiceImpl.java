package com.example.donation.Security;

import com.example.donation.Models.Admin;
import com.example.donation.Models.CharityOrganization;
import com.example.donation.Models.Donator;
import com.example.donation.Repositories.AdminRepository;
import com.example.donation.Repositories.CharityOrganizationRepositorie;
import com.example.donation.Repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    CharityOrganizationRepositorie charityOrganizationRepositorie;

    @Autowired
    AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Donator donator = donorRepository.findByUsername(username);
        CharityOrganization charityOrganization=charityOrganizationRepositorie.findByUsername(username);
        Admin admin = adminRepository.findByUsername(username);
        if (donator == null && charityOrganization == null && admin == null) {
            throw new UsernameNotFoundException("Dose not exist");
        }else if(donator != null){
            return donator;
        }else if(charityOrganization != null){
            return charityOrganization;
        }else{
            return admin;
        }
    }

}
