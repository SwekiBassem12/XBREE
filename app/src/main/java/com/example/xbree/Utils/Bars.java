package com.example.xbree.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.xbree.HomeAdapter.BarsAdapter;
import com.example.xbree.HomeAdapter.BarsHelperClass;
import com.example.xbree.HomeAdapter.RestaurantsAdapter;
import com.example.xbree.HomeAdapter.RestaurantsHelperClass;
import com.example.xbree.R;

import java.util.ArrayList;

public class Bars extends AppCompatActivity {
    RecyclerView BarsRecycler;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bars);
        BarsRecycler = findViewById(R.id.bars_ryc);
        BarsRecycler();
    }

    private void BarsRecycler() {
        BarsRecycler.setHasFixedSize(true);
        BarsRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ArrayList<BarsHelperClass> RestaurantsLocations = new ArrayList<>();
        RestaurantsLocations.add(new BarsHelperClass(R.drawable.yuka,"Yuka","Gammarth","This restaurants is specialized in sandwich "));
        RestaurantsLocations.add(new BarsHelperClass(R.drawable.jobi,"Jobi","Gammarth","This restaurants is specialized in sandwich"));
        RestaurantsLocations.add(new BarsHelperClass(R.drawable.duplex,"Duplex","Centre Ville","This restaurants is specialized in sandwich"));
        RestaurantsLocations.add(new BarsHelperClass(R.drawable.sterling,"Le Sterling","Centre Ville","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new BarsHelperClass(R.drawable.soho,"SOHO","Gammarth","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new BarsHelperClass(R.drawable.chezlm,"Chez l'Ami","Sokra","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        adapter = new BarsAdapter(RestaurantsLocations);
        BarsRecycler.setAdapter(adapter);
    }
}