package com.example.donation.Repositories;

import com.example.donation.Models.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
