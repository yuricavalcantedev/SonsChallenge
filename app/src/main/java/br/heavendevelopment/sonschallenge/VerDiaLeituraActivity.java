package br.heavendevelopment.sonschallenge;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import br.heavendevelopment.sonschallenge.Model.Leitura;
import br.heavendevelopment.sonschallenge.Service.LeituraService;

public class VerDiaLeituraActivity extends AppCompatActivity {

    private CheckBox cbLeitura;
    private CheckBox cbOracao;
    private CheckBox cbDesafio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_dia_leitura);

        int dia = getIntent().getIntExtra("dia",1);

        LeituraService leituraService = new LeituraService(this);
        Leitura leitura = leituraService.getLeituraDia(dia);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_verDiaLeitura);
        toolbar.setTitle("Visualizar Leitura"); // ou o nome do devocional
        toolbar.setSubtitle("Leitura dia - " + leitura.getDia());
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView tvMsg = (TextView) findViewById(R.id.tv_msg_ver_leitura);
        cbLeitura = (CheckBox) findViewById(R.id.cb_leitura);
        cbOracao = (CheckBox) findViewById(R.id.cb_oracao);
        cbDesafio = (CheckBox) findViewById(R.id.cb_desafio);

        boolean leituraRealizada;
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
            cbDesafio.setText("Demonstrar seu amor ao Espírito Santo de hora em hora.");
        else if(desafioLeitura == 2)
            cbDesafio.setText("Compartilhar texto e/ou frase.");
        else if(desafioLeitura == 3)
            cbDesafio.setText("Ligar para alguma pessoa.");

        tvMsg.setText(leitura.getMensagem());

        cbLeitura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(todosChecked()){
                    Toast.makeText(VerDiaLeituraActivity.this, "Dia completado", Toast.LENGTH_SHORT).show();
                    //finish
                }

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

        if(cbLeitura.isChecked() && cbOracao.isChecked() && cbDesafio.isChecked())
            todosChecked = true;

        return todosChecked;
    }
}
