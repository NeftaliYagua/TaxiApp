package com.example.taxiapp.login;
import android.app.Activity;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleSignInLauncher implements LifecycleObserver {
    private final ActivityResultRegistry registry;
    private final GoogleSignInClient signInClient;

    public GoogleSignInLauncher(ActivityResultRegistry registry, GoogleSignInClient signInClient) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        this.registry = registry;
        this.signInClient = signInClient;
    }

    public void authenticate(FirebaseAuth firebaseAuth, OnCompleteListener<AuthResult> listener) {
        ActivityResultLauncher<Intent> launcher = registry.register("GoogleSignInLauncher", new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Task<GoogleSignInAccount> googleSignInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                GoogleSignInAccount account = googleSignInAccountTask.getResult();
                if(account!=null) {
                    AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(listener);
                }
            }
        });
        launcher.launch(signInClient.getSignInIntent());
    }
}
