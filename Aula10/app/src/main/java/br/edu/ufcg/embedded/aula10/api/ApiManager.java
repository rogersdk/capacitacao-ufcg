package br.edu.ufcg.embedded.aula10.api;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.embedded.aula10.model.User;

/**
 * Created by rogerio on 04/10/16.
 */
public class ApiManager {

    private static final String host = "http://ec2-52-67-176-213.sa-east-1.compute.amazonaws.com:9000/api/v1/";

    private static final String getUserResource = "users/";
    private static final String getContactsResource = "users/:userId/contacts";
    private static final String loginResource = "users/login";
    private static final String addContact = "contacts/create";

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

    public GsonPostRequest login(RequestQueue queue, String email, String password) {
        Log.d("json", email + " / " + password);

        User user = new User(email, password);

        Map<String, String> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());

        GsonPostRequest<User> post = new GsonPostRequest<>(
                getLoginResource(),
                User.class,
                params,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        Log.d("json", "Chegou -> " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("json", "Error -> " + error.getMessage());
                    }
                }
        );

//        queue.add(post);

        return post;
    }

}
