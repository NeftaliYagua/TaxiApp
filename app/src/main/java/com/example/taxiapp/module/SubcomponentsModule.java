package com.example.taxiapp.module;
import com.example.taxiapp.login.LoginComponent;
import com.example.taxiapp.registration.RegistrationComponent;
import dagger.Module;

@Module(subcomponents = {LoginComponent.class, RegistrationComponent.class})
public class SubcomponentsModule {}