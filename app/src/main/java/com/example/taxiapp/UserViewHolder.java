package com.example.taxiapp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.taxiapp.entity.ProfilePhoto;
import com.example.taxiapp.entity.User;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvUsername, tvPhone;
    private final ImageView profilePhoto;
    private final Context context;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        tvUsername = itemView.findViewById(R.id.tvUsername);
        tvPhone = itemView.findViewById(R.id.tvPhone);
        profilePhoto = itemView.findViewById(R.id.profilePhoto);
    }

    @SuppressLint("SetTextI18n")
    public void loadData(User user) {
        tvUsername.setText(user.getName()+" "+user.getLastName());
        tvPhone.setText(user.getPhone());
        ProfilePhoto photo = user.getPhoto();
        Glide.with(context).load(photo!=null?photo.getUrl():R.mipmap.user).transform(new CircleCrop()).into(profilePhoto);
    }
}
