package com.example.xbree;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xbree.Entities.User;
import com.example.xbree.Retrofit.INodeJS;
import com.example.xbree.Retrofit.RetrofitClient;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unchecked")
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    public static INodeJS iNodeJS;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText email, password;
    SharedPreferences sharedPreferences;
    private ImageButton btRegister;
    private TextView tvLogin;
    Button Go, facebook, ggl;
    CheckBox check;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        btRegister = findViewById(R.id.btRegister);
        tvLogin = findViewById(R.id.tvLogin);
        btRegister.setOnClickListener(this);
        Go = findViewById(R.id.login_btn);
        facebook = findViewById(R.id.fb);
        ggl = findViewById(R.id.google);
        email = findViewById(R.id.emailEdit);
        password = findViewById(R.id.passwordEdit);
        check = findViewById(R.id.tvremember);

        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);

        //sharedPreferences
        sharedPreferences = getSharedPreferences("testt", Context.MODE_PRIVATE);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                email.setText(sharedPreferences.getString("test", ""));
                password.setText(sharedPreferences.getString("test1", ""));
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, LoginFacebook.class);
                startActivity(i);
            }
        });

        ggl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                if (e.equals("")) {
                    email.setError("email is empty");
                }
                if (p.equals("")) {
                    password.setError("password is empty");
                    //Toast.makeText(LoginActivity.this, "Check Your Entries!", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("test", e);
                    editor.putString("test1", p);
                    editor.apply();
                    loginUser(e, p);
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);

    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            gotoProfile();
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }
    private void gotoProfile(){
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    private void loginUser(final String email, String password) {
        compositeDisposable.add(iNodeJS.loginUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("encrypted_password")) {
                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            loadClientData();

                            Intent i = new Intent(LoginActivity.this, Accueil.class);
                            startActivity(i);
                        } else
                            Toast.makeText(LoginActivity.this, "" + s, Toast.LENGTH_SHORT).show(); //Show error from API
                    }
                })
        );
    }

    public void loadClientData() {
        sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        String email2 = email.getText().toString();
        Call<User> call = iNodeJS.getUser(email2);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                System.out.println(user.getId() + "teeesssssssssssssssssst");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("idUser", user.getId());
                editor.putString("FirstNameUser", user.getName());
                editor.putString("LastNameUser", user.getLname());
                editor.putString("EmailUser", user.getEmail());
                editor.putInt("PhoneUser", user.getPhone());
                editor.putString("ImageUser", user.getImage());
                editor.apply();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
        if (v == btRegister) {
            Intent intent = new Intent(com.example.xbree.LoginActivity.this, RegisterActivity.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(tvLogin, "tvLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(com.example.xbree.LoginActivity.this, pairs);
            startActivity(intent, activityOptions.toBundle());

        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}