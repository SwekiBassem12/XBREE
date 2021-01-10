package com.example.xbree.HomeAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xbree.Entities.Shop;
import com.example.xbree.R;
import com.example.xbree.Retrofit.INodeJS;
import com.example.xbree.Retrofit.RetrofitClient;
import com.example.xbree.Utils.database.AppDataBase;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.myViewHolder> {

    Context mContext;
    private List<Shop> mData;
    private List<Shop> event_list = new ArrayList<>();
    TextView textcomm, nom_userr;
    public static INodeJS iNodeJS;


    public ShopAdapter(Context mContext, List<Shop> mDataa) {
        this.mContext = mContext;
        this.mData =  mDataa;
    }

    @NonNull
    @Override
    public ShopAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.row_item, parent, false);
        ShopAdapter.myViewHolder vv = new ShopAdapter.myViewHolder(v);

        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.myViewHolder holder, int position) {
        Shop evenement = mData.get(position);

        nom_userr.setText(evenement.getNom_event());
        textcomm.setText(evenement.getPrice());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {

        public myViewHolder(@NonNull final View itemView) {
            super(itemView);
            iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
            nom_userr = itemView.findViewById(R.id.textView);
            textcomm = itemView.findViewById(R.id.rowCountTextView);

        }
    }

}