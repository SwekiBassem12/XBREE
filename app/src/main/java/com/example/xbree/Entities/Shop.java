package com.example.xbree.Entities;

public class Shop {
    int id,id_user,id_event,price;
    String nom_event;

    public Shop(int id, int id_user, int id_event, int price, String nom_event) {
        this.id = id;
        this.id_user = id_user;
        this.id_event = id_event;
        this.price = price;
        this.nom_event = nom_event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }
}
