package com.example.xbree.HomeAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xbree.R;
import com.example.xbree.Utils.Bars_details;
import com.example.xbree.Utils.Cafe_details;

import java.util.ArrayList;

public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.CafeViewHolder> {

    ArrayList<CafeHelperClass> CafeLocations;

    public CafeAdapter(ArrayList<CafeHelperClass> CafeLocations) {
        this.CafeLocations = CafeLocations;
    }

    @NonNull
    @Override
    public CafeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cafe_card_design, parent, false);
        CafeViewHolder CafeViewHolder = new CafeViewHolder(view);

        return CafeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CafeViewHolder holder, int position) {
        CafeHelperClass CafeHelperClass = CafeLocations.get(position);
        holder.image.setImageResource(CafeHelperClass.getImage());
        holder.title.setText(CafeHelperClass.getTitle());
        holder.descrip.setText(CafeHelperClass.getDescrip());
        holder.location.setText(CafeHelperClass.getLocation());


    }

    @Override
    public int getItemCount() {
        return CafeLocations.size();
    }

    public static class CafeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView descrip;
        TextView location;

        public CafeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Cafe_details.class);
                    //i.putExtra("image",image.)
                    i.putExtra("title", (title.getText().toString()));
                    i.putExtra("location", (location.getText().toString()));
                    // i.putExtra("image",image.)
                    //i.putExtra("image",);
                    v.getContext().startActivity(i);
                }
            });
            image = itemView.findViewById(R.id.cafe_image);
            title = itemView.findViewById(R.id.cafe_title);
            descrip = itemView.findViewById(R.id.cafe_descrip);
            location = itemView.findViewById(R.id.cafe_location);
        }
    }
}
