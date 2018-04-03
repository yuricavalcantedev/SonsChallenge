package br.heavendevelopment.sonschallenge.Activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.valdesekamdem.library.mdtoast.MDToast;

import br.heavendevelopment.sonschallenge.Model.Leitura;
import br.heavendevelopment.sonschallenge.R;
import br.heavendevelopment.sonschallenge.Service.LeituraService;

import static com.valdesekamdem.library.mdtoast.MDToast.LENGTH_LONG;
import static com.valdesekamdem.library.mdtoast.MDToast.LENGTH_SHORT;
import static com.valdesekamdem.library.mdtoast.MDToast.TYPE_SUCCESS;

public class VerDiaLeituraActivity extends AppCompatActivity {

    private CheckBox cbLeitura;
    private CheckBox cbOracao;
    private CheckBox cbDesafio;
    Context context;
    private Leitura leitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_dia_leitura);

//        MDToast.makeText(getBaseContext(),"Eu te amo e quero cuidar de você, amar você, proteger você e principalmente te levar para mais perto de Jesus pelo resto da minha vida!", Toast.LENGTH_LONG,MDToast.TYPE_INFO).show();

        context = this;

        int dia = getIntent().getIntExtra("dia",1);

        final LeituraService leituraService = new LeituraService(this);
        leitura = leituraService.getLeituraDia(dia);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_verDiaLeitura);
        toolbar.setTitle("Desafio"); // ou o nome do devocional
        toolbar.setSubtitle(leitura.getData());
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView tvMsg = (TextView) findViewById(R.id.tv_msg_ver_leitura);
        cbLeitura = (CheckBox) findViewById(R.id.cb_leitura);
        cbOracao = (CheckBox) findViewById(R.id.cb_oracao);
        cbDesafio = (CheckBox) findViewById(R.id.cb_desafio);

        final boolean leituraRealizada;
        boolean oracaoRealizada;
        boolean desafioRealizado = false;
        int desafioLeitura = leitura.getDesafio();

        if(leitura.getLeitura() == 1)
            leituraRealizada = true;
        else
            leituraRealizada = false;

        if(leitura.getOracao() == 1)
            oracaoRealizada = true;
        else
            oracaoRealizada = false;

        if(desafioLeitura == 0) {
            cbDesafio.setEnabled(false);
            cbDesafio.setVisibility(View.INVISIBLE);
        }else if(desafioLeitura == 7)
            desafioRealizado = true;
        else
            desafioRealizado = false;


        cbLeitura.setChecked(leituraRealizada);
        cbOracao.setChecked(oracaoRealizada);
        cbDesafio.setChecked(desafioRealizado);

        if(desafioLeitura == 1)
            cbDesafio.setText("Demonstrar seu amor ao Espírito Santo de hora em hora");
        else if(desafioLeitura == 2)
            cbDesafio.setText("Compartilhar texto e/ou frase");
        else if(desafioLeitura == 3)
            cbDesafio.setText("Ligar para alguma pessoa");


        tvMsg.setText(leitura.getMensagem());
        cbLeitura.setText(leitura.getReferencia());

        cbLeitura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(todosChecked()){
                    MDToast mdToast = MDToast.makeText(context, "Parabéns! Dia completado", LENGTH_SHORT, TYPE_SUCCESS);
                    mdToast.show();
                }
            }
        });

        cbOracao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(todosChecked()){
                    MDToast mdToast = MDToast.makeText(context, "Parabéns! Dia completado", LENGTH_SHORT, TYPE_SUCCESS);
                    mdToast.show();
                }
            }
        });

        cbDesafio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(todosChecked()){
                    MDToast mdToast = MDToast.makeText(context, "Parabéns! Dia completado", LENGTH_SHORT, TYPE_SUCCESS);
                    mdToast.show();
                }
            }
        });

        Button btSalvar = (Button) findViewById(R.id.bt_salvar_desafio);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int leituraUsr = 0;
                int oracao = 0;
                int desafio = 0;

                int desafioBanco = leitura.getDesafio();

                if(cbLeitura.isChecked())
                    leituraUsr = 1;
                if(cbOracao.isChecked())
                    oracao = 1;
                if(desafioBanco != 0){
                    if(!cbDesafio.isChecked()){

                        String x = cbDesafio.getText().toString();
                        String aux = x.substring(0,1);

                        if(aux == "L")
                            desafio = 1;
                        else if(aux == "O")
                            desafio = 2;
                        else
                            desafio = 3;
                    }else{
                        desafio = 7;
                    }

                }

                //settando no objeto de novo

                leitura.setLeitura(leituraUsr);
                leitura.setOracao(oracao);
                leitura.setDesafio(desafio);

                leituraService.setAtualizarLeituraDia(leitura);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean todosChecked(){

        boolean todosChecked = false;

        if(cbDesafio.isEnabled()){
            if(cbLeitura.isChecked() && cbOracao.isChecked() && cbDesafio.isChecked())
                todosChecked = true;
        }else{

            if(cbLeitura.isChecked() && cbOracao.isChecked())
                todosChecked = true;
        }

        return todosChecked;
    }
}
