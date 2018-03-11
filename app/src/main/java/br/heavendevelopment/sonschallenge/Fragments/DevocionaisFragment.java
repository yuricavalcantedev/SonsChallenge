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

import br.heavendevelopment.sonschallenge.CriarDevocional;
import br.heavendevelopment.sonschallenge.DevocionaisAdapter;
import br.heavendevelopment.sonschallenge.MainActivity;
import br.heavendevelopment.sonschallenge.Model.Devocional;
import br.heavendevelopment.sonschallenge.R;
import br.heavendevelopment.sonschallenge.Service.DevocionalService;

public class DevocionaisFragment extends Fragment {

    private ListView lvDevocionaisMain;

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
        View view = inflater.inflate(R.layout.fragment_devocionais, container, false);

        lvDevocionaisMain = (ListView) view.findViewById(R.id.lv_devocionais_main);

        DevocionalService devocionalService = new DevocionalService();
        List<Devocional> devocionalList = devocionalService.getAllDevocionais();

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

        DevocionaisAdapter devocionaisAdapter = new DevocionaisAdapter(getContext(),devocionalList);

        lvDevocionaisMain.setAdapter(devocionaisAdapter);

        lvDevocionaisMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) view.findViewById(R.id.tv_id_item_devocional);

                int idDevocional = Integer.parseInt(tvId.getText().toString());

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
}
