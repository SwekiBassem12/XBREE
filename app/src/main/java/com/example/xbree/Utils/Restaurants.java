package com.example.xbree.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.xbree.AddEvent;
import com.example.xbree.HomeAdapter.RestaurantsAdapter;
import com.example.xbree.HomeAdapter.RestaurantsHelperClass;
import com.example.xbree.R;
import com.example.xbree.RecyclerViewClickInterface;

import java.util.ArrayList;

public class Restaurants extends AppCompatActivity {
    RecyclerView RestaurantsRecycler;
    RecyclerView.Adapter adapter;
    View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        RestaurantsRecycler = findViewById(R.id.restau_ryc);
        RestaurantsRecycler();
    }

    private void RestaurantsRecycler() {
        RestaurantsRecycler.setHasFixedSize(true);
        RestaurantsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<RestaurantsHelperClass> RestaurantsLocations = new ArrayList<>();
        RestaurantsLocations.add(new RestaurantsHelperClass(R.drawable.plan_b, "Plan B", "Manzah 1", "This restaurants is specialized in sandwich "));
        RestaurantsLocations.add(new RestaurantsHelperClass(R.drawable.kfc, "KFC", "Manar 2", "This restaurants is specialized in sandwich"));
        RestaurantsLocations.add(new RestaurantsHelperClass(R.drawable.tacos, "Chaneb Tacos", "Ennasr 1", "This restaurants is specialized in sandwich"));
        RestaurantsLocations.add(new RestaurantsHelperClass(R.drawable.baguette, "Baguette&Baguette", "Centre Ville", "This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new RestaurantsHelperClass(R.drawable.senio_tacos, "Senior Tacos", "Ennasr 2", "This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new RestaurantsHelperClass(R.drawable.carmine, "Carmine", "Gammarth", "This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new RestaurantsHelperClass(R.drawable.pasta_causi, "Pasta Cosi", "Lac 1", "This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new RestaurantsHelperClass(R.drawable.dar_zmen, "Dar Zmen", "Lac 2", "This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        adapter = new RestaurantsAdapter(RestaurantsLocations);
        RestaurantsRecycler.setAdapter(adapter);

    }
}