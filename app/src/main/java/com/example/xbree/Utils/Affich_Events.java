package com.example.xbree.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.xbree.Entities.Evenement;
import com.example.xbree.HomeAdapter.EvenementAdapter;
import com.example.xbree.HomeAdapter.Most_viewedAdapter;
import com.example.xbree.HomeAdapter.Most_viewedHelperClass;
import com.example.xbree.R;
import com.example.xbree.Retrofit.INodeJS;
import com.example.xbree.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Affich_Events extends AppCompatActivity {

    ImageView acceuil1, profileuser, favori;
    List<Evenement> evenementsList;

    RecyclerView recyclerView;
    public static INodeJS iNodeJS;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affich__events);
        recyclerView = findViewById(R.id.events);
        favori = findViewById(R.id.favori);
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
        GetListEvenement();
    }

    public void GetListEvenement() {
        Call<List<Evenement>> call = iNodeJS.getEventsList();
        Log.e("hs,", "bvn,s;c");
        call.enqueue(new Callback<List<Evenement>>() {
            @Override
            public void onResponse(Call<List<Evenement>> call, Response<List<Evenement>> response) {
                evenementsList = response.body();
                Log.e("hs,", "bvn,s;c");
                Log.d("test2", String.valueOf(response.body()));
                //recyclerView = (R.id.publications);
                EvenementAdapter adapter = new EvenementAdapter(mContext, evenementsList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            }

            @Override
            public void onFailure(Call<List<Evenement>> call, Throwable t) {

            }
        });
    }

}