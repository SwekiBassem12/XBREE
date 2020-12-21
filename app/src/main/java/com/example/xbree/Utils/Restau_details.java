package com.example.xbree.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xbree.R;

public class Restau_details extends AppCompatActivity {
ImageView img;
TextView name,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restau_details);
        location = findViewById(R.id.restau_loaction);
        name = findViewById(R.id.restau_name);
        img = findViewById(R.id.image);
        String name_restau = getIntent().getStringExtra("title");
        String location_restau = getIntent().getStringExtra("location");
        int image_restau = getIntent().getIntExtra("title",0);
        name.setText(name_restau);
        location.setText(location_restau);
        img.setImageResource(R.drawable.kfc);

    }
}