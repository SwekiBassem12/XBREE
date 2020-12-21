package com.example.xbree.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "evenement")
public class Evenement {

    public Evenement(int id, String nom, String type, String dateDebut, String dateFin, int distance, String lieu, int infoline, String description, int nbPlace, int prix, int id_user, String image_eve) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.distance = distance;
        this.lieu = lieu;
        this.infoline = infoline;
        this.description = description;
        this.nbPlace = nbPlace;
        this.prix = prix;
        this.id_user = id_user;
        this.image_eve = image_eve;
    }


    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nom")
    private String nom;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "dateDebut")
    private String dateDebut;
    @ColumnInfo(name = "dateFin")
    private String dateFin;
    @ColumnInfo(name = "distance")
    private int distance;
    @ColumnInfo(name = "lieu")
    private String lieu;
    @ColumnInfo(name = "infoline")
    private int infoline;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "nbPlace")
    private int nbPlace;
    @ColumnInfo(name = "prix")
    private int prix;
    @ColumnInfo(name = "id_user")
    private int id_user;
    @ColumnInfo(name = "image_eve")
    private String image_eve;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getInfoline() {
        return infoline;
    }

    public void setInfoline(int infoline) {
        this.infoline = infoline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getImage_eve() {
        return image_eve;
    }

    public void setImage_eve(String image_eve) {
        this.image_eve = image_eve;
    }
}
