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

public class Most_viewedAdapter extends RecyclerView.Adapter<Most_viewedAdapter.Most_viewedViewHolder> {

    ArrayList<Most_viewedHelperClass> Most_viewedLocations;

    public Most_viewedAdapter(ArrayList<Most_viewedHelperClass> Most_viewedLocations) {
        this.Most_viewedLocations = Most_viewedLocations;
    }

    @NonNull
    @Override
    public Most_viewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design, parent, false);
        Most_viewedViewHolder Most_viewedViewHolder = new Most_viewedViewHolder(view);

        return Most_viewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Most_viewedViewHolder holder, int position) {
        Most_viewedHelperClass Most_viewedHelperClass = Most_viewedLocations.get(position);
        holder.image.setImageResource(Most_viewedHelperClass.getImage());
        holder.title.setText(Most_viewedHelperClass.getTitle());


    }

    @Override
    public int getItemCount() {
        return Most_viewedLocations.size();
    }

    public static class Most_viewedViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public Most_viewedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
        }
    }
}
