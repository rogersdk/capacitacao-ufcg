package br.com.rogersdk.aula04;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * BoundService.java
 *
 * Bound service utilizado para interagir com os clientes (activity...)
 *
 * Created by rogerio on 11/09/16.
 */
public class BoundService extends Service {

    // Binder passado para os clientes (Activity)
    private final IBinder mBinder = new MyBinder();

    // Gerador de número automático
    private final Random mGenerator = new Random();
    private static final String TAG = "lifecycle";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, String.format("%s.%s", getClassName(), "onCreate()"));
    }

    /**
     * Necessário para se criar um Bound Service
     * */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, String.format("%s.%s", getClassName(), "onBind()"));
        return mBinder;
    }


    /**
     * Necessário para se desvincular ao Serviço
     * */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, String.format("%s.%s", getClassName(), "onUnbind()"));
        return super.onUnbind(intent);
    }

    /**
     * Classe usada para o cliente (Activity) vinculado, ou seja, Binder. Por saber que este
     * serviço sempre roda no mesmo processo que seu cliente, nós não precisamos lidar com
     * IPC - Inter Process Communication
     *
     * IPC = A comunicação entre processos, é o grupo de mecanismos que permite aos processos
     * transferirem informação entre si. - Wikipedia
     *
     */
    public class MyBinder extends Binder {

        BoundService getService() {
            // Retorna esta instancia de BoundService para então os clientes chamar seus métodos
            // públicos
            return BoundService.this;
        }
    }

    /**
     * Método utilizado pelo cliente
     * */
    public int generateRandomNumber(int interval) {
        return mGenerator.nextInt(interval);
    }


    public String getClassName() {
        String className = getClass().getName();
        return (className.substring(className.lastIndexOf(".") + 1));
    }
}
