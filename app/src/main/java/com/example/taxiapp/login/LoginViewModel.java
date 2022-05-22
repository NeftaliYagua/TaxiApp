package com.example.taxiapp.login;
import android.content.Context;
import androidx.activity.result.ActivityResultRegistry;
import com.example.taxiapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import javax.inject.Inject;

public class LoginViewModel {

    private final FirebaseAuth firebaseAuth;

    @Inject
    public LoginViewModel(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public void loginWithEmailAndPassword(String email, String password, OnCompleteListener<AuthResult> listener) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public void googleSignIn(Context context, ActivityResultRegistry registry, OnCompleteListener<AuthResult> listener) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(context.getString(R.string.default_web_client_id)).requestEmail().build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
        GoogleSignInLauncher launcher = new GoogleSignInLauncher(registry,mGoogleSignInClient);
        launcher.authenticate(firebaseAuth,listener);;
    }
}