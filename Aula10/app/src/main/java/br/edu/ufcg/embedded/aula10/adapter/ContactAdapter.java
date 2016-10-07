/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package br.edu.ufcg.embedded.aula10.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
 * ContactAdapter.java
 *
 * Classe responsável pelo binding (ligação entre resource e view) de dados específicos da aplicação
 * (Contact) que será exibido em um RecyclerView.
 *
 * View.OnCreateContextMenuListener - Listener responsável pelo Long Click e exibição do menu de
 * remoção do dado (ContextMenu).
 *
 * MenuItem.OnMenuItemClickListener - Listener responsável pelo evento do click na opção do
 * ContextMenu.
 * Created by rogerio on 05/10/16.
 */
public class ContactAdapter
        extends RecyclerView.Adapter<ContactAdapter.ViewHolder>
            implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    // Lista de contatos
    private List<Contact> contacts;

    // Contexto passado para o Adapter
    final private Context contexto;

    // Contato selecionado da lista
    private Contact selectedContact;

    public ContactAdapter(Context mCallback, List<Contact> contacts) {
        this.contexto = mCallback;
        this.contacts = contacts;
    }

    /**
     * Responsável por criar novas views (invocado pelo layout manager)
     */
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_contact, parent, false);

        ContactAdapter.ViewHolder vh = new ViewHolder(v, this);

        return vh;
    }

    /**
     * Chamado pelo RecyclerView para exibir os dados em uma posição específica. Este método deve
     * atualizar o conteúdo do itemView que é um determinado item em uma determinada posição.
     * */
    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {

        Contact contact = contacts.get(position);

        holder.mContactName.setText(contact.getName());
        holder.mContactEmail.setText(contact.getEmail());

        holder.itemView.setTag(position);
    }

    /**
     * Retorna o total de itens nos dados referenciados no adapter. Deve-se retornar o número total
     * de dados.
     *
     * */
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    /**
     * Chamado quando o ContextMenu para esta view está sendo construído. Não é indicado que você
     * mantenha referência para o menu depois que este método retorne.
     *
     * Refs - https://developer.android.com/guide/topics/ui/menus.html
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem remove = menu.add(Menu.NONE, 1, 1, "Remover");
        remove.setOnMenuItemClickListener(this);

        selectedContact = contacts.get(new Integer(v.getTag().toString()));
    }

    /**
     * Chamado quando um item do menu foi invocado (ativado). Este é o primeiro código que é executado;
     * se retornar TRUE, nenhum outro callback será executado.
     *
     * */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(selectedContact != null) {
            final Map<String, String> params = new HashMap<>();
            params.put("id", selectedContact.getId());

            GsonPostRequest<StringApiResponse> delete = new GsonPostRequest<>(
                    ApiManager.getInstance().getContactsResource(),
                    StringApiResponse.class,
                    params,
                    new Response.Listener<StringApiResponse>() {
                        @Override
                        public void onResponse(StringApiResponse response) {
                            if(response != null) {
                                Toast.makeText(contexto,
                                        response.toString(), Toast.LENGTH_SHORT).show();
                                contacts.remove(selectedContact);
                                notifyDataSetChanged();
                                selectedContact = null;
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(contexto,
                                    error.getMessage(), Toast.LENGTH_SHORT).show();
                            selectedContact = null;
                        }
                    }
            );


            Volley.newRequestQueue(contexto).add(delete).setTag("remove");
        }

        return true;
    }

    /**
     * Um ViewHolder descreve uma view de item no RecyclerView.
     * */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.item_contact_name)
        TextView mContactName;

        @BindView(R.id.item_contact_email)
        TextView mContactEmail;

        public ViewHolder(View v, View.OnCreateContextMenuListener contextMenuListener) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnCreateContextMenuListener(contextMenuListener);
        }


    }

}
