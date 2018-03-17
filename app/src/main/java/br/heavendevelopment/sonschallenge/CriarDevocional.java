package br.heavendevelopment.sonschallenge;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import br.heavendevelopment.sonschallenge.Model.Devocional;
import br.heavendevelopment.sonschallenge.Model.Leitura;
import br.heavendevelopment.sonschallenge.Service.DevocionalService;
import br.heavendevelopment.sonschallenge.Service.LeituraService;
import de.mrapp.android.bottomsheet.BottomSheet;

public class CriarDevocional extends AppCompatActivity {

    private int diaAtualEscolhido = 0;
    private EditText etTitulo;
    private Spinner spinnerDia;
    private EditText etDevocional;
    private Bundle bundleDevocional;
    private Context context;
    private int idDevocional;
    private DevocionalService devocionalService;
    private int flag = 0; //0 = criar devocional, 1 = atualizar devocional

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_devocional);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_criar_devocional);
        toolbar.setTitle("Criar Devocional"); // ou o nome do devocional
        toolbar.setTitleTextColor(Color.WHITE);

        spinnerDia = (Spinner) findViewById(R.id.spinner_dia_devocional);
        TextView tvDataDevocional = (TextView) findViewById(R.id.tvDataDevocional);
        etTitulo = (EditText) findViewById(R.id.et_titulo_devocional);
        etDevocional = (EditText) findViewById(R.id.et_devocional);

        devocionalService = new DevocionalService(context);

        List<String> spinnerArray = new ArrayList<>();
        for(int i = 1; i <= 80; i++)
            spinnerArray.add("Dia " +i );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                CriarDevocional.this,
                android.R.layout.simple_spinner_item,
                spinnerArray
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // (4) set the adapter on the spinner
        spinnerDia.setAdapter(adapter);

        bundleDevocional = getIntent().getExtras();
        if(bundleDevocional != null){

            idDevocional = bundleDevocional.getInt("idDevocional");

            Devocional devocional = devocionalService.getDevocionalById(idDevocional);

            tvDataDevocional.setText(devocional.getData());
            etTitulo.setText(devocional.getTitulo());
            etDevocional.setText(devocional.getTextoDevocional());
            tvDataDevocional.setText(devocional.getData());

            flag = 1;


            //dou o "-1" para ele colocar o item correto.
            spinnerDia.setSelection(devocional.getDiaDesafio() - 1);
        }else{

            GregorianCalendar gregorianCalendar = new GregorianCalendar();

            int dia = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);
            int mes = gregorianCalendar.get(gregorianCalendar.MONTH) + 1;
            int ano = 2018;

            spinnerDia.setSelection(diaAtualEscolhido);

            String dataHoje = dia + "/" + mes + "/" + ano;
            tvDataDevocional.setText(dataHoje);

        }

        LeituraService leituraService = new LeituraService(context);

        spinnerDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                diaAtualEscolhido = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btSalvar = (Button) findViewById(R.id.bt_salvar_devocional);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etTitulo = (EditText) findViewById(R.id.et_titulo_devocional);
                spinnerDia = (Spinner) findViewById(R.id.spinner_dia_devocional);
                etDevocional = (EditText) findViewById(R.id.et_devocional);
                TextView tvDataDevocional = (TextView) findViewById(R.id.tvDataDevocional);

                String titulo = etTitulo.getText().toString();
                String textoDevocional = etDevocional.getText().toString();
                String data = tvDataDevocional.getText().toString();

                Devocional devocional = new Devocional(diaAtualEscolhido+1,data,titulo,textoDevocional);

                if(flag == 0) {
                    devocionalService.criarDevocional(devocional);
                    Toast.makeText(CriarDevocional.this, "Devocional Salvo!", Toast.LENGTH_SHORT).show();
                }else {
                    devocional.setId(idDevocional);
                    devocionalService.atualizarDevocional(devocional);
                    Toast.makeText(CriarDevocional.this, "Devocional Atualizado!", Toast.LENGTH_SHORT).show();
                }

                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(bundleDevocional != null)
            getMenuInflater().inflate(R.menu.menu_criar_devocionais, menu);
        return true;
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

        }else if (id == R.id.ic_menu_delete){

            deletarDevocional();

        }else if(id == R.id.ic_menu_share){

            compartilharDevocional();
        }

        return super.onOptionsItemSelected(item);
    }

    private void compartilharDevocional(){

        etTitulo = (EditText) findViewById(R.id.et_titulo_devocional);
        //Spinner spinnerDia = (Spinner) findViewById(R.id.spinner_dia_devocional);
        //deixar de stand by aqui, caso a Jhey queira colocar o dia.

        etDevocional = (EditText) findViewById(R.id.et_devocional);

        String titulo = etTitulo.getText().toString();
        String devocional = etDevocional.getText().toString();

        String textoCompartilhar = "Son's Challenge - Closer \n"; // mudar o desafio depois.
        textoCompartilhar += "Devocional - " + titulo+ " \n\n";
        textoCompartilhar += devocional;

        BottomSheet.Builder builder = new BottomSheet.Builder(this);
        builder.setTitle("Compartilhar via...");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, textoCompartilhar );
        intent.setType("text/plain");

        builder.setIntent(this, intent);
        builder.setStyle(BottomSheet.Style.LIST);

        BottomSheet bottomSheet = builder.create();
        bottomSheet.show();

    }

    private void deletarDevocional(){


        //pego o devocional completo por causa do titulo
        final Devocional devocionalExcluir = devocionalService.getDevocionalById(idDevocional);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Excluir Devocional")
                .setMessage("Você realmente deseja excluir o devocional : " + devocionalExcluir.getTitulo())
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        devocionalService.deletarDevocional(idDevocional);

                        Toast.makeText(context, "Devocional excluído com sucesso", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
