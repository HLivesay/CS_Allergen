package com.example.allergen;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.allergen.model.DatabaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity {
    public ArrayList<String> allergens = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Bundle extras = getIntent().getExtras();
        final String restID = extras.getString("rID");
        final String restName = extras.getString("rName");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setNavigationIcon(getResources().getDrawable(android.R.drawable.arrow_up_float));
        final AddItemActivity selfRef = this;
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(myToolbar);
        TextView tv1 = (TextView)findViewById(R.id.activity_title);


        //Array adapter stuff
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allergens);
        ListView listView = (ListView)findViewById(R.id.currentAllergenList);
        listView.setAdapter(adapter);

        final EditText field = (EditText)findViewById(R.id.addAllergyText);
        final EditText field2 = (EditText)findViewById(R.id.addDishText);
        field.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                allergens.add(v.getText().toString());
                                adapter.notifyDataSetChanged();

                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });

        Button button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add new item to database then navigate back to the previous activity
                DatabaseManager.db.collection("restaurants").document(restID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        ArrayList<String> items = (ArrayList)task.getResult().get("items");
                        items.add(field2.getText().toString() + "|" + String.join(",", allergens));
                        Map map = new HashMap<String, Object>();
                        map.put("items", items);
                        DatabaseManager.db.collection("restaurants").document(restID).update(map);
                    }
                });


                Intent intent = new Intent(selfRef, RestaurantActivity.class);
                intent.putExtra("rID", restID);
                intent.putExtra("rName", restName);
                startActivity(intent);
            }
        });
    }
}