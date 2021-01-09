package com.example.xbree.HomeAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xbree.Entities.Evenement;
import com.example.xbree.Entities.User;
import com.example.xbree.Entities.UserCommentaire;
import com.example.xbree.R;
import com.example.xbree.Retrofit.INodeJS;
import com.example.xbree.Retrofit.RetrofitClient;
import com.example.xbree.Utils.database.AppDataBase;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentaireAdapter extends RecyclerView.Adapter<CommentaireAdapter.myViewHolder> {

    Context mContext;
    private List<UserCommentaire> mData;
    private List<UserCommentaire> event_list = new ArrayList<>();
    SharedPreferences sharedPreferences, sharedPreferencesUE, sharedPreferencesU;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextView textcomm, nom_userr;
    private AppDataBase database;
    public static INodeJS iNodeJS;
    int idus;


    public CommentaireAdapter(Context mContext, List<UserCommentaire> mDataa) {
        this.mContext = mContext;
        this.mData = mDataa;
    }

    @NonNull
    @Override
    public CommentaireAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.com_item, parent, false);
        CommentaireAdapter.myViewHolder vv = new CommentaireAdapter.myViewHolder(v);

        sharedPreferencesUE = parent.getContext().getSharedPreferences("UserEvent", Context.MODE_PRIVATE);
        sharedPreferencesU = parent.getContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        idus = sharedPreferencesU.getInt("idUser", 0);


        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentaireAdapter.myViewHolder holder, int position) {
        UserCommentaire evenement = mData.get(position);

        nom_userr.setText(evenement.getNom_user());
        textcomm.setText(evenement.getCommentaire());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {

        public myViewHolder(@NonNull final View itemView) {
            super(itemView);
            iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
            nom_userr = itemView.findViewById(R.id.nom_user);
            textcomm = itemView.findViewById(R.id.textcom);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    database = AppDataBase.getAppDatabase(v.getContext());
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        UserCommentaire ce = mData.get(position);

                    }
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return false;
                }
            });

        }
    }

}