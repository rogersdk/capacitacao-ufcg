package br.edu.ufcg.embedded.aula06;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by rogerio on 18/09/16.
 */
public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgress;
    private Button mBtnGetPost;
    private ViewGroup mPost;
    private static final String TAG = "async";
    private TextView mProgressText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progress);
        mProgressText = (TextView) findViewById(R.id.progress_text);

        mBtnGetPost = (Button) findViewById(R.id.btn_get_post);

        mPost = (ViewGroup) findViewById(R.id.post_item);

        mBtnGetPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetPostAsyncTask().execute("http://jsonplaceholder.typicode.com/posts/1");
            }
        });

    }

    /**
     * GetPostAsyncTask.java
     *
     * Inner class responsavel por realizar a requisicao
     *
     * Params String        - Url a ser acessada
     * Progress Integer     - Inteiro representando o progresso
     * Result JSONObject    - Objeto json com os dados
     *
     * */
    public final class GetPostAsyncTask extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress();

            Log.d(TAG, String.format("GetPostAsyncTask.%s", "onPreExecute()"));

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            Log.d(TAG, String.format("GetPostAsyncTask.%s progress é: %d%%", "onProgressUpdate()",
                    values[0]));

            // atualiza o progresso
            updateProgress(values[0]);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            Log.d(TAG, String.format("GetPostAsyncTask.%s", "doInBackground()"));
            JSONObject json = null;
            while(true) {
                try {

                    // apenas para mostrar que iniciou
                    publishProgress(1);

                    // apenas para simular um delay
                    Thread.sleep(1000);
                    Log.d(TAG, String.format("GetPostAsyncTask.%s", "->running..."));

                    // busca o dado se nao for cancelada
                    if(!isCancelled()){

                        URL url = new URL(strings[0]);
                        URLConnection urlConnection = url.openConnection();

                        // simulando o progresso
                        Thread.sleep(1000);
                        publishProgress(50);

                        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

                        // verifica se o result code da resposta http é 200
                        if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                            InputStream is = urlConnection.getInputStream();

                            BufferedReader reader = new BufferedReader(new InputStreamReader(
                                    is), 8);

                            StringBuilder sb = new StringBuilder();
                            String line = null;

                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                            is.close();

                            // instancia o json object com a string de resposta
                            json = new JSONObject(sb.toString());

                            // apenas para simular um delay
                            Thread.sleep(1000);
                            publishProgress(100);

                            Thread.sleep(500);
                            return json;
                        } else {
                            cancel(true);
                        }


                    } else {
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    cancel(true);
                } catch (IOException e) {
                    e.printStackTrace();
                    cancel(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                    cancel(true);
                } catch (Exception e) {
                    cancel(true);
                }

            }

            return json;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d(TAG, String.format("GetPostAsyncTask.%s", "onCancelled()"));

            hideProgress();

        }

        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);
            Log.d(TAG, String.format("GetPostAsyncTask.%s", "onPostExecute()"));

            // passa o json para o método que seta os valores na view
            showPost(json);

            // esconde o progress
            hideProgress();
        }
    }

    private void showPost(JSONObject json) {
        try {

            // instancia as views
            TextView mUserId = (TextView) mPost.findViewById(R.id.user_id);
            TextView mPostId = (TextView) mPost.findViewById(R.id.post_id);
            TextView mTitle = (TextView) mPost.findViewById(R.id.post_title);
            TextView mBody = (TextView) mPost.findViewById(R.id.post_body);

            // instancia um objeto Gson
            Gson gson = new Gson();

            // da o parse do jsonString, lembrando que utilizei a requisicao JsonObjectRequest
            // poderia ter utilizado uma StringRequest do Volley, pois não precisa de dar o parse
            // sabendo-se que ja utilizo uma biblioteca de parse
            Post post = gson.fromJson(json.toString(), Post.class);

            // seta os valores das views
            mUserId.setText(String.valueOf(post.getUserId()));
            mPostId.setText(String.valueOf(post.getId()));
            mTitle.setText(String.valueOf(post.getTitle()));
            mBody.setText(String.valueOf(post.getBody()));

            mPost.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


}
