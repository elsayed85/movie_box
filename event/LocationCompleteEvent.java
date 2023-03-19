package com.movieboxpro.android.event;
/* loaded from: classes3.dex */
public class LocationCompleteEvent {
    private double latitude;
    private String location;
    private double longitude;

    public LocationCompleteEvent(double d, double d2, String str) {
        this.latitude = d;
        this.longitude = d2;
        this.location = str;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }
}
