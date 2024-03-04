package com.example.cs478_project2;

import android.os.Parcel;
import android.os.Parcelable;

public class StreamingService implements Parcelable {
    private String serviceName;
    private String serviceLink;

    public StreamingService(String serviceName, String serviceLink) {
        this.serviceName = serviceName;
        this.serviceLink = serviceLink;
    }

    public String getServiceName() {
        return serviceName;
    }
    public String toString() {
        return serviceName; // or any other meaningful representation
    }

    public String getServiceLink() {
        return serviceLink;
    }


    protected StreamingService(Parcel in) {
        serviceName = in.readString();
        serviceLink = in.readString();
    }

    public static final Creator<StreamingService> CREATOR = new Creator<StreamingService>() {
        @Override
        public StreamingService createFromParcel(Parcel in) {
            return new StreamingService(in);
        }

        @Override
        public StreamingService[] newArray(int size) {
            return new StreamingService[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(serviceName);
        dest.writeString(serviceLink);
    }
}


