package com.example.donation.Controllers;

import com.example.donation.Models.Catalog;
import com.example.donation.Repositories.CatalogRepository;
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

    @GetMapping("/")
    public String getHomePage(Model model, Principal principal){
       List<Catalog> catalog= (List<Catalog>) catalogRepository.findAll();
       model.addAttribute("catList",catalog);
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

}
