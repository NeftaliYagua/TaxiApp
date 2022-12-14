package com.example.taxiapp;
import android.content.Context;
import com.example.taxiapp.login.LoginComponent;
import com.example.taxiapp.module.AppModule;
import com.example.taxiapp.module.FirebaseModule;
import com.example.taxiapp.module.SubcomponentsModule;
import com.example.taxiapp.registration.RegistrationComponent;
import com.example.taxiapp.ui.fragment.DriversFragment;

import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, FirebaseModule.class, SubcomponentsModule.class})
public interface AppComponent {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }

    RegistrationComponent.Factory registrationComponent();

    LoginComponent.Factory loginComponent();

    void inject(MainActivity activity);

    void inject(DriversFragment fragment);
}
