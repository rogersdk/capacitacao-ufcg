/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package aula05.embedded.capacitacao.com.aula05;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rogerio on 14/09/16.
 */
public class StorageActivity extends AppCompatActivity {

    private Button mBtnInternal, mBtnExternal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_storage);


        mBtnInternal = (Button) findViewById(R.id.btn_internal);
        mBtnExternal = (Button) findViewById(R.id.btn_external);

        mBtnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFileIntoInternalStorage();
            }
        });


        mBtnExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFileIntoExternalStorage();
            }
        });
    }


    public void createFileIntoInternalStorage() {
        Log.d("storage", String.format("Saving file..."));


        try {
            String filename = "myfile.txt";
            String string = "Hello world!";
            FileOutputStream outputStream;

            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();

            Log.d("storage", String.format("The file is %s", getFilesDir()));
        } catch (Exception e) {
            Log.e("storage", String.format("Error", e.toString()));
            e.printStackTrace();
        }

    }


    public void createFileIntoExternalStorage() {
        if(isExternalStorageWritable()) {
            Log.d("storage", String.format("Saving file..."));
            // Get the directory for the user's public pictures directory.

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }

            File root = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "turma02");

            Log.d("storage", String.format("Can write: %b", root.isDirectory()));

            if(!root.isDirectory()) {
                root.mkdir();
            } else {

                try {
                    String filename = "myfile.txt";
                    String text = "Hello World";

                    File file = new File(root, filename);
                    FileOutputStream out = new FileOutputStream(file);
                    out.write(text.getBytes());
                    out.close();

                    Log.d("storage", String.format("The file is %s", file.toString()));
                } catch (IOException e) {
                    Log.e("storage", String.format("Error: %s", e.toString()));
                    e.printStackTrace();
                }

            }
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }




}
