/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nohop
 */
@Entity
@Table(name = "product")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    int id;
    String name;
    String description;
    int price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category ct = new Category();
    @ManyToMany
    @JoinTable
    (
        name = "product_manufacturer",
        joinColumns = {
        @JoinColumn(name = "product_id")
        
    },
        inverseJoinColumns = {
            @JoinColumn(name = "manufactorer_id")
        }
    )
    Set<Manufacturer> manu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCt() {
        return ct;
    }

    public void setCt(Category ct) {
        this.ct = ct;
    }

    public Set<Manufacturer> getManu() {
        return manu;
    }

    public void setManu(Set<Manufacturer> manu) {
        this.manu = manu;
    }
    
    
}
