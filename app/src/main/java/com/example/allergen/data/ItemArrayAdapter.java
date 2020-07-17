package com.example.allergen.data;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class ItemArrayAdapter extends GenericArrayAdapter<String> {
    public ItemArrayAdapter(Context context, ArrayList<String> objects) {
        super(context, objects);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void drawText(TextView textView, String object) {
        String dishName = object.split("|")[0];
        String[] allerges = object.split("|")[1].split(",");

        textView.setText(object);
    }
}
