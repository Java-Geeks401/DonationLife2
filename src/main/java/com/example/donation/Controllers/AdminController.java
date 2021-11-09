package com.example.donation.Controllers;


import com.example.donation.Models.*;
import com.example.donation.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    CatalogItemRepository catalogItemRepository;

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    CharityOrganizationRepositorie charityOrganizationRepositorie;

    @Autowired
    PasswordEncoder passwordEncoder;

    //add new admin
    @GetMapping("/addadmin")
    public void addNewAmin(){
        Admin admin=new Admin("mohmad",passwordEncoder.encode("12345"),"mohmud","ali","jpj");
        adminRepository.save(admin);
    }

    @GetMapping("/dashbord")
    public String getAdminPanel(Model model, Principal p) {
        Admin admin = adminRepository.findByUsername(p.getName());
        List<Catalog> catalogs= (List<Catalog>) catalogRepository.findAll();
        List<CatalogItem> items= (List<CatalogItem>) catalogItemRepository.findAll();
        List<Donator> donators= (List<Donator>) donorRepository.findAll();
        Iterable<CharityOrganization> organizations=  charityOrganizationRepositorie.findAll();
        model.addAttribute("adminData", admin);
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("items", items);
        model.addAttribute("donaters", donators);
        model.addAttribute("organizations", organizations);
        return "dashboard";
    }

    @PostMapping("/admin/addCatalog")
    public RedirectView addCatalog( @RequestParam(value = "name") String name){
        try {
            Catalog catalog = new Catalog(name);
            catalogRepository.save(catalog);
            return new RedirectView("/dashbord");
        }catch (Exception e){
            return new RedirectView("/error");
        }
    }

    @PostMapping("/admin/addItem")
    public RedirectView addPost( int catalogId ,String itemName){
        try {
            Catalog catalog =catalogRepository.findById(catalogId).get();
            CatalogItem catalogItem = new CatalogItem(itemName,catalog);
            catalogItemRepository.save(catalogItem);
            return new RedirectView("/dashbord");
        }catch (Exception e){
            return new RedirectView( "/error");
        }
    }

    @RequestMapping("/admin/deletecatalog/{id}")
    public RedirectView deleteCatalog(@PathVariable int id) {
        try {
            if (catalogRepository.findById(id).get().getItemsList().isEmpty()) {
                catalogRepository.deleteById(id);
                return new RedirectView( "/dashbord");
            }else {
                return new RedirectView("/error");
            }
        }catch (Exception e){
            return new RedirectView("/error");
        }
    }

    @RequestMapping("/admin/deleteItem/{id}")
    public RedirectView deleteItem(@PathVariable int id) {
        try {
            catalogItemRepository.deleteById(id);
            return new RedirectView( "/dashbord");
        }catch (Exception e){
            return new RedirectView("/error");
        }
    }

    @RequestMapping("/admin/deletedonator/{id}")
    public RedirectView delete(@PathVariable int id) {
        try {
            donorRepository.deleteById(id);
            return new RedirectView( "/dashbord");
        }catch (Exception e){
            return new RedirectView("/error");
        }
    }

    @RequestMapping("/admin/deletecharityorg/{id}")
    public RedirectView deleteCharityOrgnization(@PathVariable int id) {
        try {
            charityOrganizationRepositorie.deleteById(id);
            return new RedirectView( "/dashbord");
        }catch (Exception e){
            return new RedirectView("/error");
        }
    }

}