package com.example.xbree.Utils.dao;


import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.xbree.Entities.Evenement;
import com.example.xbree.Entities.UserCommentaire;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insertOne(Evenement evenement);
    @Query("SELECT * FROM evenement WHERE id=:id")
    int check_item(int id);
    @Delete
    void delete(Evenement produit);
    @Query("SELECT * FROM evenement")
    List<Evenement> getAll();


}