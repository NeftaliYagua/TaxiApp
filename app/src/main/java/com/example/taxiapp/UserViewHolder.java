package com.example.taxiapp;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.taxiapp.entity.ProfilePhoto;
import com.example.taxiapp.entity.User;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvUsername, tvPhone;
    private final ImageView profilePhoto;
    private final Context context;
    private User user;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            Navigation.findNavController(view).navigate(R.id.action_driversFragment_to_driverFragment,bundle);
        });
        context = itemView.getContext();
        tvUsername = itemView.findViewById(R.id.tvUsername);
        tvPhone = itemView.findViewById(R.id.tvPhone);
        profilePhoto = itemView.findViewById(R.id.profilePhoto);
    }

    public void loadData(User user) {
        this.user = user;
        tvUsername.setText(user.getUsername());
        tvPhone.setText(user.getPhone());
        ProfilePhoto photo = user.getPhoto();
        Glide.with(context).load(photo!=null?photo.getUrl():R.mipmap.user).transform(new CircleCrop()).into(profilePhoto);
    }
}
