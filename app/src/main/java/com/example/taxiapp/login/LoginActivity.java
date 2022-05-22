package com.example.taxiapp.login;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.taxiapp.AppComponent;
import com.example.taxiapp.BaseApplication;
import com.example.taxiapp.MainActivity;
import com.example.taxiapp.R;
import com.example.taxiapp.entity.User;
import com.example.taxiapp.registration.RegistrationActivity;
import com.example.taxiapp.ui.fragment.ForgotPasswordFragment;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText loginUser;
    private TextInputEditText loginPassword;
    private LinearLayout animationLayout;
    private RelativeLayout activityLayout;
    @Inject SharedPreferences preferences;
    @Inject LoginViewModel loginViewModel;
    @Inject FirebaseAuth firebaseAuth;
    @Inject FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppComponent appComponent = ((BaseApplication) getApplication()).getAppComponent();
        LoginComponent loginComponent = appComponent.loginComponent().create();
        loginComponent.inject(this);
        loginUser = findViewById(R.id.login_user);
        loginPassword = findViewById(R.id.login_password);
        //animationLayout = findViewById(R.id.animation_layout);
        //activityLayout = findViewById(R.id.activity_layout);
        MaterialButton btnSignUp = findViewById(R.id.btn_sign_up);
        MaterialButton btnLogin = findViewById(R.id.btn_login);
        MaterialButton btnForgotPassword = findViewById(R.id.btn_forgot_password);
        SignInButton googleSignInButton = findViewById(R.id.google_sign_in_button);
        ImageButton btnClose = findViewById(R.id.btn_close);
        googleSignInButton.setSize(SignInButton.SIZE_STANDARD);
        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);
        googleSignInButton.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    private void changeVisibility(boolean showAnimation) {
        animationLayout.setVisibility(showAnimation?View.VISIBLE:View.GONE);
        activityLayout.setVisibility(showAnimation?View.GONE:View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;

            case R.id.btn_login:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(loginUser.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(loginPassword.getWindowToken(), 0);
                changeVisibility(true);
                String email = loginUser.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                if(email.isEmpty()) {
                    loginUser.setError(getString(R.string.required_field));
                    changeVisibility(false);
                } else if(!User.isValidEmail(email)) {
                    loginUser.setError(getString(R.string.invalid_email));
                    changeVisibility(false);
                }
                else if(password.isEmpty()) {
                    loginPassword.setError(getString(R.string.required_field));
                    changeVisibility(false);
                } else {
                    loginViewModel.loginWithEmailAndPassword(email, password, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String uid = firebaseUser.getUid();
                            firebaseFirestore.collection("users").document(uid).get().addOnCompleteListener(t ->{
                                if(t.isSuccessful()) {
                                    User user = t.getResult().toObject(User.class);
                                    UserProfileChangeRequest.Builder upcrb = new UserProfileChangeRequest.Builder();
                                    //upcrb.setDisplayName(user.getName());
                                    firebaseUser.updateProfile(upcrb.build());
                                    preferences.edit().putString("login",user.toString()).apply();
                                    startActivity(new Intent(this, MainActivity.class));
                                    finish();
                                } else {
                                    changeVisibility(false);
                                    FirebaseFirestoreException exception = (FirebaseFirestoreException) t.getException();
                                    Snackbar.make(findViewById(R.id.activity_login),exception.getMessage(),Snackbar.LENGTH_SHORT).show();
                                    exception.printStackTrace();
                                }
                            });

                        } else {
                            changeVisibility(false);
                            FirebaseException exception = (FirebaseException) task.getException();
                            View view = findViewById(R.id.activity_login);
                            Snackbar.make(view, exception.getMessage(), Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
                break;

            case R.id.btn_forgot_password: (new ForgotPasswordFragment()).show(getSupportFragmentManager());break;

            case R.id.google_sign_in_button:
                changeVisibility(true);
                loginViewModel.googleSignIn(this, getActivityResultRegistry(), task -> {
                    if(task.isSuccessful()) {
                        FirebaseUser firebaseUser = task.getResult().getUser();
                        if(firebaseUser!=null) {
                            String photoUri = firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().getLastPathSegment() : null;
                            User user = new User();//User(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail(), null, photoUri);
                            firebaseFirestore.collection("users").document(user.getUid()).get().addOnCompleteListener(findTask -> {
                                if (findTask.isSuccessful()) {
                                    User u = findTask.getResult().toObject(User.class);
                                    if (u == null) {
                                        firebaseFirestore.collection("users").document(user.getUid()).set(user).addOnCompleteListener(finalTask -> {
                                            if (finalTask.isSuccessful()) {
                                                preferences.edit().putString("login",user.toString()).apply();
                                                startActivity(new Intent(this, MainActivity.class));
                                                finish();
                                            } else {
                                                changeVisibility(false);
                                                FirebaseFirestoreException exception = (FirebaseFirestoreException) finalTask.getException();
                                                Snackbar.make(findViewById(R.id.activity_login), exception.getMessage(), Snackbar.LENGTH_SHORT).show();
                                                exception.printStackTrace();
                                            }
                                        });
                                    } else {
                                        preferences.edit().putString("login",user.toString()).apply();
                                        startActivity(new Intent(this, MainActivity.class));
                                        finish();
                                    }
                                } else {
                                    changeVisibility(false);
                                    FirebaseFirestoreException exception = (FirebaseFirestoreException) findTask.getException();
                                    Snackbar.make(findViewById(R.id.activity_login), exception.getMessage(), Snackbar.LENGTH_SHORT).show();
                                    exception.printStackTrace();
                                }
                            });
                        }
                    } else {
                        changeVisibility(false);
                        FirebaseException exception = (FirebaseException) task.getException();
                        Snackbar.make(findViewById(R.id.activity_login),exception.getMessage(),Snackbar.LENGTH_SHORT).show();
                        exception.printStackTrace();
                    }
                });
                break;

            case R.id.btn_close:finish();break;
        }
    }
}
