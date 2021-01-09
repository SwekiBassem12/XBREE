package com.example.xbree.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xbree.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    ArrayList<CategoriesHelperClass> CategoriesLocations;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> CategoriesLocations) {
        this.CategoriesLocations = CategoriesLocations;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design, parent, false);
        CategoriesViewHolder CategoriesViewHolder = new CategoriesViewHolder(view);

        return CategoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        CategoriesHelperClass CategoriesHelperClass = CategoriesLocations.get(position);
        holder.image.setImageResource(CategoriesHelperClass.getImage());
        holder.title.setText(CategoriesHelperClass.getTitle());
    }

    @Override
    public int getItemCount() {
        return CategoriesLocations.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.Categories_image);
            title = itemView.findViewById(R.id.Categories_title);
        }
    }
}
