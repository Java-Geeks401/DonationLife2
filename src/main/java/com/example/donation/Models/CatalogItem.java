package com.example.donation.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class CatalogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    @ManyToOne
    Catalog catalogItems;
    // relation with the donation
    @OneToOne(targetEntity=Donation.class,cascade=CascadeType.ALL)
    private Donation donation;
    @OneToMany(mappedBy ="catalogItem")
    private List<DonationReq> donationReqList;

    public CatalogItem(){
    }
    public CatalogItem(String itemName, Catalog catalogItems) {
        this.itemName = itemName;
        this.catalogItems = catalogItems;
    }

    public List<DonationReq> getDonationReqList() {
        return donationReqList;
    }

    public void setDonationReqList(List<DonationReq> donationReqList) {
        this.donationReqList = donationReqList;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Catalog getCatalogItems() {
        return catalogItems;
    }

    public void setCatalogItems(Catalog catalogItems) {
        this.catalogItems = catalogItems;
    }
}
