package com.example.taxiapp.registration;
import com.example.taxiapp.ActivityScope;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        RegistrationComponent create();
    }

    void inject(RegistrationActivity activity);
}