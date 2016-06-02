package com.touche.achyut.paytouch.presentation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.touche.achyut.paytouch.R;
import com.touche.achyut.paytouch.domain.model.Actor;
import com.touche.achyut.paytouch.presentation.Activity.ProfileDetails;
import com.touche.achyut.paytouch.presentation.AsyncTask.ImageStreamDownloadTask;
import com.touche.achyut.paytouch.utils.BitmapHelper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by ISSV193 on 1/24/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater inflator;
    List<Actor> info = Collections.emptyList();
    Context appContext;
    private Actor currentInfo;

    public RecyclerViewAdapter(Context context, List<Actor> list) {
       inflator = LayoutInflater.from(context);
        this.info = list;
        this.appContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view = inflator.inflate(R.layout.profile_list_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        currentInfo = getItem(position);

        //TODO Uncomment to load bitmap
//        Bitmap bitmap = BitmapHelper.getBitmap(currentInfo.getProfile_pic());

        if (position%2==0){
            holder.root.setBackground(appContext.getResources().getDrawable(R.drawable.roundcorner_grey));
            holder.name.setTextColor(Color.WHITE);
            holder.location.setTextColor(Color.WHITE);
            holder.description.setTextColor(Color.WHITE);
            holder.location_img.setImageResource(R.drawable.pin_white);
            holder.popularity.setBackground(appContext.getResources().getDrawable(R.drawable.border_blue));
        }

        holder.name.setText(currentInfo.getName());
        holder.location.setText(currentInfo.getLocation());
        holder.description.setText(currentInfo.getDescription());
        holder.popularity.setText(String.valueOf(currentInfo.getPopularity()));
        //TODO Uncomment to set bitmap
//        holder.profile_pic.setImageBitmap(bitmap);


    }

    public Actor getItem(int position){
        return info.get(position);
    }



    @Override
    public int getItemCount() {
        return  info == null ? 0 : info.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,location,description,popularity;
        ImageView profile_pic,location_img;
        LinearLayout root;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            profile_pic = (ImageView)itemView.findViewById(R.id.profile_pic);
            location = (TextView)itemView.findViewById(R.id.location);
            description = (TextView)itemView.findViewById(R.id.description_text);
            location_img =(ImageView)itemView.findViewById(R.id.location_img);
            popularity = (TextView)itemView.findViewById(R.id.popularity);
            root = (LinearLayout)itemView.findViewById(R.id.root_layout);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent i = null;

            if (v == itemView){
                 i = new Intent(appContext, ProfileDetails.class);
                i.putExtra("profileDetail", getItem(getAdapterPosition()));
                appContext.startActivity(i);
            }
        }
    }
}
