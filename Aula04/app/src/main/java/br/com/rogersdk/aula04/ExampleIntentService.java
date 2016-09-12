package br.com.rogersdk.aula04;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * ExampleIntentService.java
 *
 * Gerencia uma Intent por vez.
 *
 * Não necessita implementar o método onStartCommand(), pois possui uma implementação própria da
 * implementação e envia toda a chamada para a Worker Thread (background).
 *
 * Created by rogerio on 11/09/16.
 */
public class ExampleIntentService extends IntentService {
    public static String EXTRA = "key1";
    private String TAG = "lifecycle";
    private int extra;


    /**
     * Um construtor é necessário, e necessita chamar o método super() de IntentService(String),
     * um construtor com um nome para a worker thread.
     *
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public ExampleIntentService() {
        super("ExampleIntentService");
    }


    /**
     * O IntentService chama este método da worker thread padrão com a intent que iniciou o serviço.
     * Quando este método retorna, IntentService para o serviço, apropriadamente.
     *
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        doTheJob();

        extra = Integer.parseInt(intent.getExtras().get(EXTRA).toString());
        Log.d(TAG, String.format("The Extra value is %d", extra));
    }

    /**
     * Método que bloqueia a execução da main thread.
     * */
    private Integer doTheJob() {
        Integer result = new Integer(0);
        while (result < 3) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result++;

            Log.d(TAG, String.format("->Incrementando valor result = %d", result));
        }

        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, String.format("%s.%s->%d", getClassName(), "onDestroy()", extra));
    }

    public String getClassName() {
        String className = getClass().getName();
        return (className.substring(className.lastIndexOf(".") + 1));
    }
}
