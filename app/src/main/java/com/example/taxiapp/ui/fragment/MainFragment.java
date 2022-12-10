package com.example.taxiapp.ui.fragment;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.taxiapp.R;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener{

    private ActivityResultLauncher<String[]> locationPermissionRequest;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        locationPermissionRequest = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                Boolean backgroundLocationGranted = null;
                Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseLocationGranted = coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,false);
                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.P) backgroundLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_BACKGROUND_LOCATION, false);
                if (fineLocationGranted == null || !fineLocationGranted || coarseLocationGranted == null || !coarseLocationGranted) requestLocationPermissions();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialCardView menuMaps = view.findViewById(R.id.mnu_map);
        MaterialCardView menuDrivers = view.findViewById(R.id.mnu_call);
        menuMaps.setOnClickListener(this);
        menuDrivers.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.mnu_map:
                int COARSE_PERMISSION = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
                int FINE_PERMISSION = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
                if (COARSE_PERMISSION == PackageManager.PERMISSION_DENIED || FINE_PERMISSION == PackageManager.PERMISSION_DENIED) {
                    showDialogPermissions();
                } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                    int BACKGROUND_PERMISSION = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION);
                    if (BACKGROUND_PERMISSION == PackageManager.PERMISSION_DENIED) showDialogPermissions();
                    else {
                        NavDirections action = MainFragmentDirections.actionMainFragmentToMapsFragment();
                        Navigation.findNavController(view).navigate(action);
                    }
                } else {
                    NavDirections action = MainFragmentDirections.actionMainFragmentToMapsFragment();
                    Navigation.findNavController(view).navigate(action);
                }
                break;

            case R.id.mnu_call:
                NavDirections action = MainFragmentDirections.actionMainFragmentToDriversFragment();
                Navigation.findNavController(view).navigate(action);
                break;
        }
    }

    private void showDialogPermissions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.location_permission);
        builder.setMessage(R.string.explanation_location_permission);
        builder.setIcon(R.mipmap.globe);
        builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        builder.setPositiveButton(R.string.allow, (dialogInterface, i) -> requestLocationPermissions());
        builder.show();
    }

    private void requestLocationPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) permissions.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        locationPermissionRequest.launch((String[]) permissions.toArray(new String[0]));
    }
}
