package com.example.cs478_project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private int layoutResourceID;

    public MovieAdapter(@NonNull Context context, int resource, @NonNull Movie[] movies){
        super(context, resource, movies);
        layoutResourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View row = convertView;

        if (row == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(layoutResourceID, parent, false);
        }
        ImageView poster = row.findViewById(R.id.imageThumbnail);
        TextView title = row.findViewById(R.id.textMovieTitle);
        TextView actors = row.findViewById(R.id.textActorList);

        Movie movieItem = getItem(position);

        if (movieItem != null) {
            poster.setImageResource(movieItem.getThumbnail());
            title.setText(movieItem.getTitle());
            actors.setText(movieItem.getActorList());
        }
        return row;
    }
}
