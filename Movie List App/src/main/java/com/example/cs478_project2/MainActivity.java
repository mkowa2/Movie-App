package com.example.cs478_project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView movieList = findViewById(R.id.movieListView);

        // Adds all the movies, their actors, title, url, and urls to their streaming services
        Movie[] movies = new Movie[]{
                new Movie(
                        R.drawable.allquiet,
                        getString(R.string.all_quiet),
                        getString(R.string.all_quiet_actors),
                        "https://en.wikipedia.org/wiki/All_Quiet_on_the_Western_Front",
                        new ArrayList<>(Arrays.asList(new StreamingService(getString(R.string.netflix), getString(R.string.allquiet_netflix))))
                ),
                new Movie(
                        R.drawable.batman,
                        getString(R.string.batman),
                        getString(R.string.batman_actors),
                        "https://en.wikipedia.org/wiki/The_Batman_(film)",
                        new ArrayList<>(Arrays.asList(
                                new StreamingService(getString(R.string.max), getString(R.string.batman_max)),
                                new StreamingService(getString(R.string.amazon), getString(R.string.batman_amazon))
                        ))
                ),
                new Movie(
                        R.drawable.halloween,
                        getString(R.string.halloween),
                        getString(R.string.halloween_actors),
                        "https://en.wikipedia.org/wiki/Halloween_Ends",
                        new ArrayList<>(Arrays.asList(
                                new StreamingService(getString(R.string.peacock), getString(R.string.halloween_peacock)),
                                new StreamingService(getString(R.string.youtube), getString(R.string.halloween_youtube))
                        ))
                ),
                new Movie(
                        R.drawable.hunger,
                        getString(R.string.hunger),
                        getString(R.string.hunger_actors),
                        "https://en.wikipedia.org/wiki/The_Hunger_Games:_The_Ballad_of_Songbirds_%26_Snakes",
                        new ArrayList<>(Arrays.asList(
                                new StreamingService(getString(R.string.vudu), getString(R.string.hunger_vudu)),
                                new StreamingService(getString(R.string.amazon), getString(R.string.hunger_amazon))
                        ))
                ),
                new Movie(
                        R.drawable.indianajones,
                        getString(R.string.indiana),
                        getString(R.string.indiana_actors),
                        "https://en.wikipedia.org/wiki/Indiana_Jones_and_the_Dial_of_Destiny",
                        new ArrayList<>(Arrays.asList(
                                new StreamingService(getString(R.string.disney), getString(R.string.indiana_disney)),
                                new StreamingService(getString(R.string.youtube), getString(R.string.indiana_youtube))
                        ))
                ),
                new Movie(
                        R.drawable.oppenheimer,
                        getString(R.string.oppenheimer),
                        getString(R.string.oppenheimer_actors),
                        "https://en.wikipedia.org/wiki/Oppenheimer_(film)",
                        new ArrayList<>(Arrays.asList(
                                new StreamingService(getString(R.string.peacock), getString(R.string.oppenheimer_peacock)),
                                new StreamingService(getString(R.string.youtube), getString(R.string.oppenheimer_youtube))
                        ))
                ),
                new Movie(
                        R.drawable.tmnt,
                        getString(R.string.tmnt),
                        getString(R.string.tmnt_actors),
                        "https://en.wikipedia.org/wiki/Teenage_Mutant_Ninja_Turtles:_Mutant_Mayhem",
                        new ArrayList<>(Arrays.asList(
                                new StreamingService(getString(R.string.paramount), getString(R.string.tmnt_paramount)),
                                new StreamingService(getString(R.string.amazon), getString(R.string.tmnt_amazon))
                        ))
                ),
                new Movie(
                        R.drawable.x,
                        getString(R.string.x),
                        getString(R.string.x_actors),
                        "https://en.wikipedia.org/wiki/X_(2022_film)",
                        new ArrayList<>(Arrays.asList(new StreamingService(getString(R.string.netflix), getString(R.string.x_netflix))))
                )
        };

        //Gives the list of movies an addapter
        MovieAdapter movieAdapter = new MovieAdapter(this, R.layout.single_item, movies);
        movieList.setAdapter(movieAdapter);


        //Opens the Wiki pagae on a short click
        movieList.setOnItemClickListener((parent, view, position, id) -> {
            Movie selectedMovie = movieAdapter.getItem(position);
            if (selectedMovie != null) {
                openWeb(selectedMovie.getWebPageUrl());
            }
        });

        registerForContextMenu(movieList);

        //For a long click it will open the menu
        movieList.setOnItemLongClickListener(((parent, view, position, id) -> {
            Movie selectedMovie = movieAdapter.getItem(position);
            if (selectedMovie != null) {
                openContextMenu(movieList);
                return true;
            }
            return false;
        }));
    }


    //Used to lauch the wiki website
    private void openWeb(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }

    //Creates the menu that pops up during a long press
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    //Used to handle the clicks when chosing the three optons in the menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        MovieAdapter movieAdapter = (MovieAdapter) ((ListView) findViewById(R.id.movieListView)).getAdapter();

        Movie selectedMovie = movieAdapter.getItem(info.position);

        if (item.getItemId() == R.id.view_full_poster) {
            viewFullPoster(selectedMovie);
            return true;
        }
        else if (item.getItemId() == R.id.view_wikipedia_page) {
            openWeb(selectedMovie.getWebPageUrl());
            return true;
        }
        else if (item.getItemId() == R.id.view_streaming_services) {
            viewStreamingServices(selectedMovie);
            return true;
        }
        else {
            return super.onContextItemSelected(item);
        }
    }

    //Starts the activity where the enire movie photo fills the screen
    private void viewFullPoster(Movie movie) {
        Intent intent = new Intent(this, FullView.class);
        intent.putExtra("SELECTED_MOVIE", movie);
        startActivity(intent);
    }

    //Starts the activity to see the streaming services
    private void viewStreamingServices(Movie movie) {
        Intent intent = new Intent(this, StreamingActivity.class);
        intent.putExtra("SELECTED_MOVIE", movie);

        Log.d("MainActivity", "Selected movie streaming services count: " + movie.getStreamingServices().size());
        startActivity(intent);
    }

}