package com.iwa.iwatesting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iwa.iwatesting.R;
import com.iwa.iwatesting.model.User;
import com.iwa.iwatesting.util.ImagineLoadService;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.DataViewHolder> {

    private List<User> users;
    private Context context;
    private OnItemClick callback;

    public UsersAdapter(Context c, List<User> users, OnItemClick callback) {
        this.context = c;
        this.users = users;
        this.callback = callback;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, final int position) {
        String avaUrl = users.get(position).getAvatar_url();
        String username = users.get(position).getLogin();
        holder.title.setText(username);
        if (avaUrl.isEmpty()) {
            holder.imgAvatar.setVisibility(View.INVISIBLE);
            return;
        }
        new ImagineLoadService(avaUrl, holder.imgAvatar).execute();
        if (callback != null) {
            holder.items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClick(view, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView title;
        LinearLayout items;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            items = (LinearLayout) itemView.findViewById(R.id.itemUser);
        }
    }

}


