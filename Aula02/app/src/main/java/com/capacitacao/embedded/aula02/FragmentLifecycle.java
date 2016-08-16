package com.capacitacao.embedded.aula02;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * FragmentLifecycle.java
 *
 * Fragment que demonstra o ciclo de vida de um fragment, assim como todos os seus
 * principais callbacks.
 *
 * Created by rogerio on 16/08/16.
 */
public class FragmentLifecycle extends Fragment {

    private static final String TAG = "lifecycle";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onCreate()"));
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onStart()"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lifecycle, container, false);


        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onCreateView()"));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onResume()"));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onAttach()"));
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onDetach()"));
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onPause()"));
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onStop()"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, String.format("Fragment - %s passou por %s", TAG, "onDestroy()"));
    }
}
