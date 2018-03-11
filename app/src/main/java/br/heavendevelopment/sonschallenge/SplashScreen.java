package br.heavendevelopment.sonschallenge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.activeandroid.ActiveAndroid;

import br.heavendevelopment.sonschallenge.Model.Leitura;
import me.itangqi.waveloadingview.WaveLoadingView;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final int DIA_INICIO_DESAFIO = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActiveAndroid.initialize(this);


        sharedPreferences = getSharedPreferences("splashPref",MODE_PRIVATE);

        boolean firstTime = sharedPreferences.getBoolean("firstTime",false);

        if(!firstTime){

            String [] referencias = {"","","","","","","","","","",
                    "","","","","","","","","","",
                    "","","","","","","","","","",
                    "","","","","","","","","","",
                    "","","","","","","","","","",
                    "","","","","","","","","","",
                    "","","","","","","","","","",
                    "","","","","","","","","","",

            };

            //para não ter que criar um array enorme com datas, eu criei essa váriavél auxíliar que vai começar com o número 15
            //se referenciando ao dia 15 de março. Então faço uma ligação com o "i" para ele ir zerando sempre que acabar um mês.
            int diaDataAux = DIA_INICIO_DESAFIO;


            ActiveAndroid.beginTransaction();
            try {
                for (int i = 1; i < 80; i++) {

                    Leitura leitura = new Leitura();
                    leitura.setDia(i); //porque o i já vai de 1 até 80.
                    leitura.setReferencia(referencias[i]);
                    leitura.setLeitura(false);
                    leitura.setDesafio(false);
                    leitura.setOracao(false);

                    if(i < 17)
                        leitura.setData(diaDataAux + "/3/18");
                    else if (i == 17) {
                        leitura.setData(diaDataAux + "/3/18");
                        diaDataAux = 0; // 0 porque lá em baixo ele é incrementado.
                    }else if(i < 46)
                        leitura.setData(diaDataAux + "/4/18");
                    else if (i == 47) {
                        leitura.setData(diaDataAux + "/4/18");
                        diaDataAux = 0; // 0 porque lá em baixo ele é incrementado.
                    }
                    else if(i < 77)
                        leitura.setData(diaDataAux + "/5/18");
                    else if (i == 78) {
                        leitura.setData(diaDataAux + "/5/18");
                        diaDataAux = 0; // 0 porque lá em baixo ele é incrementado.
                    }else{
                        leitura.setData(diaDataAux + "/6/18");
                    }

                    leitura.save();

                    //incrementa a variável auxiliar para setar as datas
                    diaDataAux++;
                }

                ActiveAndroid.setTransactionSuccessful();
            }
            finally {
                ActiveAndroid.endTransaction();
            }

            editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",true);
            editor.apply();

        }

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
