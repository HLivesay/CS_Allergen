package com.example.allergen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allergen.data.User;
import com.example.allergen.model.DatabaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Activity", "Calling signup activity class" );

        super.onCreate(savedInstanceState);
        Log.i("Activity", "Calling signup activity class 2" );

        setContentView(R.layout.activity_signup);

        final Button signupButton = findViewById(R.id.signup);
        final EditText usernameEditText = findViewById(R.id.signup_email);
        final EditText passwordEditText = findViewById(R.id.signup_password);
        final EditText firstNameEditText = findViewById(R.id.signup_first_name);
        final EditText lastNameEditText = findViewById(R.id.signup_last_name);

        final Intent intent = new Intent(this, MainActivity.class);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();
                final String firstname = firstNameEditText.getText().toString();
                final String lastname = lastNameEditText.getText().toString();

                if(email=="" || email==null)
                    return;

                if(password=="" || password==null)
                    return;

                final User u = new User(firstname, lastname, new ArrayList<String>());
                Task q = DatabaseManager.mAuth.createUserWithEmailAndPassword(email, password);
                q.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            final FirebaseUser user =  DatabaseManager.mAuth.getCurrentUser();

                            //Get the unique token for this user and store their data in the users database
                            user.getIdToken(true).addOnCompleteListener(
                                    new OnCompleteListener<GetTokenResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                                            if(task.isSuccessful()){
                                                u.ID = task.getResult().getToken();

                                                final Map<String, Object> userData = new HashMap<String, Object>();
                                                userData.put("firstname", firstname);
                                                userData.put("lastname", lastname);
                                                ArrayList<String> allergens = new ArrayList<String>();
                                                allergens.add("Gluten");
                                                allergens.add("Seafood");
                                                userData.put("allergies", allergens);
                                                userData.put("uid", user.getUid());
                                                DatabaseManager.db.collection("users").document(user.getUid())
                                                        .set(userData)
                                                        .addOnSuccessListener(new OnSuccessListener() {
                                                            @Override
                                                            public void onSuccess(Object o) {
                                                                displayToast("Welcome" + userData.get("firstname"));
                                                                Log.i("Activity", "Moving to main activity" );
                                                                startActivity(intent);
                                                            }
                                                        });
                                            }
                                            else {
                                                Log.w(TAG, "getIdToken:failure", task.getException());
                                                displayToast("Data storage failure");
                                            }
                                        }
                                    }
                            );
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            displayToast("Signin failure");
                        }
                    }
                });
            }
        });
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}