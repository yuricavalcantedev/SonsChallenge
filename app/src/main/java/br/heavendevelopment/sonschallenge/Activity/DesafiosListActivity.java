package br.heavendevelopment.sonschallenge.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.List;

import br.heavendevelopment.sonschallenge.Adapters.DesafiosLeituraAdapter;
import br.heavendevelopment.sonschallenge.Model.Leitura;
import br.heavendevelopment.sonschallenge.R;
import br.heavendevelopment.sonschallenge.Service.LeituraService;

public class DesafiosListActivity extends AppCompatActivity {

    private ListView listView;
    private LeituraService leituraService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafios_list);

        listView = (ListView) findViewById(R.id.lv_desafios_leitura);
        leituraService = new LeituraService(this);

        int mes = getIntent().getIntExtra("mes",0);

        List<Leitura> leituras = leituraService.getLeiturasPorMes(mes);
        DesafiosLeituraAdapter desafiosLeituraAdapter = new DesafiosLeituraAdapter(this, leituras);

        listView.setAdapter(desafiosLeituraAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvDia = (TextView) view.findViewById(R.id.img_item_desafios_list);
                String x [] = tvDia.getText().toString().split(" ");
                int dia = Integer.parseInt(x[1]);

                Intent intent = new Intent(DesafiosListActivity.this,VerDiaLeituraActivity.class);
                intent.putExtra("dia", dia);
                startActivity(intent);
            }
        });

        MDToast.makeText(getBaseContext(),"Você é muito mas muito melhor do que eu havia orado e imaginado", Toast.LENGTH_LONG,MDToast.TYPE_INFO).show();
    }

    @Override
    public void onResume(){
        super.onResume();


        int mes = getIntent().getIntExtra("mes",0);

        List<Leitura> leituras = leituraService.getLeiturasPorMes(mes);
        DesafiosLeituraAdapter desafiosLeituraAdapter = new DesafiosLeituraAdapter(this, leituras);

        listView.setAdapter(desafiosLeituraAdapter);
    }
}
