package com.example.donation.Repositories;


import com.example.donation.Models.CharityOrganization;
import org.springframework.data.repository.CrudRepository;

public interface CharityOrganizationRepositorie extends CrudRepository<CharityOrganization,Integer> {
    CharityOrganization findByUsername(String name);
}
