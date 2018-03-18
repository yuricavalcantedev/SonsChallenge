package br.heavendevelopment.sonschallenge.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.widget.Toast;

import com.valdesekamdem.library.mdtoast.MDToast;

import br.heavendevelopment.sonschallenge.R;
import br.heavendevelopment.sonschallenge.Service.LeituraService;
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

        LeituraService leituraService = new LeituraService(this);
        int desafiosCompletados = (int) leituraService.getPorCentagemDesafio();

        mWaveLoadingView.setProgressValue(desafiosCompletados);
        mWaveLoadingView.startAnimation();

        MDToast.makeText(getBaseContext(),"Oi amor, eu te amo sabia?", Toast.LENGTH_LONG,MDToast.TYPE_INFO).show();


        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getBaseContext(),MainActivity.class));
            }
        }, 3000);
    }
}
