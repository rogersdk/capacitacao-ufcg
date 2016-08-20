package com.capacitacao.embedded.aula03;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * UserListAdapter.java
 *
 * RecyclerView.Adapter criado para exibir as informações de UserModel
 *
 * Created by rogerio on 20/08/16.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    //declares a list
    private List<UserModel> users = new ArrayList<>();

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserListAdapter(List<UserModel> users) {
        this.users = users;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user, parent, false);

        // set the view's size, margins, paddings and layout parameters
        // ...

        //set the click listener
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(UserListAdapter.ViewHolder holder, int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final UserModel user = users.get(position);

        holder.setName(user.getName());
        holder.setEmail(user.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), String.format("Olá %s", user.getName()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.users.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mUserName;
        TextView mUserEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);

            mUserName = (TextView) itemView.findViewById(R.id.list_item_user_name);
            mUserEmail = (TextView) itemView.findViewById(R.id.list_item_user_email);
        }

        public void setName(String name) {
            mUserName.setText(name);
        }

        public void setEmail(String email) {
            mUserEmail.setText(email);
        }


    }
}
