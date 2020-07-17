package com.example.allergen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allergen.model.DatabaseManager;
import com.example.allergen.model.PreferencesManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button signupButton = findViewById(R.id.signup);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


        final LoginActivity selfRef = this;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                DatabaseManager.mAuth.signInWithEmailAndPassword(usernameEditText.getText().toString(), passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");

                            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                            final Intent intent = new Intent(selfRef, MainActivity.class);
                            DatabaseManager.db.collection("users").document(DatabaseManager.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    System.out.println(task.getResult().getId());
                                    ArrayList<String> allergies = (ArrayList<String>)task.getResult().get("allergies");
                                    PreferencesManager.storeString("length", Integer.toString(allergies.size()));
                                    for(int i=0;i<allergies.size();i++)
                                    {
                                        PreferencesManager.storeString("allergy"+i, allergies.get(i));
                                    }

                                    Log.i("Activity", "Moving to main activity" );
                                    startActivity(intent);
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Error signing in", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        final Intent intent = new Intent(this, SignupActivity.class);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Activity", "Moving to signup activity" );
                startActivity(intent);
            }
        });
    }
}