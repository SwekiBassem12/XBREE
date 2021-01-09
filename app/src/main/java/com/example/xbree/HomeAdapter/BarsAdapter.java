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

public class BarsAdapter extends RecyclerView.Adapter<BarsAdapter.BarsViewHolder> {

    ArrayList<BarsHelperClass> BarsLocations;

    public BarsAdapter(ArrayList<BarsHelperClass> BarsLocations) {
        this.BarsLocations = BarsLocations;
    }

    @NonNull
    @Override
    public BarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bars_card_design, parent, false);
        BarsViewHolder BarsViewHolder = new BarsViewHolder(view);

        return BarsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BarsViewHolder holder, int position) {
        BarsHelperClass BarsHelperClass = BarsLocations.get(position);
        holder.image.setImageResource(BarsHelperClass.getImage());
        holder.title.setText(BarsHelperClass.getTitle());
        holder.descrip.setText(BarsHelperClass.getDescrip());
        holder.location.setText(BarsHelperClass.getLocation());


    }

    @Override
    public int getItemCount() {
        return BarsLocations.size();
    }

    public static class BarsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView descrip;
        TextView location;

        public BarsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.bars_image);
            title = itemView.findViewById(R.id.bars_title);
            descrip = itemView.findViewById(R.id.bars_descrip);
            location = itemView.findViewById(R.id.bars_location);
        }
    }
}
