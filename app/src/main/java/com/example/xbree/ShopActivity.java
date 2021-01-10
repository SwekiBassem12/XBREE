package com.example.xbree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.xbree.Entities.Shop;
import com.example.xbree.HomeAdapter.ShopAdapter;
import com.example.xbree.Retrofit.INodeJS;
import com.example.xbree.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {
    List<Shop> comsList;
    Context mContext;
    public static INodeJS iNodeJS;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_shop);
        recyclerView = findViewById(R.id.recyclerViewS);
        sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
        GetListCom();
    }
    private void GetListCom() {
        Call<List<Shop>> call = iNodeJS.getUserShop(sharedPreferences.getInt("idUser",0));
        Log.e("hs,", String.valueOf(sharedPreferences.getInt("idUser",0)));
        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                comsList = response.body();
                ShopAdapter adapter = new ShopAdapter(mContext, comsList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {

            }
        });
    }
}