package com.example.xbree.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_com")
public class UserCommentaire {

    public UserCommentaire(int id, String commentaire, int id_user, String nom_user) {
        this.id = id;
        this.commentaire = commentaire;
        this.id_user = id_user;
        this.nom_user = nom_user;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "commentaire")
    private String commentaire;
    @ColumnInfo(name = "id_user")
    private int id_user;
    @ColumnInfo(name = "nom_user")
    private String nom_user;

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }


}
