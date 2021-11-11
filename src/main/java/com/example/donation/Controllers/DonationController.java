package com.example.donation.Controllers;


import com.example.donation.Models.*;
import com.example.donation.Repositories.*;
import com.example.donation.Services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.List;

@Controller
public class DonationController {
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    CharityOrganizationRepositorie charityOrganizationRepositorie;
    @Autowired
    RequestDonateRepository requestDonateRepository;
    @Autowired
    CatalogItemRepository catalogItemRepository;
    @Autowired
    CatalogRepository catalogRepository;
    @Autowired
    private EmailSenderService service;

    @PostMapping ("/addDonation")//add as donator
    public RedirectView addDonation(String description, String status, int id, Principal principal){
        Donator donator=donorRepository.findByUsername(principal.getName());
        CatalogItem donationItem=catalogItemRepository.findById(id).get();
//        List<DonationReq> donationReq= requestDonateRepository.findAllByCatalogItem(donationItem);
        Donation donation= new Donation( description,donationItem,donator);
//        donation.getDonationsItems().getDonationReqList()
        donationRepository.save(donation);
     return new RedirectView("/myPage");
    }

    @GetMapping("/pageOfDonation")
    public String pageOfDonation(Model model, Principal principal) {
        Donator donator = donorRepository.findByUsername(principal.getName());
        List<Donation> donationList = donationRepository.findAllByDonatorItems(donator);
        model.addAttribute("donationList", donationList);
        return "showDonation";
    }
// for charities form .
    @GetMapping("/openform")

    public String openForm(Model m){
        List<Catalog> catalog= (List<Catalog>) catalogRepository.findAll();
        m.addAttribute("catList",catalog);
        return "addReqDonation1";
    }


    @GetMapping("/donationForm/{id}")
    public String getDonation(Model model, @PathVariable int id){
        CatalogItem catalogItem=catalogItemRepository.findById(id).get();
        model.addAttribute("itemId",catalogItem);
        List<Catalog> catalog= (List<Catalog>) catalogRepository.findAll();
        model.addAttribute("catList",catalog);

        return "addDonation1";
    }

    // for req donation from charities
    @PostMapping("/RequestDonationForm")
    public RedirectView addingRequest(String description, Principal principal,  int id) {
        CharityOrganization charityOrganization=charityOrganizationRepositorie.findByUsername(principal.getName());
        CatalogItem donationItem=catalogItemRepository.findById(id).get();
        DonationReq requestDonate=new DonationReq(description,charityOrganization,donationItem);
        requestDonateRepository.save(requestDonate);
        return new RedirectView("/charityRequests");
    }


    @GetMapping("/charityRequests")
    public String charityRequests(Model model, Principal principal) {
        CharityOrganization charityOrganization = charityOrganizationRepositorie.findByUsername(principal.getName());
        List<DonationReq> requestDonates = requestDonateRepository.findAllByCharityOrganization(charityOrganization);
        model.addAttribute("requestDonates", requestDonates);
        List<Catalog> catalog= (List<Catalog>) catalogRepository.findAll();
        model.addAttribute("catList",catalog);
        return "charityPage";
    }
//    @PostMapping ("/finishDonation")//finish the donation process
//    public RedirectView finishDonation(int idForDonation,int idForDonationReq,Principal principal){
//        Donator donator=donorRepository.findByUsername(principal.getName());
//        Donation donationFinished=donationRepository.findById(idForDonation).get();
//        DonationReq donationReqFinished=requestDonateRepository.findById(idForDonationReq).get();
//        donationFinished.setStatus(false);
//        donationReqFinished.setStatus(false);
//        donationRepository.save(donationFinished);
//        requestDonateRepository.save(donationReqFinished);
//
//        return new RedirectView("/pageOfDonation");
//    }

    @PostMapping ("/finishDonation")//finish the donation process
    public RedirectView finishDonation(int idForDonation,int idForDonationReq,Principal principal) throws MessagingException {
        Donator donator=donorRepository.findByUsername(principal.getName());
        Donation donationFinished=donationRepository.findById(idForDonation).get();
        DonationReq donationReqFinished=requestDonateRepository.findById(idForDonationReq).get();
        donationFinished.setStatus(false);
        donationReqFinished.setStatus(false);
        triggerDonaterMail(donator,donationReqFinished.getCharityOrganization());
        triggerCharityMail(donationReqFinished.getCharityOrganization(),donator);
        donationRepository.save(donationFinished);
        requestDonateRepository.save(donationReqFinished);

        return new RedirectView("/pageOfDonation");
    }

    public void triggerDonaterMail(Donator donator,CharityOrganization charityOrganization) throws MessagingException {

        service.sendSimpleEmail(donator.getEmail(),
                "Thank you" +" "+ donator.getFirstName() +" "+ "very much for your Donation , you are really a great person, We will send you updates over the next few months so that you know exactly what is happening with your donation, and the impact that we have been able to create together.\n" +
                        "\n" +
                        "With all my gratitude" + " "+ charityOrganization.getName(),
                "Thanking message");

    }

    public void triggerCharityMail(CharityOrganization charityOrganization,Donator donator) throws MessagingException {

        service.sendSimpleEmail(charityOrganization.getEmail(),
                "  Dear " +" "+ charityOrganization.getName() +" "+ ":\n the donation is covered by " + donator.getFirstName() + donator.getLastName() + " with email " + donator.getEmail()
                        + " please contact him as soon as possible or tell us that you covered the donation.\n" +
                        "\n" +
                        "With all my gratitude ",
                "Reporting Message");

    }
}
