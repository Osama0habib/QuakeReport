package com.example.android.quakereport;


public class Earthquake {
    private double mMag ;
    private String mLocation ;
    private long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake(double vMag, String vLocation, long TimeInMilliseconds, String url) {
        mMag = vMag ;
        mLocation = vLocation ;
        mTimeInMilliseconds = TimeInMilliseconds ;
        mUrl = url;
    }

    public double getvMag() { return mMag; }

    public String getvLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() { return mTimeInMilliseconds; }

    public String getmUrl() { return mUrl; }
}
