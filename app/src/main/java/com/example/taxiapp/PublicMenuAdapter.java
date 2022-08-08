package com.example.taxiapp;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PublicMenuAdapter extends RecyclerView.Adapter<PublicMenuViewHolder> {

    private Context context;

    public PublicMenuAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public PublicMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PublicMenuViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
