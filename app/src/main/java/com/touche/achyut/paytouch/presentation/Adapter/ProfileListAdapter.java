package com.touche.achyut.paytouch.presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.touche.achyut.paytouch.R;
import com.touche.achyut.paytouch.domain.model.Film;

import java.util.List;


public class ProfileListAdapter extends ArrayAdapter<Film> {

    private Context context;
    private List<Film> films;

    public ProfileListAdapter(Context context, int resource, List<Film> filmList) {
        super(context, resource, filmList);
        this.context = context;
        this.films = filmList;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView title,date,vote,count;
        ImageView poster;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.film_list_item, parent, false);

        Film currentFilm = getItem(position);

        //TODO Uncomment to load bitmap
//        Bitmap bitmap = BitmapHelper.getBitmap(currentFilm.getPoster());

        poster = (ImageView)rowView.findViewById(R.id.poster);
        title=(TextView)rowView.findViewById(R.id.title);
        date=(TextView)rowView.findViewById(R.id.date);
        vote=(TextView)rowView.findViewById(R.id.vote);
        count=(TextView)rowView.findViewById(R.id.count);

        title.setText(currentFilm.getTitle());
        date.setText(currentFilm.getRelease_date());
        vote.setText(String.valueOf(currentFilm.getAvg_vote()));
        count.setText(String.valueOf(currentFilm.getAvg_count()));
        //TODO Uncomment to set bitmap
//        poster.setImageBitmap(bitmap);
        return rowView;

    }

    @Override
    public Film getItem(int position) {
        return films.get(position);
    }
}
