package com.example.taxiapp;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.example.taxiapp.entity.User;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class FirestoreAdapter extends RecyclerView.Adapter<UserViewHolder> implements EventListener<QuerySnapshot> {

    private static final String TAG = "Firestore Error";
    private final List<User> userList;

    public FirestoreAdapter(Query query) {
        this.userList = new ArrayList<>();
        query.addSnapshotListener(this);
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException error) {
        // Handle errors
        if (error != null) {
            Log.w(TAG, "onEvent:error", error);
            return;
        }

        if (documentSnapshots != null) {
            for (DocumentChange change : documentSnapshots.getDocumentChanges()) {

                DocumentChange.Type type = change.getType();

                if (type == DocumentChange.Type.ADDED) onDocumentAdded(change);
                else if (type == DocumentChange.Type.MODIFIED) onDocumentModified(change);
                else if (type == DocumentChange.Type.REMOVED) onDocumentRemoved(change);
            }
        }
    }

    private void onDocumentAdded(DocumentChange change) {
        User user = change.getDocument().toObject(User.class);
        userList.add(change.getNewIndex(), user);
        notifyItemInserted(change.getNewIndex());
    }

    private void onDocumentModified(DocumentChange change) {
        if (change.getOldIndex() == change.getNewIndex()) {
            userList.set(change.getOldIndex(), change.getDocument().toObject(User.class));
            notifyItemChanged(change.getOldIndex());
        } else {
            userList.remove(change.getOldIndex());
            userList.add(change.getNewIndex(), change.getDocument().toObject(User.class));
            notifyItemMoved(change.getOldIndex(), change.getNewIndex());
        }
    }

    private void onDocumentRemoved(DocumentChange change) {
        userList.remove(change.getOldIndex());
        notifyItemRemoved(change.getOldIndex());
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.loadData(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
