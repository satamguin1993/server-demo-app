package com.server.example.serverdemo.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Item {

    public enum Status {
        AVAILABLE,
        SOLD_OUT,
        PENDING,
        COMING_SOON
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Float price;

    @Column
    private Float weight;

    @Column
    private String metaData;

    @Column
    private Date publishedDate;

    @Column
    private Integer availableCopies;

    @Column
    private Integer soldSoFar;

    @Column
    private Integer maxUnitAvailablePerCustomer;

    @Column
    private Department department;

    @Column
    private Status status;

    @Column
    private UUID encodedItemId;

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    private Brand brand;

    @OneToOne
    @JoinColumn(name = "id")
    private  Category category;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Integer getSoldSoFar() {
        return soldSoFar;
    }

    public void setSoldSoFar(Integer soldSoFar) {
        this.soldSoFar = soldSoFar;
    }

    public Integer getMaxUnitAvailablePerCustomer() {
        return maxUnitAvailablePerCustomer;
    }

    public void setMaxUnitAvailablePerCustomer(Integer maxUnitAvailablePerCustomer) {
        this.maxUnitAvailablePerCustomer = maxUnitAvailablePerCustomer;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getEncodedItemId() {
        return encodedItemId;
    }

    public void setEncodedItemId(UUID encodedItemId) {
        this.encodedItemId = encodedItemId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
