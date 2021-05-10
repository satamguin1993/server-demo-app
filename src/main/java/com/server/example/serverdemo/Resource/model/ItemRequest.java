package com.server.example.serverdemo.Resource.model;

import java.util.Date;
import java.util.UUID;

public class ItemRequest {

    public enum Status {
        AVAILABLE,
        SOLD_OUT,
        PENDING,
        COMING_SOON
    }

    private int id;
    private String name;
    private String author;
    private String genreId;
    private Date publishedDate;
    private Integer availableCopies;
    private Integer soldSoFar;
    private Department department;
    private Status status;
    private UUID encodedBookId;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
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

    public UUID getEncodedBookId() {
        return encodedBookId;
    }

    public void setEncodedBookId(UUID encodedBookId) {
        this.encodedBookId = encodedBookId;
    }
}
