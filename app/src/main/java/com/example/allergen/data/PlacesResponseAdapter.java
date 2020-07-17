package com.example.allergen.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.allergen.R;

import java.util.ArrayList;

public class PlacesResponseAdapter extends GenericArrayAdapter<PlacesResponse_Restaurant> {
    public PlacesResponseAdapter(Context context, ArrayList<PlacesResponse_Restaurant> objects) {
        super(context, objects);
    }

    @Override
    public void drawText(TextView textView, PlacesResponse_Restaurant object) {
        textView.setText(object.getName());
    }
}
