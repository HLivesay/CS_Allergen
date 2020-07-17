package com.example.allergen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.R.drawable;

import com.example.allergen.data.ItemArrayAdapter;
import com.example.allergen.model.DatabaseManager;
import com.example.allergen.model.PreferencesManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        //Get the users current allergen preferences
        int length = Integer.parseInt(PreferencesManager.getString("length"));
        final ArrayList<String> allergies = new ArrayList<>();
        for(int i=0;i<length;i++)
        {
            allergies.add(PreferencesManager.getString("allergy" + i));
        }


        Bundle extras = getIntent().getExtras();
        final String restID = extras.getString("rID");
        final String restName = extras.getString("rName");


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        myToolbar.setNavigationIcon(getResources().getDrawable(drawable.arrow_up_float));
        final RestaurantActivity selfRef = this;
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(myToolbar);
        TextView tv1 = (TextView)findViewById(R.id.activity_title);
        tv1.setText(restName);
        DatabaseManager.db.collection("restaurants").document(restID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                //List of items
                System.out.println(task.isSuccessful());
                if(task.getResult().exists())//We successfully found the restaurant populate the list view with its items
                {
                    System.out.println("Restaurant " + restName + " found in database");
                    System.out.println(task.getResult().toString());
                    ArrayList<String> items = (ArrayList<String>)task.getResult().get("items");
                    ArrayList<String> finalResult = new ArrayList<String>();
                    for(String s: items)
                    {
                        boolean contains = false;
                        for(String q: allergies)
                        {
                            if(s.contains(q))
                            {
                                contains=true;
                                break;
                            }
                        }
                        if(contains)
                        {
                            finalResult.add(s);
                        }
                    }
                    ItemArrayAdapter adapter = new ItemArrayAdapter(selfRef, finalResult);
                    ListView listview = (ListView)findViewById(R.id.itemList);
                    listview.setAdapter(adapter);


                }
                else //The database doesn't contain this restaurant yet so add it
                {
                    Map map = new HashMap<String, Object>();
                    map.put("name", restName);
                    map.put("items", new ArrayList<String>());
                    map.put("allergies", new ArrayList<String>());
                    DatabaseManager.db.collection("restaurants").document(restID).set(map);
                    System.out.println("Added restaurant " + restName + " to database");
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.addItem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Adding item");
                Intent intent = new Intent(selfRef, AddItemActivity.class);
                intent.putExtra("rID", restID);
                intent.putExtra("rName", restName);
                startActivity(intent);
            }
        });
    }
}