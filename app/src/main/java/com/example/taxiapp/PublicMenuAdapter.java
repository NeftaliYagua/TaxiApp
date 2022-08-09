package com.example.taxiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.taxiapp.entity.Assets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PublicMenuAdapter extends RecyclerView.Adapter<PublicMenuViewHolder> {

    private final Context context;
    private final JSONArray menus;

    public PublicMenuAdapter(Context context) {
        this.context = context;
        menus = Assets.publicMenus(context);

    }

    @NonNull
    @Override
    public PublicMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new PublicMenuViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicMenuViewHolder holder, int position) {
        try {
            JSONObject menu = menus.getJSONObject(position);
            holder.setTitle(menu.getString("title"));
            holder.setDescription(menu.getString("description"));
            holder.setImage(R.mipmap.map_location);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return menus.length();
    }
}
