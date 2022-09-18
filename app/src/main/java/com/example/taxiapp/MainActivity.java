package com.example.taxiapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridLayout;
import com.example.taxiapp.entity.User;
import com.example.taxiapp.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject FirebaseFirestore firebaseFirestore;
    @Inject SharedPreferences preferences;
    @Inject FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_TaxiApp);
        setContentView(R.layout.activity_main);

        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        authStateListener = firebaseAuth -> {
            if(firebaseAuth.getCurrentUser()==null) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        };
        GridLayout container = findViewById(R.id.menu_container);

    }

    //@Override protected void onStart(){firebaseAuth.addAuthStateListener(authStateListener);super.onStart();}
}