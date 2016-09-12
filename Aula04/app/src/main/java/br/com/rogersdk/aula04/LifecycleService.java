package br.com.rogersdk.aula04;


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * LifecycleService.java
 *
 * Started service criado para demonstrar o ciclo de vida de um serviço.
 *
 * Created by rogerio on 10/09/16.
 */
public class LifecycleService extends Service {

    public static String EXTRA = "key1";

    private static final String TAG = "lifecycle";


    /**
     * Chamado antes do onStartCommand()
     * Utilizado para fazer configurações iniciais do serviço.
     *
     * */
    @Override
    public void onCreate() {
        Log.d(TAG, String.format("%s.%s", getClassName(), "onCreate()"));
        super.onCreate();
    }

    /**
     * Chamado após onCreate() e quando o sistema recriar o serviço.
     *
     * @return START_STICKY -   O sistema recria o serviço e chama onStartCommand(), com intent nula.
     *                          ideal para utilizar quando não está se executando comandos, porém
     *                          encontra-se à espera de algo.
     * @return START_NOT_STICKY -   o sistema não recria o serviço, a não ser que exista alguma
     *                              intent pendente a ser enviada. Melhor opção, seu App que controla.
     * */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        /**
         * Verifica se existe intent, quando dá sua execução pela Activity.
         *
         * Se a Activity for destruída, o serviço é recriado, porém a Intent é nula.
         * */
        if( intent != null) {
            String extra = (String) intent.getExtras().get(EXTRA);
            Toast.makeText(getApplicationContext(),
                    String.format("Extra value is %s", extra), Toast.LENGTH_SHORT).show();
        }


        /**
         * NÃO executem blocking operations na main thread, evite ANR's
         * */
//        doTheJob();

        /**
         * Como um serviço executa na main thread, deve-se criar uma nova para executar operações
         * que bloqueam o fluxo (blocking operations), ou operações de network
         * */
        new AsyncTask<Void, Void, Integer>() {

            /**
             * Execução de tarefa em background, fora da main thread (segundo plano)
             *
             * */
            @Override
            protected Integer doInBackground(Void... params) {
                return doTheJob();
            }

            /**
             * Método executado após o retorno do método doInBackground(),
             * este método está na main thread (primeiro plano)
             * */
            @Override
            protected void onPostExecute(Integer integer) {

                // para o serviço
                stopSelf();
                super.onPostExecute(integer);
            }
        }.execute();

        Log.d(TAG, String.format("%s.%s", getClassName(), "onStartCommand()"));

        return START_STICKY;
    }

    /**
     * Método que bloqueia a execução da main thread.
     * */
    private Integer doTheJob() {
        Integer result = new Integer(0);
        while (result < 10) {

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
        Log.d(TAG, String.format("%s.%s", getClassName(), "onDestroy()"));
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public String getClassName() {
        String className = getClass().getName();
        return (className.substring(className.lastIndexOf(".") + 1));
    }

}
