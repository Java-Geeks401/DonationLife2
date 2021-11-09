package com.example.donation.Repositories;

import com.example.donation.Models.CatalogItem;
import com.example.donation.Models.CharityOrganization;
import com.example.donation.Models.DonationReq;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestDonateRepository extends CrudRepository<DonationReq, Integer> {
    List<DonationReq> findAllByCharityOrganization(CharityOrganization charityOrganization);
    List<DonationReq> findAllByCatalogItem(CatalogItem catalogItem);
}
