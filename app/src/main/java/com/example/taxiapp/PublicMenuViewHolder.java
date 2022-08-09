package com.example.taxiapp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

public class PublicMenuViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView tvTitle, tvDescription;
    private MaterialButton actionButton;
    private Context context;

    public PublicMenuViewHolder(@NonNull Context context, @NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.card_image);
        tvTitle = itemView.findViewById(R.id.card_title);
        tvDescription = itemView.findViewById(R.id.card_description);
        actionButton = itemView.findViewById(R.id.card_action_button);
        //actionButton.setText();
        this.context = context;
    }

    public void setImage(int resource) {
        imageView.setImageResource(resource);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setDescription(String description) {
        tvDescription.setText(description);
    }
}
