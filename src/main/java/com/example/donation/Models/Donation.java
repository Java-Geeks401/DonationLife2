package com.example.donation.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;
    private String description;
    private boolean status=true;
    // Relation with the static catalog item.
    @OneToOne(targetEntity=CatalogItem.class,cascade=CascadeType.ALL)
    private CatalogItem donationsItems;
    //Relation with the donator.
    @ManyToOne
    private Donator donatorItems;
    // Relation with the charity
    @ManyToOne
    private CharityOrganization charityItems;
    @OneToMany(mappedBy = "donation")
    private List<DonationReq> donationReqList;

    public Donation() {
    }

    public Donation(String description, CatalogItem donationsItems, Donator donatorItems){
        this.description = description;
        this.donationsItems = donationsItems;
        this.donatorItems = donatorItems;
    }

    public CharityOrganization getCharityItems() {
        return charityItems;
    }

    public void setCharityItems(CharityOrganization charityItems) {
        this.charityItems = charityItems;
    }

    public List<DonationReq> getDonationReqList() {
        return donationReqList;
    }

    public void setDonationReqList(List<DonationReq> donationReqList) {
        this.donationReqList = donationReqList;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CatalogItem getDonationsItems() {
        return donationsItems;
    }

    public void setDonationsItems(CatalogItem donationsItems) {
        this.donationsItems = donationsItems;
    }

    public Donator getDonatorItems() {
        return donatorItems;
    }

    public void setDonatorItems(Donator donatorItems) {
        this.donatorItems = donatorItems;
    }


}