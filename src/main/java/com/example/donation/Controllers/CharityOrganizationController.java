package com.example.donation.Controllers;


import com.example.donation.Models.CharityOrganization;
import com.example.donation.Repositories.CharityOrganizationRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class CharityOrganizationController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CharityOrganizationRepositorie charityOrganizationRepositorie;

    @GetMapping("/charityProfile")
    public String getCharityProfilePage(Model model, Principal principal){
        CharityOrganization charityOrganization = charityOrganizationRepositorie.findByUsername(principal.getName());
        model.addAttribute("CharityOrganization",charityOrganization);
        return "charityProfile";
    }
    @GetMapping("/CharityOrganization")
    public String getSignUpPage() {
        return "CharityOrganization";
    }

    @PostMapping("/charityOrganizationForm")
    public RedirectView postCharityOrganization(  String username,String password, String name,
                                               String email,  String facebook,String number,String address){
        CharityOrganization charityOrganization=new CharityOrganization(username,passwordEncoder.encode(password),name,email,facebook,number,address);
        charityOrganizationRepositorie.save(charityOrganization);
        Authentication authentication = new UsernamePasswordAuthenticationToken(charityOrganization, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");
    }
}