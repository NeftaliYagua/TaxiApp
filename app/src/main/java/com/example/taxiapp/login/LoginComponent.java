package com.example.taxiapp.login;
import com.example.taxiapp.ActivityScope;
import com.example.taxiapp.ui.fragment.ForgotPasswordFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        LoginComponent create();
    }

    void inject(LoginActivity activity);

    void inject(ForgotPasswordFragment forgotPasswordFragment);
}
