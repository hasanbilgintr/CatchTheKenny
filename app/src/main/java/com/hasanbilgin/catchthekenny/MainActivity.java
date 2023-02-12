package com.hasanbilgin.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView zamanTextView, puanTextView;
    ImageView picture1imageView, picture2imageView, picture3imageView, picture4imageView, picture5imageView, picture6imageView, picture7imageView, picture8imageView, picture9imageView, picture10imageView, picture11imageView, picture12imageView, picture13imageView, picture14imageView, picture15imageView, picture16imageView, picture17imageView, picture18imageView, picture19imageView, picture20imageView, picture21imageView, picture22imageView, picture23imageView, picture24imageView, picture25imageView;
    ImageView[] array;
    int puan;
    int zaman;
    Runnable runnable;
    Handler handler;
    Button startButton, againButton;
    CountDownTimer gerisay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
    }

    private void tanimla() {
        zamanTextView = findViewById(R.id.zamanTextView);
        puanTextView = findViewById(R.id.puanTextView);
        picture1imageView = findViewById(R.id.picture1imageView);
        picture2imageView = findViewById(R.id.picture2imageView);
        picture3imageView = findViewById(R.id.picture3imageView);
        picture4imageView = findViewById(R.id.picture4imageView);
        picture5imageView = findViewById(R.id.picture5imageView);
        picture6imageView = findViewById(R.id.picture6imageView);
        picture7imageView = findViewById(R.id.picture7imageView);
        picture8imageView = findViewById(R.id.picture8imageView);
        picture9imageView = findViewById(R.id.picture9imageView);
        picture10imageView = findViewById(R.id.picture10imageView);
        picture11imageView = findViewById(R.id.picture11imageView);
        picture12imageView = findViewById(R.id.picture12imageView);
        picture13imageView = findViewById(R.id.picture13imageView);
        picture14imageView = findViewById(R.id.picture14imageView);
        picture15imageView = findViewById(R.id.picture15imageView);
        picture16imageView = findViewById(R.id.picture16imageView);
        picture17imageView = findViewById(R.id.picture17imageView);
        picture18imageView = findViewById(R.id.picture18imageView);
        picture19imageView = findViewById(R.id.picture19imageView);
        picture20imageView = findViewById(R.id.picture20imageView);
        picture21imageView = findViewById(R.id.picture21imageView);
        picture22imageView = findViewById(R.id.picture22imageView);
        picture23imageView = findViewById(R.id.picture23imageView);
        picture24imageView = findViewById(R.id.picture24imageView);
        picture25imageView = findViewById(R.id.picture25imageView);
        againButton = findViewById(R.id.againButton);
        startButton = findViewById(R.id.startButton);
        array = new ImageView[]{picture1imageView, picture2imageView, picture3imageView, picture4imageView, picture5imageView, picture6imageView, picture7imageView, picture8imageView, picture9imageView, picture10imageView, picture11imageView, picture11imageView, picture12imageView, picture13imageView, picture14imageView, picture15imageView, picture16imageView, picture17imageView, picture17imageView, picture18imageView, picture19imageView, picture19imageView, picture20imageView, picture21imageView, picture22imageView, picture23imageView, picture24imageView, picture25imageView};
        puan = 0;
        puanTextView.setText("Puan: " + puan);
        zaman = 0;
        zamanTextView.setText("Zaman: " + zaman);


    }

    public void startButtonOnClick(View view) {
        zamansay();
        fulgizleveac();
        startButton.setEnabled(false);
    }

    public void againButtonOnClick(View view) {
        againplay();
    }

    public void againplay() {
        handler.removeCallbacks(runnable);
        puan = 0;
        puanTextView.setText("Puan: " + puan);
        zaman = 0;
        zamanTextView.setText("Zaman: " + zaman);
        for (ImageView pictures : array) {
            pictures.setVisibility(View.VISIBLE);
        }
        gerisay.cancel();//geri sayarı durdurur

        startButton.setEnabled(true);

        /*
        //tabi aktivasyonu yenide başlatabilirizde
        Intent intent=getIntent();
        finish();
        startActivity(intent);*/
    }

    public void fulgizleveac() {
        handler = new Handler(Looper.myLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView pictures : array) {
                    pictures.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int index = random.nextInt(25);
                array[index].setVisibility(View.VISIBLE);

                //runnable = new Runnable() { bunu gördüğü için direk this denilebilir
                //handler.postDelayed(this, 1000);
                handler.postDelayed(runnable, 1000);
            }
        };
        handler.post(runnable);
    }

    public void zamansay() {
        gerisay = new CountDownTimer(10000/*10sn sayması için 10000 yazıldı*/, 1000/*kaçar kaçar sayması gerekiyorsa 1000 yazdık yani 1sn 1 sn sayıcak*/) {
            @Override
            public void onTick(long millisUntilFinished) {
                zaman = (int) (millisUntilFinished / 1000);
                zamanTextView.setText("Zaman: " + zaman);
            }

            @Override
            public void onFinish() {
                //10sn bitiminde bu metod çalşır

                handler.removeCallbacks(runnable);
                puan = 0;
                puanTextView.setText("Puan: " + puan);
                zaman = 0;
                zamanTextView.setText("Zaman: " + zaman);
                for (ImageView pictures : array) {
                    pictures.setVisibility(View.VISIBLE);
                }
                gerisay.cancel();//geri sayarı durdurur
                Toast.makeText(MainActivity.this, "zaman bitti", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Tekrarla");
                alert.setMessage("Yeniden Oynayalımmı? :)");
                alert.setCancelable(false);
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        zamansay();
                        fulgizleveac();
                        startButton.setEnabled(false);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.show();
                startButton.setEnabled(true);
            }
        }.start();
    }

    public void imageOnClick(View view) {
        if (!startButton.isEnabled()) {
            puan++;
            puanTextView.setText("Puan: " + puan);
        }

    }
}


