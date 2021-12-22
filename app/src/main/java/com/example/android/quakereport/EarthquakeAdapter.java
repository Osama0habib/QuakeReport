package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> objects) {
        super(context, 0, objects);
    }
    private static final String LOCATION_SEPARATOR = " of ";

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView = convertView ;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
         Earthquake currentEarthquake = getItem(position);
            TextView magTextView = listItemView.findViewById(R.id.magnitude);
            String formatMagnitude = formatMagnitude(currentEarthquake.getvMag());
            magTextView.setText(formatMagnitude);

            String originalLocation = currentEarthquake.getvLocation();
            String primaryLocation;
            String locationOffset;

            if (originalLocation.contains(LOCATION_SEPARATOR)){
                String[] parts = originalLocation.split(LOCATION_SEPARATOR);
                primaryLocation = parts[1] + LOCATION_SEPARATOR;
                locationOffset= parts[0];
            } else{
                locationOffset = "Near The" ;
                primaryLocation = originalLocation ;
            }

            TextView locationOffsetTextView = listItemView.findViewById(R.id.location_offset);
            locationOffsetTextView.setText(locationOffset);


        TextView primarylocationTextView = listItemView.findViewById(R.id.primary_location);
        locationOffsetTextView.setText(primaryLocation);
        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getvMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView with view ID date
        TextView dateView =  listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView =  listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);
            return listItemView;
    }

    private int getMagnitudeColor(double getvMag) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(getvMag);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

    }

}
