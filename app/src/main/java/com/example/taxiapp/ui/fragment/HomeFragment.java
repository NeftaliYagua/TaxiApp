package com.example.taxiapp.ui.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.taxiapp.R;
import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*MaterialButton mnuTriki = view.findViewById(R.id.mnu_triki);
        MaterialButton mnuCompleting = view.findViewById(R.id.mnu_completing);
        MaterialButton mnuHunting = view.findViewById(R.id.mnu_hunting);
        MaterialButton mnuTutorials = view.findViewById(R.id.mnu_tutorials);
        mnuTriki.setOnClickListener(this);
        mnuCompleting.setOnClickListener(this);
        mnuHunting.setOnClickListener(this);
        mnuTutorials.setOnClickListener(this);*/
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.mnu_triki:

                break;
            case R.id.mnu_completing:

                break;
            case R.id.mnu_hunting:
                break;
            case R.id.mnu_tutorials:
                break;
        }*/
    }
}
