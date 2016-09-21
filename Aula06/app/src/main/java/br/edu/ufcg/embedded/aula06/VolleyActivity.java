package br.edu.ufcg.embedded.aula06;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rogerio on 18/09/16.
 */
public class VolleyActivity extends AppCompatActivity {

    private static final String LOG_TAG = "volley";

    private ProgressBar mProgress;
    private Button mBtnGetPost, mBtnNewPost;
    private ViewGroup mPost;
    private static final String TAG = "async";
    private TextView mProgressText;
    private JsonObjectRequest getVolleyRequest;
    private StringRequest postVolleyRequest;
    private static final String VOLLEY_REQUEST_TAG = "MyRequestTag";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_volley);

        requestQueue = Volley.newRequestQueue(this);

        mProgress = (ProgressBar) findViewById(R.id.progress);
        mProgressText = (TextView) findViewById(R.id.progress_text);

        mBtnGetPost = (Button) findViewById(R.id.btn_get_post);
        mBtnNewPost = (Button) findViewById(R.id.btn_new_post);

        mPost = (ViewGroup) findViewById(R.id.post_item);



        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                hideProgress();
            }
        });

        mBtnGetPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // adiciona a requisicao à fila de envio
                requestQueue.add(getVolleyRequest());
            }
        });

        mBtnNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Simulate data from the View

                Post novoPost = new Post();
                novoPost.setUserId(22);
                novoPost.setTitle("Perigo e caos");
                novoPost.setBody("Posso pressentir, o perigo e o caos...");

                Gson gson = new Gson();

                String jsonString = gson.toJson(novoPost, Post.class);

                Log.d(LOG_TAG, jsonString);

                // adiciona a requisicao à fila de envio
                requestQueue.add(postVolleyRequest(jsonString));
            }
        });
    }

    private void showPost(JSONObject json) {
        try {

            // instancia as views
            TextView mUserId = (TextView) mPost.findViewById(R.id.user_id);
            TextView mPostId = (TextView) mPost.findViewById(R.id.post_id);
            TextView mTitle = (TextView) mPost.findViewById(R.id.post_title);
            TextView mBody = (TextView) mPost.findViewById(R.id.post_body);

            // seta os valores das views
            mUserId.setText(String.valueOf(json.getInt("userId")));
            mPostId.setText(String.valueOf(json.getInt("id")));
            mTitle.setText(String.valueOf(json.getString("title")));
            mBody.setText(String.valueOf(json.getString("body")));

            mPost.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showPost(Post post) {

        // instancia as views
        TextView mUserId = (TextView) mPost.findViewById(R.id.user_id);
        TextView mPostId = (TextView) mPost.findViewById(R.id.post_id);
        TextView mTitle = (TextView) mPost.findViewById(R.id.post_title);
        TextView mBody = (TextView) mPost.findViewById(R.id.post_body);

        // seta os valores das views
        mUserId.setText(String.valueOf(post.getUserId()));
        mPostId.setText(String.valueOf(post.getId()));
        mTitle.setText(post.getTitle());
        mBody.setText(post.getBody());


        mPost.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        mProgress.setVisibility(View.GONE);
        mProgressText.setVisibility(View.GONE);
    }

    private void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
        mProgressText.setVisibility(View.VISIBLE);
    }

    private void updateProgress(Integer newProgress) {
        mProgressText.setText(String.format("%d%%", newProgress));
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Cancela todas as requisicoes na fila
        if (requestQueue != null) {
            requestQueue.cancelAll(VOLLEY_REQUEST_TAG);
        }
    }

    private JsonObjectRequest getVolleyRequest() {
        // tipo pronto de requisicao Json sem precisar recriar varias vezes a mesma funcionalidade
        getVolleyRequest = new JsonObjectRequest
                (Request.Method.GET,
                        "http://jsonplaceholder.typicode.com/posts/5",
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                showPost(response);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        // adiciona uma tag para a requisicao, util na hora de cancelar as requests
        getVolleyRequest.setTag(VOLLEY_REQUEST_TAG);

        return getVolleyRequest;
    }

    private StringRequest postVolleyRequest(String data) {
        postVolleyRequest = new NovoPostRequest(
                data,
                Request.Method.POST,
                "http://jsonplaceholder.typicode.com/posts/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

                        // da um parse do response para a Post.class
                        Post newPost = gson.fromJson(response, Post.class);

                        Log.d(LOG_TAG, newPost.toString());

                        // exibe as informacoes retornadas do POST
                        showPost(newPost);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        postVolleyRequest.setTag(VOLLEY_REQUEST_TAG);

        return postVolleyRequest;
    }


}
