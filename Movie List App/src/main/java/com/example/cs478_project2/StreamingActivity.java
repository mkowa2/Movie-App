package com.example.cs478_project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;

public class StreamingActivity extends AppCompatActivity {

    //Creates the list of streaming services and then inflates it and handles the clicks to open
    // their specific websites
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streaming_services);

        ListView listView = findViewById(R.id.streamingServicesListView);

        Movie selectedMovie = getIntent().getParcelableExtra("SELECTED_MOVIE");
        ArrayList<StreamingService> streamingServices = selectedMovie.getStreamingServices();

        if (streamingServices != null && !streamingServices.isEmpty()) {
            // Log to check if streamingServices is not null and contains items
            Log.d("StreamingActivity", "Streaming services count: " + streamingServices.size());

            ArrayAdapter<StreamingService> adapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, streamingServices);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                StreamingService clickedService = streamingServices.get(position);
                openWeb(clickedService.getServiceLink());
            });
        } else {
            Log.e("StreamingActivity", "No streaming services available for the selected movie.");
        }


    }
    private void openWeb(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }
}
