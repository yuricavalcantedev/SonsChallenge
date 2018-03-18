package br.heavendevelopment.sonschallenge.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.heavendevelopment.sonschallenge.Activity.CriarDevocional;
import br.heavendevelopment.sonschallenge.Adapters.DevocionaisAdapter;
import br.heavendevelopment.sonschallenge.Model.Devocional;
import br.heavendevelopment.sonschallenge.R;
import br.heavendevelopment.sonschallenge.Service.DevocionalService;

public class DevocionaisFragment extends Fragment {

    private ListView lvDevocionaisMain;
    private List<Devocional> devocionalList;
    private DevocionaisAdapter devocionaisAdapter;
    private View view;
    public static DevocionaisFragment newInstance() {

        DevocionaisFragment fragment = new DevocionaisFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_devocionais, container, false);

        lvDevocionaisMain = (ListView) view.findViewById(R.id.lv_devocionais_main);

        fillListViewDevocionais(view);

        TextView tvMsgDevocional = (TextView) view.findViewById(R.id.tv_msgDevocional_naoCriado);
        TextView tvMsgEspirito = (TextView) view.findViewById(R.id.tv_msgDevocional_guiadoPeloEspirito);

        if(devocionalList.size() == 0){
            tvMsgDevocional.setVisibility(View.VISIBLE);
            tvMsgEspirito.setVisibility(View.VISIBLE);
            lvDevocionaisMain.setVisibility(View.INVISIBLE);
        }else{

            tvMsgDevocional.setVisibility(View.INVISIBLE);
            tvMsgEspirito.setVisibility(View.INVISIBLE);
            lvDevocionaisMain.setVisibility(View.VISIBLE);

        }

        lvDevocionaisMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) view.findViewById(R.id.tv_id_item_devocional);

                String aux = tvId.getText().toString();
                String x [] = aux.split("a");
                int idDevocional = Integer.parseInt(x[1]);

                Bundle bundle = new Bundle();
                bundle.putInt("idDevocional",idDevocional);

                Intent intent = new Intent(getContext(),CriarDevocional.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

        Button btCriarDevocional = (Button) view.findViewById(R.id.bt_criar_devocional);
        btCriarDevocional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CriarDevocional.class));
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        fillListViewDevocionais(view);
    }


    private void fillListViewDevocionais(View view){

        DevocionalService devocionalService = new DevocionalService(getContext());

        devocionalList = devocionalService.getDevocionais();
        TextView tvmsgGuiadoEspirito= (TextView) view.findViewById(R.id.tv_msgDevocional_guiadoPeloEspirito);
        TextView tvMsgNaoCriado = (TextView) view.findViewById(R.id.tv_msgDevocional_naoCriado);

        if(devocionalList.size() == 0) {
            tvmsgGuiadoEspirito.setVisibility(View.VISIBLE);
            tvMsgNaoCriado.setVisibility(View.VISIBLE);
        }else {
            tvmsgGuiadoEspirito.setVisibility(View.INVISIBLE);
            tvMsgNaoCriado.setVisibility(View.INVISIBLE);
        }

        devocionaisAdapter = new DevocionaisAdapter(getContext(),devocionalList);
        lvDevocionaisMain.setAdapter(devocionaisAdapter);

    }

}
