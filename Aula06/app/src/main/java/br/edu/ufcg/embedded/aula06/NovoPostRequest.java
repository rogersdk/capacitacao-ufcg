/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package br.edu.ufcg.embedded.aula06;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rogerio on 21/09/16.
 */
public class NovoPostRequest extends StringRequest {

    private static final String DATA_KEY = "data";

    private Gson gson = new Gson();
    private Map<String, String> mParams;

    public NovoPostRequest(String data, int method, String url, Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);

        mParams = new HashMap<>();
        mParams.put(DATA_KEY, data);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String jsonStringResponse = null;
        try {
            // retorna a string completa de resposta vinda da requisição
            jsonStringResponse = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));

            // Instancia um novo JSONObject com a resposta
            JSONObject jsonObject = new JSONObject(jsonStringResponse);

            // Recupera cada objeto separadamente e atribui a uma string cada
            // Adaptação técnica para funcionar com o POST do placeholder api
            String postString = jsonObject.getString(DATA_KEY);
            int newId = jsonObject.getInt("id");

            // Cria o JsonObject para a resposta com os valores corretos e ajustados
            JSONObject postJson = new JSONObject(postString);

            // Modifica o valor do ID para o retornado na resposta
            postJson.put("id", newId);

            // Retorna a string formatada corretamente no formato abaixo
            // {"body":"","id":"101","title":"","userId":""}
            jsonStringResponse = postJson.toString();

            return Response.success(jsonStringResponse, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("volley",String.format("parse -> %s", jsonStringResponse));
        return super.parseNetworkResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
