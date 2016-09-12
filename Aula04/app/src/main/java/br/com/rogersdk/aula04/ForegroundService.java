package br.com.rogersdk.aula04;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by rogerio on 11/09/16.
 */
public class ForegroundService extends Service {

    public static String EXTRA = "key1";
    private String TAG = "lifecycle";
    private int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, String.format("%s.%s", getClassName(), "onCreate()"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Intent theIntent = intent;

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
                showNotification(theIntent);
                theIntent.setAction(ServiceBroadcastReceiver.SERVICE_ACTION);
                return doTheJob();
            }

            /**
             * Método executado após o retorno do método doInBackground(),
             * este método está na main thread (primeiro plano)
             * */
            @Override
            protected void onPostExecute(Integer integer) {

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.cancel(NOTIFICATION_ID);

                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(ServiceBroadcastReceiver.SERVICE_ACTION);

                sendBroadcast(broadcastIntent);

                // para o serviço
                stopSelf();
                super.onPostExecute(integer);
            }
        }.execute();

        Log.d(TAG, String.format("%s.%s", getClassName(), "onStartCommand()"));

        return START_NOT_STICKY;
    }

    private void showNotification(Intent intent) {
        //        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

// build notification
// the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder(this)
                .setContentTitle("Notification Title")
                .setContentText("The content")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[]{0, 100, 100})
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "Action", pIntent).build();

        // Adicionando Actions
                /*.addAction(R.mipmap.ic_launcher, "More", pIntent)
                .addAction(R.mipmap.ic_launcher, "And more", pIntent).build();*/


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, n);
    }

    /**
     * Método que bloqueia a execução da main thread.
     * */
    private Integer doTheJob() {
        Integer result = new Integer(0);
        while (result < 5) {

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

        Log.d(TAG, String.format("%s.%s", getClassName(), "onDestroy()"));
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
