package com.on29studio.ekazpraw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_layout);
		
		/**
         * Membuat tombol kejadian
         * */
        // Dashboard Tombol lamp
        Button btn_lamp = (Button) findViewById(R.id.btn_lamp);
         
        // Dashboard Tombol help
        Button btn_help = (Button) findViewById(R.id.btn_help);
         
        // Dashboard Tombol about
        Button btn_about = (Button) findViewById(R.id.btn_about);
         
        // Dashboard Tombol exit
        Button btn_exit = (Button) findViewById(R.id.btn_exit);
         
        /**
         * Menangani klik events pada tombol
         * */
         
        // Menangkap klik event tombol lamp
        btn_lamp.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Menampilkan screen berita
                Intent i = new Intent(getApplicationContext(), LampActivity.class);
                startActivity(i);
            }
        });
         
       // Menangkap klik event tombol help
        btn_help.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Menampilkan screen teman
                Intent i = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(i);
            }
        });
         
        // Menangkap klik event tombol about
        btn_about.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Menampilkan screen pesan
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
            }
        });
         
        // Menangkap klik event tombol exit
        btn_exit.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                close();
            }
        });
    }

    public void close(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda yakin ingin keluar? ")
                .setCancelable(false)
                .setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick (DialogInterface dialog, int id){
                                DashboardActivity.this.finish();


                                // TODO Auto-generated method stub

                            }
                        })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }


                }).show();
    }

    public boolean onKeyDown (int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            close();
        }
        return super.onKeyDown(keyCode, event);
    }
}
