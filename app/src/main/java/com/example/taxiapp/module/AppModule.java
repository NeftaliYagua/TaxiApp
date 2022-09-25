package com.example.taxiapp.module;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import com.example.taxiapp.R;
import com.example.taxiapp.ui.fragment.MainFragment;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public Fragment provideMainFragment() {
        return new MainFragment();
    }
}
