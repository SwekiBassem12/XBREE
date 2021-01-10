package com.example.xbree.Utils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.xbree.AddEvent;
import com.example.xbree.Entities.Evenement;
import com.example.xbree.Entities.User;
import com.example.xbree.HomeAdapter.EvenementAdapter;
import com.example.xbree.LoginActivity;
import com.example.xbree.Profile;
import com.example.xbree.R;
import com.example.xbree.RegisterActivity;
import com.example.xbree.Retrofit.INodeJS;
import com.example.xbree.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Update_profile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText name1, lname1, email1, phone1;
    Button update;
    private static int id = 1;
    public static INodeJS iNodeJS;
    int idd;
    LottieAnimationView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        v = findViewById(R.id.animm);
        email1 = findViewById(R.id.emailll);
        name1 = findViewById(R.id.nameee);
        lname1 = findViewById(R.id.lnameee);
        phone1 = findViewById(R.id.phoneee);
        update = findViewById(R.id.btn_updt);
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
        loadClientData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateuser(idd, email1.getText().toString(), name1.getText().toString(), lname1.getText().toString(), Integer.valueOf(phone1.getText().toString()));
                System.out.println(idd);
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }

        });

    }

    void showDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        alertDialog.show();

        Button acceptButton = view.findViewById(R.id.acceptButton);
        Button cancelButton = view.findViewById(R.id.cancelButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }

    private void deleteUser() {
        sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        int idu = sharedPreferences.getInt("idUser", 0);
        Call<User> call = iNodeJS.DeleteUser(idu);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(Update_profile.this, "YYEEESSS", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Update_profile.this, LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Update_profile.this, "Deleted Succefully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Update_profile.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void updateuser(int id, final String email, final String name, final String prenom, final int tel) {

        Call<User> call = iNodeJS.updateProfile(id, email, name, prenom, tel);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(Update_profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Update_profile.this, Profile.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Update_profile.this, "ERROORR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void loadClientData() {
        sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        String idus = sharedPreferences.getString("EmailUser", "");
        Call<User> call = iNodeJS.getUser(idus);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                System.out.println(user.getId() + "teeesssssssssssssssssst");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("idUser", user.getId());
                editor.putInt("telUser", user.getPhone());
                editor.putString("nomUser", user.getName());
                editor.putString("prenomUser", user.getLname());
                editor.putString("EmailUser", user.getEmail());
                editor.putString("pass", user.getPassword());
                editor.apply();

                idd = sharedPreferences.getInt("idUser", 0);
                String email = sharedPreferences.getString("EmailUser", "");
                String name = sharedPreferences.getString("nomUser", "");
                String lastname = sharedPreferences.getString("prenomUser", "");
                int phone = sharedPreferences.getInt("telUser", 0);
                String password = sharedPreferences.getString("pass", "");
                System.out.println(name + lastname);
                email1.setText(email);
                name1.setText(name);
                lname1.setText(lastname);
                phone1.setText(String.valueOf(phone));

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}