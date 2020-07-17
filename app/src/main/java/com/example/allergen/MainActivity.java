package com.example.allergen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.allergen.data.PlacesResponseAdapter;
import com.example.allergen.data.PlacesResponse_Restaurant;
import com.example.allergen.model.DatabaseManager;
import com.example.allergen.model.PlacesManager;
import com.example.allergen.model.PreferencesManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.util.Logger;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseManager.init();
        PreferencesManager.init(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Check if the user is logged in
        final Intent intent = new Intent(this, LoginActivity.class); //If we have to load the login activity we pre initialize it
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null) {
                    //Continue with this activity
                    System.out.println("User is already logged in");
                    System.out.println(DatabaseManager.getCurrentUser().getUid());
                    //initialize preferences
                    DatabaseManager.db.collection("users").document(DatabaseManager.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.getResult().exists()) {
                                System.out.println("Importing preferences");
                                ArrayList<String> allergies = (ArrayList<String>) task.getResult().get("allergies");
                                PreferencesManager.storeString("length", Integer.toString(allergies.size()));
                                for (int i = 0; i < allergies.size(); i++) {
                                    PreferencesManager.storeString("allergy" + i, allergies.get(i));
                                }
                            }
                        }
                    });
                } else {
                    //Load the login activity
                    Log.i("Activity", "Creating Login activity" );

                    startActivity(intent);
                }
            }
        };

        //Populate list view
        System.out.println("Populating list view");
        try {
            final ArrayList<PlacesResponse_Restaurant> myPlaces = (ArrayList<PlacesResponse_Restaurant>) PlacesManager.getRestaurantIDinRange(15);
            PlacesResponseAdapter arrayAdapter = new PlacesResponseAdapter(this, myPlaces);
            ListView lv = (ListView)findViewById(R.id.restaurantDisplayList);
            lv.setAdapter(arrayAdapter);
            final MainActivity selfReference = this;
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                // argument position gives the index of item which is clicked
                public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
                {
                    String selectedRestaurant=myPlaces.get(position).getId();
                    Toast.makeText(getApplicationContext(), "Restaurant Selected : "+selectedRestaurant,   Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(selfReference, RestaurantActivity.class);
                    intent.putExtra("rID", selectedRestaurant);
                    intent.putExtra("rName", myPlaces.get(position).getName());
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseManager.mAuth.addAuthStateListener(authStateListener);
    }
}
