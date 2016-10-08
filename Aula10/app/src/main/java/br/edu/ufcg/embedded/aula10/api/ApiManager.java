package br.edu.ufcg.embedded.aula10.api;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.embedded.aula10.model.User;

/**
 * ApiManager.java
 *
 * Classe Singleton responsável por registrar as principais informações do Serviço, tais como:
 * HOST e RECURSOS.
 *
 *
 */
public class ApiManager {

    private static final String host = "http://ec2-52-67-176-213.sa-east-1.compute.amazonaws.com:9000/api/v1/";

    private static final String getUserResource = "users/";
    private static final String getContactsResource = "users/:userId/contacts";
    private static final String loginResource = "users/login";
    private static final String addContact = "contacts/create";
    private static final String editContact = "contacts/update";
    private static final String contacts = "contacts/remove";

    static ApiManager instance;

    private ApiManager() {

    }

    public static ApiManager getInstance() {

        if(instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    public String getUser(String id) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(getUserResource);
        buffer.append(id);


        return buffer.toString();
    }

    public String getGetContacts(String userId) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(getContactsResource.replace(":userId", userId));

        return buffer.toString();
    }

    public String getLoginResource() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(loginResource);

        return buffer.toString();
    }

    public String getAddContactResource() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(addContact);

        return buffer.toString();
    }

    public String getEditContactResource() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(editContact);

        return buffer.toString();
    }

    public String getContactsResource() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(contacts);

        return buffer.toString();
    }

}
