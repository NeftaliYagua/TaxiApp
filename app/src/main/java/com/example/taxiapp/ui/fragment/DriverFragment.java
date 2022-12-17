package com.example.taxiapp.ui.fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.taxiapp.R;
import com.example.taxiapp.entity.ProfilePhoto;
import com.example.taxiapp.entity.User;

public class DriverFragment extends Fragment {

    private User user = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int sdkInt = Build.VERSION.SDK_INT;
        Bundle arguments = getArguments();
        if (arguments!=null) user = sdkInt > Build.VERSION_CODES.S_V2 ? arguments.getSerializable("user", User.class): (User) getArguments().getSerializable("user");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_driver, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView profilePhoto = view.findViewById(R.id.profilePhoto);
        TextView tvUsername = view.findViewById(R.id.tvUsername);

        if (user!=null) {
            ProfilePhoto photo = user.getPhoto();
            Glide.with(view.getContext()).load(photo!=null?photo.getUrl():R.mipmap.user).transform(new CircleCrop()).into(profilePhoto);
            tvUsername.setText(user.getUsername());
        }
    }
}
