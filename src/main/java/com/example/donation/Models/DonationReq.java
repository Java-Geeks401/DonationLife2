package com.example.donation.Models;

import javax.persistence.*;


@Entity
public class DonationReq {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String description;
        private boolean status=true;
        @ManyToOne
        private CharityOrganization charityOrganization;
        @ManyToOne
        private Donation donation;
        @ManyToOne
        private CatalogItem catalogItem;


public DonationReq(){

}

    public DonationReq(String discription,CharityOrganization charityOrganization,CatalogItem catalogItem) {
            this.description = discription;
            this.charityOrganization=charityOrganization;
            this.catalogItem=catalogItem;
        }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public CatalogItem getCatalogItem() {
        return catalogItem;
    }

    public void setCatalogItem(CatalogItem catalogItem) {
        this.catalogItem = catalogItem;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDiscription() {
            return description;
        }

        public void setDiscription(String discription) {
            this.description = discription;
        }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CharityOrganization getCharityOrganization() {
        return charityOrganization;
    }

    public void setCharityOrganization(CharityOrganization charityOrganization) {
        this.charityOrganization = charityOrganization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

