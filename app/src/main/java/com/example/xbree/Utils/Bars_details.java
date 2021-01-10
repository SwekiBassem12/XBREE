package com.example.xbree.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.xbree.Entities.UserCommentaire;
import com.example.xbree.HomeAdapter.CommentaireAdapter;
import com.example.xbree.R;
import com.example.xbree.Retrofit.INodeJS;
import com.example.xbree.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bars_details extends AppCompatActivity {

        ImageView img;
        TextView name, location;
        SharedPreferences sharedPreferences;
        EditText comtext;
        List<UserCommentaire> comsList;
        Context mContext;
        RecyclerView recyclerView;
        TextView com;
        LottieAnimationView refsh;
        public static INodeJS iNodeJS;
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bars_details);

            location = findViewById(R.id.bars_loaction);
            name = findViewById(R.id.bars_name);
            img = findViewById(R.id.imageB);
            com = findViewById(R.id.comBBtn);
            comtext = findViewById(R.id.textcom);
            refsh = findViewById(R.id.refreshB);
            recyclerView = findViewById(R.id.comBrecycler);

            iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);

            String name_restau = getIntent().getStringExtra("title");
            String location_restau = getIntent().getStringExtra("location");
            int image_restau = getIntent().getIntExtra("title", 0);
            name.setText(name_restau);
            location.setText(location_restau);
            img.setImageResource(R.drawable.yuka);

            sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);

            GetListCom();

            refsh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetListCom();
                }
            });

            com.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comtext.getText().toString().equals("")) {

                        Toast.makeText(Bars_details.this, "Add a comment!", Toast.LENGTH_SHORT).show();

                    } else
                        commenter(comtext.getText().toString(), sharedPreferences.getInt("idUser", 0), sharedPreferences.getString("nomUser", ""));
                }
            });

        }

        private void GetListCom () {
            Call<List<UserCommentaire>> call = iNodeJS.getComsList();
            Log.e("hs,", "bvn,s;c");
            call.enqueue(new Callback<List<UserCommentaire>>() {
                @Override
                public void onResponse(Call<List<UserCommentaire>> call, Response<List<UserCommentaire>> response) {
                    comsList = response.body();
                    //Log.e("hs,", "bvn,s;c");
                    //Log.d("test2", String.valueOf(response.body()));
                    //recyclerView = (R.id.publications);
                    CommentaireAdapter adapter = new CommentaireAdapter(mContext, comsList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

                }

                @Override
                public void onFailure(Call<List<UserCommentaire>> call, Throwable t) {

                }
            });
        }

        private void commenter ( final String commentaire, final int id_user, final String nom_user)
        {

            compositeDisposable.add(iNodeJS.commenterUser(commentaire, id_user, nom_user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            Toast.makeText(Bars_details.this, "" + s, Toast.LENGTH_SHORT).show();
                        }
                    })
            );

        }


}