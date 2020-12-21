package com.example.xbree.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.xbree.HomeAdapter.BarsAdapter;
import com.example.xbree.HomeAdapter.BarsHelperClass;
import com.example.xbree.HomeAdapter.HotelsAdapter;
import com.example.xbree.HomeAdapter.HotelsHelperClass;
import com.example.xbree.R;

import java.util.ArrayList;

public class Hotels extends AppCompatActivity {
    RecyclerView HotelsRecycler;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        HotelsRecycler = findViewById(R.id.hotels_ryc);
        HotelsRecycler();
    }

    private void HotelsRecycler() {
        HotelsRecycler.setHasFixedSize(true);
        HotelsRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ArrayList<HotelsHelperClass> RestaurantsLocations = new ArrayList<>();
        RestaurantsLocations.add(new HotelsHelperClass(R.drawable.gldntulip,"Golde Tulip","Gammarth","This restaurants is specialized in sandwich "));
        RestaurantsLocations.add(new HotelsHelperClass(R.drawable.mouradi,"El Mouradi","Gammarth","This restaurants is specialized in sandwich"));
        RestaurantsLocations.add(new HotelsHelperClass(R.drawable.hilton,"Hilton","Gammarth","This restaurants is specialized in sandwich"));
        RestaurantsLocations.add(new HotelsHelperClass(R.drawable.mercure,"Mercure","Centre Ville","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new HotelsHelperClass(R.drawable.movenpik,"Movenpick","Sousse","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new HotelsHelperClass(R.drawable.chezlm,"Chez l'Ami","Sokra","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        adapter = new HotelsAdapter(RestaurantsLocations);
        HotelsRecycler.setAdapter(adapter);
    }
}