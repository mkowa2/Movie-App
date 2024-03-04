package com.example.cs478_project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FullView extends AppCompatActivity {

    //Creates the full view and then if it is clicked, it will launch the website
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.full_view);

        ImageView fullPoster = findViewById(R.id.full_image);

        Movie clickedMovie = getIntent().getParcelableExtra("SELECTED_MOVIE");

        fullPoster.setImageResource(clickedMovie.getThumbnail());

        fullPoster.setOnClickListener(v ->
                openWeb(clickedMovie.getWebPageUrl())
                );
    }

    private void openWeb(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

}
