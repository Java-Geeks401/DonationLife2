package com.example.donation.Repositories;

import com.example.donation.Models.Donation;
import com.example.donation.Models.Donator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DonationRepository  extends CrudRepository<Donation,Integer> {
    List<Donation> findAllByDonatorItems(Donator donatorItems);

}
