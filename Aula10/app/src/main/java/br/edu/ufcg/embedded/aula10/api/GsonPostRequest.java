/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package br.edu.ufcg.embedded.aula10.api;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import br.edu.ufcg.embedded.aula10.exception.ApiCallException;

/**
 * Created by rogerio on 05/10/16.
 */
public class GsonPostRequest<T>  extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;
    private final Object data;

    /**
     * Make a POST request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonPostRequest(Object data, String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.data = data;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return headers;
    }

    @Override
    protected void deliverResponse(T response) {

        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {

            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(
                    new ApiCallException(
                            new String(response.data).replace("\"","")));
        }
    }
}
