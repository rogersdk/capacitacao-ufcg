/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package br.edu.ufcg.embedded.aula10.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ufcg.embedded.aula10.R;
import br.edu.ufcg.embedded.aula10.api.ApiManager;
import br.edu.ufcg.embedded.aula10.api.GsonPostRequest;
import br.edu.ufcg.embedded.aula10.api.StringApiResponse;
import br.edu.ufcg.embedded.aula10.model.Contact;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rogerio on 05/10/16.
 */
public class ContactAdapter
        extends RecyclerView.Adapter<ContactAdapter.ViewHolder>
            implements View.OnLongClickListener {

    private List<Contact> contacts;

    private OnContactSelectedListener mCallback;

    public ContactAdapter(OnContactSelectedListener mCallback, List<Contact> contacts) {
        this.mCallback = mCallback;
        this.contacts = contacts;
    }

        // Create new views (invoked by the layout manager)
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_contact, parent, false);

        ContactAdapter.ViewHolder vh = new ViewHolder(v);

        v.setOnLongClickListener(this);

        return vh;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {

        Contact contact = contacts.get(position);

        holder.mContactName.setText(contact.getName());
        holder.mContactEmail.setText(contact.getEmail());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public boolean onLongClick(final View view) {

        final Contact contact = contacts.get(new Integer(view.getTag().toString()));

//        https://developer.android.com/guide/topics/ui/menus.html#CAB

        mCallback.onContactToRemoveSelected();

        final Map<String, String> params = new HashMap<>();
        params.put("id", contact.getId());

        GsonPostRequest<StringApiResponse> delete = new GsonPostRequest<>(
                ApiManager.getInstance().getContactsResource(),
                StringApiResponse.class,
                params,
                new Response.Listener<StringApiResponse>() {
                    @Override
                    public void onResponse(StringApiResponse response) {
                        if(response != null) {
                            Log.d("remove", String.format("Contact %s %s",params.get("id"),response.toString()));
//                            redirectToMain(response);
                            Toast.makeText(view.getContext(),
                                    response.toString(), Toast.LENGTH_SHORT).show();
                            contacts.remove(contact);
                            notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(view.getContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
//                        hideProgress();
                    }
                }
        );

        Volley.newRequestQueue(view.getContext()).add(delete).setTag("removeContact");

        return false;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.item_contact_name)
        TextView mContactName;

        @BindView(R.id.item_contact_email)
        TextView mContactEmail;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public interface OnContactSelectedListener {
        void onContactSelected(Contact contact);
        void onContactToRemoveSelected();
    }

}
