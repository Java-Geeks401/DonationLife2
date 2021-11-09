package com.example.donation.Repositories;

import com.example.donation.Models.Donator;
import org.springframework.data.repository.CrudRepository;

public interface DonorRepository extends CrudRepository<Donator,Integer> {

    Donator findByUsername(String username);
}
