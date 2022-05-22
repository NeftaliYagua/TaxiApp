package com.example.taxiapp.ui.fragment;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.example.taxiapp.BaseApplication;
import com.example.taxiapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;
import javax.inject.Inject;

public class ForgotPasswordFragment extends DialogFragment implements View.OnClickListener{

    private TextInputEditText forgotUser;
    private View fragmentView;

    @Inject FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NORMAL,R.style.Theme_TaxiApp);
        super.onCreate(savedInstanceState);
        ((BaseApplication)getActivity().getApplication()).getAppComponent().loginComponent().create().inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //fragmentView = inflater.inflate(R.layout.fragment_forgot_password,container,false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*forgotUser = view.findViewById(R.id.forgot_user);
        MaterialButton btnRestore = view.findViewById(R.id.btn_restore);
        ImageButton btnClose = view.findViewById(R.id.btn_close);
        forgotUser.setOnKeyListener((v, keyCode, event) -> {
            forgotUser.setError(null);
            return true;
        });
        btnRestore.setOnClickListener(this);
        btnClose.setOnClickListener(this);*/
        super.onViewCreated(view, savedInstanceState);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.btn_restore:
                String email = forgotUser.getText().toString().trim();
                if(!email.isEmpty()) {
                    firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<String> methods = task.getResult().getSignInMethods();
                            if (methods != null) {
                                String method = methods.get(0);
                                if (method.equals("google.com")) {
                                    Snackbar.make(fragmentView, R.string.google_user, Snackbar.LENGTH_SHORT).show();
                                } else {

                                }
                            } else
                                Snackbar.make(fragmentView, R.string.user_not_found, Snackbar.LENGTH_SHORT).show();
                        } else {
                            FirebaseException exception = (FirebaseException) task.getException();
                            Snackbar.make(fragmentView, exception.getMessage(), Snackbar.LENGTH_SHORT).show();
                            exception.printStackTrace();
                        }
                    });
                } else forgotUser.setError(getContext().getString(R.string.required_field));
                break;
            case R.id.btn_close:
                dismiss();
                break;
        }*/
    }

    public void show(FragmentManager fm) {
        show(fm,"DIALOG");
    }
}
