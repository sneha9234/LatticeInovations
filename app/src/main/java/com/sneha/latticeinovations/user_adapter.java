package com.sneha.latticeinovations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class user_adapter extends RecyclerView.Adapter<user_adapter.ViewHolder> {

    private List<user> users;
    private Context context;
    String vId;

    public user_adapter(List<user> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public user_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull user_adapter.ViewHolder holder, int position) {

        final user u = users.get(position);

        holder.nametxt.setText(u.getName());
        holder.addresstxt.setText(u.getAddress());
        holder.emailtxt.setText(u.getAddress());
        holder.phonetxt.setText(u.getAddress());
        holder.passwordtxt.setText(u.getAddress());
        holder.locationtxt.setText(u.getAddress());
    }

    public void updateList(List<user> list){
        users = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nametxt, addresstxt, emailtxt, phonetxt, passwordtxt, locationtxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nametxt = itemView.findViewById(R.id.nametxt);
            addresstxt = itemView.findViewById(R.id.addresstxt);
            emailtxt = itemView.findViewById(R.id.emailtxt);
            phonetxt = itemView.findViewById(R.id.phonetxt);
            passwordtxt = itemView.findViewById(R.id.passwordtxt);
            locationtxt = itemView.findViewById(R.id.locationtxt);
        }
    }
}

