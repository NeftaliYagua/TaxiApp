package com.example.taxiapp.registration;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.taxiapp.BaseApplication;
import com.example.taxiapp.MainActivity;
import com.example.taxiapp.R;
import com.example.taxiapp.entity.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import javax.inject.Inject;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText signUpName;
    private TextInputEditText signUpEmail;
    private TextInputEditText signUpPassword;
    private LinearLayout animationLayout;
    private RelativeLayout activityLayout;
    @Inject FirebaseFirestore firebaseFirestore;
    @Inject FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registration);
        RegistrationComponent registrationComponent = ((BaseApplication) getApplication()).getAppComponent().registrationComponent().create();
        registrationComponent.inject(this);
        /*animationLayout = findViewById(R.id.animation_layout);
        activityLayout = findViewById(R.id.activity_layout);
        signUpName = findViewById(R.id.sign_up_name);
        signUpEmail = findViewById(R.id.sign_up_email);
        signUpPassword = findViewById(R.id.sign_up_password);
        MaterialButton btnRegister = findViewById(R.id.btn_register);
        MaterialButton btnLogin = findViewById(R.id.btn_login);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);*/
    }

    private void changeVisibility(boolean showAnimation) {
        animationLayout.setVisibility(showAnimation?View.VISIBLE:View.GONE);
        activityLayout.setVisibility(showAnimation?View.GONE:View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.btn_register:
                if(checkInput(signUpName)&&checkInput(signUpEmail)&&checkInput(signUpPassword)) {
                    changeVisibility(true);
                    String name = signUpName.getText().toString().trim();
                    String email = signUpEmail.getText().toString().trim();
                    String password = signUpPassword.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();
                            User user = new User(uid,name,email,password,null);
                            firebaseFirestore.collection("users").document(uid).set(user).addOnCompleteListener(storeTask->{
                                if(storeTask.isSuccessful()) {
                                    UserProfileChangeRequest.Builder upcrb = new UserProfileChangeRequest.Builder();
                                    upcrb.setDisplayName(user.getName());
                                    firebaseAuth.getCurrentUser().updateProfile(upcrb.build());
                                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    changeVisibility(false);
                                    FirebaseException exception = (FirebaseException) task.getException();
                                    Snackbar.make(findViewById(R.id.activity_registration),exception.getMessage(),Snackbar.LENGTH_SHORT).show();
                                    exception.printStackTrace();
                                }
                            });
                        } else {
                            changeVisibility(false);
                            FirebaseException exception = (FirebaseException) task.getException();
                            Snackbar.make(findViewById(R.id.activity_registration),exception.getMessage(),Snackbar.LENGTH_SHORT).show();
                            exception.printStackTrace();
                        }
                    });
                }
                break;
            case R.id.btn_login: case R.id.btn_close:onBackPressed();break;
        }*/
    }

    @SuppressLint("NonConstantResourceId")
    private boolean checkInput(TextInputEditText input) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(signUpName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(signUpEmail.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(signUpPassword.getWindowToken(), 0);
        String content = input.getText().toString().trim();
        boolean valid = true;
        if(content.isEmpty()) {
            input.setError(getString(R.string.required_field));
            valid = false;
        }
        else {
            /*switch (input.getId()) {
                case R.id.sign_up_email:
                    if(!User.isValidEmail(content)) {
                        input.setError(getString(R.string.invalid_email));
                        valid = false;
                    }
                    break;
                case R.id.sign_up_password:
                    if(content.length()<8) {
                        input.setError(getString(R.string.invalid_password));
                        valid = false;
                    }
                    break;
                case R.id.sign_up_name:
                    if(content.length()<10) {
                        input.setError(getString(R.string.invalid_name));
                        valid = false;
                    }
                    break;
            }*/
        }
        return valid;
    }
}
