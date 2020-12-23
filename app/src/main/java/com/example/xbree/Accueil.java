package com.example.xbree;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xbree.HomeAdapter.Most_viewedAdapter;
import com.example.xbree.HomeAdapter.Most_viewedHelperClass;
import com.example.xbree.Utils.Affich_Events;
import com.example.xbree.Utils.Bars;
import com.example.xbree.Utils.Cafee;
import com.example.xbree.Utils.Hotels;
import com.example.xbree.Utils.Restaurants;
import com.google.android.material.navigation.NavigationView;
import com.example.xbree.HomeAdapter.CategoriesAdapter;
import com.example.xbree.HomeAdapter.CategoriesHelperClass;
import com.example.xbree.HomeAdapter.FeaturedAdapter;
import com.example.xbree.HomeAdapter.FeaturedHelperClass;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    static final float END_SCALE= 0.7f;
    LinearLayout contentView;
RecyclerView FeaturedRecycler;
RecyclerView CategoriesRecycler;
RecyclerView Most_viewedRecycler;
RecyclerView.Adapter adapter;
ImageView icon_drawer;
ImageView shop;
ImageView restau,hotel,bar,cafe;
private GradientDrawable gradient1, gradient2, gradient3;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_acceuil);
        FeaturedRecycler = findViewById(R.id.featued_recycler);
        CategoriesRecycler = findViewById(R.id.categories_recycler);
        Most_viewedRecycler = findViewById(R.id.mv_recycler);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        icon_drawer = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        shop = findViewById(R.id.shop);
        restau = findViewById(R.id.restau);
        hotel = findViewById(R.id.hotel);
        bar = findViewById(R.id.bar);
        cafe = findViewById(R.id.cafe);
        navigationDrawer();
        FeaturedRecycler();
        CategoriesRecycler();
        Most_viewedRecycler();
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(Accueil.this,Swipe_Shop.class);
                startActivity(i);
            }
        });
        restau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(Accueil.this, Restaurants.class);
                startActivity(i);
            }
        });
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(Accueil.this, Cafee.class);
                startActivity(i);
            }
        });
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(Accueil.this, Bars.class);
                startActivity(i);
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(Accueil.this, Hotels.class);
                startActivity(i);
            }
        });
    }

    private void Most_viewedRecycler() {
        Most_viewedRecycler.setHasFixedSize(true);
        Most_viewedRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<Most_viewedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new Most_viewedHelperClass(R.drawable.plan_b,"Plan B"));
        featuredLocations.add(new Most_viewedHelperClass(R.drawable.kfc,"KFC"));
        featuredLocations.add(new Most_viewedHelperClass(R.drawable.tacos,"Chaneb Tacos"));
        adapter = new Most_viewedAdapter(featuredLocations);
        Most_viewedRecycler.setAdapter(adapter);

    }

    private void CategoriesRecycler() {
        CategoriesRecycler.setHasFixedSize(true);
        CategoriesRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<CategoriesHelperClass> categoriesLocations = new ArrayList<>();
        categoriesLocations.add(new CategoriesHelperClass(R.drawable.after_wrk_img,"After Work"));
        categoriesLocations.add(new CategoriesHelperClass(R.drawable.romantic,"Romantic"));
        categoriesLocations.add(new CategoriesHelperClass(R.drawable.match_watch,"Live matches"));
        categoriesLocations.add(new CategoriesHelperClass(R.drawable.discodance,"Dance fields"));
        categoriesLocations.add(new CategoriesHelperClass(R.drawable.eat_img,"Something to eat"));
        adapter = new CategoriesAdapter(categoriesLocations);
        CategoriesRecycler.setAdapter(adapter);
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
        icon_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNagivationDrawer();
    }

    private void animateNagivationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    public void onBackPressed(){
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }

    private void FeaturedRecycler() {
        FeaturedRecycler.setHasFixedSize(true);
        FeaturedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.plan_b,"Plan B"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.kfc,"KFC"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.tacos,"Chaneb Tacos"));
        adapter = new FeaturedAdapter(featuredLocations);
        FeaturedRecycler.setAdapter(adapter);

        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400, 0xffaff600});
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400, 0xffaff600});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400, 0xffaff600});

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Intent i  = new Intent(Accueil.this,Profile.class);
                startActivity(i);
                break;
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.search:
                Intent x  = new Intent(Accueil.this, Affich_Events.class);
                startActivity(x);
                break;
            case R.id.chat:
                Intent y  = new Intent(Accueil.this, MessageActivity.class);
                startActivity(y);
                break;
            case R.id.near_by:
                Intent intent = null, chooser = null;
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:36.845170,11.080392"));
                chooser = Intent.createChooser(intent,"Launch Maps");
                startActivity(chooser);
                break;

        }
        return true;
    }
}