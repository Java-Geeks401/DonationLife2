package com.example.donation.Repositories;


import com.example.donation.Models.CatalogItem;
import org.springframework.data.repository.CrudRepository;

public interface CatalogItemRepository extends CrudRepository<CatalogItem,Integer> {
}
