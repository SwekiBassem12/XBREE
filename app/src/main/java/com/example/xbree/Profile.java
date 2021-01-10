package com.example.xbree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xbree.Utils.Update_profile;

public class Profile extends AppCompatActivity {
    TextView logout;
    TextView evenement;
    TextView update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = findViewById(R.id.logout);
        evenement = findViewById(R.id.evenement);
        update = findViewById(R.id.updt_profile);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Update_profile.class);
                startActivity(i);

            }
        });

        evenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, AddEvent.class);
                startActivity(i);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.xbree.Profile.this, LoginActivity.class);
                startActivity(i);

            }
        });
    }
}