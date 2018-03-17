package br.heavendevelopment.sonschallenge;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.itangqi.waveloadingview.WaveLoadingView;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //pegar as informações do banco de quantas leituras o usuário já fez
        //na verdade, se os 3 booleanos estiverem true, ele completou o dia.

        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        //mWaveLoadingView.setAmplitudeRatio(); então faço um cálculo para saber a porcentagem de dia
        mWaveLoadingView.startAnimation();

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getBaseContext(),MainActivity.class));
            }
        }, 3000);
    }
}
