package com.example.taxiapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import com.example.taxiapp.entity.User;
import com.example.taxiapp.ui.fragment.MainFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {

    @Inject FirebaseFirestore firebaseFirestore;
    @Inject SharedPreferences preferences;
    @Inject FirebaseAuth firebaseAuth;
    MainFragment mainFragment;
    FirebaseAuth.AuthStateListener authStateListener;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_TaxiApp);
        setContentView(R.layout.activity_main);

        ((BaseApplication) getApplication()).getAppComponent().inject(MainActivity.this);
        /*authStateListener = firebaseAuth -> {
            if(firebaseAuth.getCurrentUser()==null) {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        };*/
        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,mainFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //@Override protected void onStart(){firebaseAuth.addAuthStateListener(authStateListener);super.onStart();}
}