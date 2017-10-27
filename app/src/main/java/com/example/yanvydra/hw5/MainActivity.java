package com.example.yanvydra.hw5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final VersionController checker = new VersionController();
                if (!checker.isCorrectVersion()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            VersionDialog(alertDialogBuilder, checker);
                        }
                    });
                }

            }
        }).start();
    }

    void VersionDialog(AlertDialog.Builder alertDialogBuilder, VersionController checker) {
        if (checker.isForceUpdate()) {
            alertDialogBuilder
                    .setTitle("Necessary update")
                    .setMessage("Update application here: " + BuildConfig.UPDATE_URL);
        } else {
            alertDialogBuilder
                    .setTitle(" Update?")
                    .setMessage("You want to update application? ")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
        }
        alertDialogBuilder
                .setCancelable(false)
                .create()
                .show();
    }
}
