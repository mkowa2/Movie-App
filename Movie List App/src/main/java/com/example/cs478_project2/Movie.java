package com.example.cs478_project2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    private int picture;
    private String actorList;
    private String url;
    private String title;

    private ArrayList<StreamingService> streamingServices;


    public Movie(int picture, String title, String actorList, String url, ArrayList<StreamingService> streamingServices){
        this.picture = picture;
        this.title = title;
        this.actorList = actorList;
        this.url = url;
        this.streamingServices = streamingServices;
    }

    public int getThumbnail() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getActorList() {
        return actorList;
    }

    public String getWebPageUrl() {
        return url;
    }

    public ArrayList<StreamingService> getStreamingServices() {
        return streamingServices;
    }

    protected Movie(Parcel in) {
        picture = in.readInt();
        actorList = in.readString();
        url = in.readString();
        title = in.readString();
        streamingServices = new ArrayList<>();
        in.readTypedList(streamingServices, StreamingService.CREATOR);

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(picture);
        dest.writeString(actorList);
        dest.writeString(url);
        dest.writeString(title);
        dest.writeTypedList(streamingServices);
    }
}
