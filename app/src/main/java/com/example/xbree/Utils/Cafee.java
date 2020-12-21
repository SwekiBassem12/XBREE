package com.example.xbree.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.xbree.HomeAdapter.BarsAdapter;
import com.example.xbree.HomeAdapter.BarsHelperClass;
import com.example.xbree.HomeAdapter.CafeAdapter;
import com.example.xbree.HomeAdapter.CafeHelperClass;
import com.example.xbree.R;

import java.util.ArrayList;

public class Cafee extends AppCompatActivity {
    RecyclerView CafeRecycler;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafee);
        CafeRecycler = findViewById(R.id.cafe_ryc);
        CafeRecycler();

    }

    private void CafeRecycler() {
        CafeRecycler.setHasFixedSize(true);
        CafeRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ArrayList<CafeHelperClass> RestaurantsLocations = new ArrayList<>();
        RestaurantsLocations.add(new CafeHelperClass(R.drawable.kafeine,"Kafeine","La petite Ariana","This restaurants is specialized in sandwich "));
        RestaurantsLocations.add(new CafeHelperClass(R.drawable.baroque,"Le Baroque","Sokra","This restaurants is specialized in sandwich"));
        RestaurantsLocations.add(new CafeHelperClass(R.drawable.cafe_716,"716","Lac 1","This restaurants is specialized in sandwich"));
        RestaurantsLocations.add(new CafeHelperClass(R.drawable.cct,"Comme Chez Toi","La petite Ariana","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new CafeHelperClass(R.drawable.frederic,"Frederic Cassel","Lac 2","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        RestaurantsLocations.add(new CafeHelperClass(R.drawable.paradise,"Paradise","Ennasr 1","This restaurants is specialized in sandwich and grilled food with hot sauces !!"));
        adapter = new CafeAdapter(RestaurantsLocations);
        CafeRecycler.setAdapter(adapter);
    }
}