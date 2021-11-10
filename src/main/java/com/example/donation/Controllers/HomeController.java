package com.example.donation.Controllers;

import com.example.donation.Models.Catalog;
import com.example.donation.Models.Donation;
import com.example.donation.Models.Donator;
import com.example.donation.Repositories.CatalogRepository;
import com.example.donation.Repositories.DonationRepository;
import com.example.donation.Repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CatalogRepository catalogRepository;
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    DonationRepository donationRepository;

    @GetMapping("/")
    public String getHomePage(Model model, Principal principal){
       List<Catalog> catalog= (List<Catalog>) catalogRepository.findAll();
       model.addAttribute("catList",catalog);
        return "index";
    }

    @GetMapping("/login")

    public String getLoginPage() {
        return "login1";
    }

    @GetMapping("/indexin")
    public String indexINp() {
        return "indexin";
    }

    @GetMapping("/myPage")

        public String pageOfDonation(Model model, Principal principal) {
            Donator donator = donorRepository.findByUsername(principal.getName());
            List<Donation> donationList = donationRepository.findAllByDonatorItems(donator);
            model.addAttribute("donationList", donationList);
        List<Catalog> catalog= (List<Catalog>) catalogRepository.findAll();
        model.addAttribute("catList",catalog);
        return "myPage";
    }

}
