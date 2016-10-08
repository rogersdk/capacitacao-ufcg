/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package br.edu.ufcg.embedded.aula10;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import br.edu.ufcg.embedded.aula10.model.User;

/**
 * SessionManager.java
 *
 * Classe Singleton responsável pela sessão do usuário no aplicativo
 */
public class SessionManager {

    private static final String USER_SESSION_PREF = "user_session";
    private static final String USER_SESSION_KEY = "user";

    private static Context context;
    private static SessionManager instance;
    private final Gson gson;

    private SessionManager() {
        gson = new Gson();
    }

    public static SessionManager getInstance(Context ctx) {
        context = ctx;

        if(instance == null) {
            instance = new SessionManager();
        }

        return instance;
    }

    /**
     * Recupera o usuário que possui a sessão no aplicativo
     * */
    public User getUserSession() {

        SharedPreferences sharedPref = context.getSharedPreferences(
                USER_SESSION_PREF, Context.MODE_PRIVATE);

        String userString = sharedPref.getString(USER_SESSION_KEY, "");

        return gson.fromJson(userString, User.class);
    }

    /**
     * Verifica se existe alguma sessão
     * */
    public boolean isUserLogged() {

        SharedPreferences sharedPref = context.getSharedPreferences(
                USER_SESSION_PREF, Context.MODE_PRIVATE);

        String userString = sharedPref.getString(USER_SESSION_KEY, "");

        return !userString.equals("");
    }

    /**
     * Inicia a sessão para um usuário
     * */
    public void startUserSession(User user) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                USER_SESSION_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_SESSION_KEY, gson.toJson(user).toString());
        editor.commit();
    }

    /**
     * Encerra a sessão do usuário
     * */
    public void stopUserSession() {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_SESSION_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(USER_SESSION_KEY, "");
        editor.commit();
    }

}
