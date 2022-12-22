package com.example.taxiapp.ui.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.taxiapp.BaseApplication;
import com.example.taxiapp.R;
import com.example.taxiapp.entity.ProfilePhoto;
import com.example.taxiapp.entity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import javax.inject.Inject;

public class MyProfileFragment extends Fragment {

    @Inject
    FirebaseFirestore firestore;

    @Inject
    FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication)getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView profilePhoto = view.findViewById(R.id.profilePhoto);
        ImageButton btnFileChooser = view.findViewById(R.id.btnFileChooser);
        TextView tvUsername = view.findViewById(R.id.tvUsername);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        btnFileChooser.setOnClickListener(v -> {

        });
        if (firebaseUser!=null) {
            firestore.document("/users/" +firebaseUser.getUid()).get().addOnSuccessListener(documentSnapshot -> {
                User user = documentSnapshot.toObject(User.class);
                if (user != null){
                    ProfilePhoto photo = user.getPhoto();
                    tvUsername.setText(user.getUsername());
                    Glide.with(view.getContext()).load(photo.getUrl()).transform(new CircleCrop()).into(profilePhoto);
                }
            }).addOnFailureListener(e -> {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            });
        }
    }
}
