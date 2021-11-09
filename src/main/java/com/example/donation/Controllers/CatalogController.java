package com.example.donation.Controllers;

import com.example.donation.Models.Catalog;
import com.example.donation.Models.CatalogItem;
import com.example.donation.Repositories.CatalogItemRepository;
import com.example.donation.Repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogController {

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    CatalogItemRepository catalogItemRepository;
//
//    @GetMapping("/addCat")
//        public String addCat(){
//        Catalog catalog = new Catalog("kitchen");
//        Catalog catalog2 = new Catalog("furniture");
//        catalogRepository.save(catalog);
//        catalogRepository.save(catalog2);
//        CatalogItem catalogItem = new CatalogItem("fridge",catalog);
//        CatalogItem catalogItem1 = new CatalogItem("microwave",catalog);
//        CatalogItem catalogItem2 = new CatalogItem("dishwasher",catalog);
//        CatalogItem catalogItem3 = new CatalogItem("washing machine",catalog);
//        catalogItemRepository.save(catalogItem);
//        catalogItemRepository.save(catalogItem1);
//        catalogItemRepository.save(catalogItem2);
//        catalogItemRepository.save(catalogItem3);
//        return "addedPAge";
//    }

//    @PostMapping("/admin/addCatalog")
//    public String addCatalog( @RequestParam(value = "name") String name){
//        try {
//            Catalog catalog = new Catalog(name);
//            catalogRepository.save(catalog);
//            return "index";
//        }catch (Exception e){
//            return "error";
//        }
//    }
//
//    @PostMapping("/admin/addItem")
//    public String addPost(@RequestParam(value = "catalogId") String catalogId , @RequestParam(value = "itemName") String itemName){
//        try {
//            Catalog catalog =catalogRepository.findById(Integer.valueOf(catalogId)).get();
//            CatalogItem catalogItem = new CatalogItem(itemName,catalog);
//            catalogItemRepository.save(catalogItem);
//            return "index";
//        }catch (Exception e){
//            return "error";
//        }
//    }
}