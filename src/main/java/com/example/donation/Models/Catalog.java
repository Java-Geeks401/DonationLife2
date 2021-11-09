package com.example.donation.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catalogId;
    @Column(unique = true)
    private String catalogName;
    @OneToMany(mappedBy = "catalogItems")
    private List<CatalogItem> itemsList;

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
    }

    public Catalog(){
    }
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public List<CatalogItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<CatalogItem> itemsList) {
        this.itemsList = itemsList;
    }
}
