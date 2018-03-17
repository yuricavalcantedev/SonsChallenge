package br.heavendevelopment.sonschallenge.Fragments;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.heavendevelopment.sonschallenge.MainActivity;
import br.heavendevelopment.sonschallenge.R;

import static android.content.Context.MODE_PRIVATE;

public class ConfiguracoesFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static ConfiguracoesFragment newInstance() {

        ConfiguracoesFragment fragment = new ConfiguracoesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracoes, container, false);


        sharedPreferences = getContext().getSharedPreferences("preferencesMain",MODE_PRIVATE);


        Switch switchAlarmeLeitura = (Switch) view.findViewById(R.id.switch_alarme_leitura);
        Switch switchNotificacoes = (Switch) view.findViewById(R.id.switch_notificacoes);
        Button btSalvarConfig = (Button) view.findViewById(R.id.bt_salvar_configuracoes);
        final EditText etAlarme = (EditText) view.findViewById(R.id.et_alarme);
        final TextView tvHelpAlarme = (TextView) view.findViewById(R.id.tv_help_alarme);
        final TextView tvHelpNotificacoes = (TextView) view.findViewById(R.id.tv_help_notificacoes_diarias);

        boolean alarmeLigado = sharedPreferences.getBoolean("alarmeLigado",true);
        boolean notificacoesLigadas = sharedPreferences.getBoolean("notificacoesLigadas",true);
        String alarme = sharedPreferences.getString("alarme","07:00");

        switchAlarmeLeitura.setChecked(alarmeLigado);
        switchNotificacoes.setChecked(notificacoesLigadas);
        etAlarme.setText(alarme);

        if(alarmeLigado){
            tvHelpAlarme.setText("Alarme ligado :)");
            etAlarme.setEnabled(true);
        }
        else {
            tvHelpAlarme.setText("Configure um alarme para sua leitura");
            etAlarme.setEnabled(false);
        }

        if(notificacoesLigadas)
            tvHelpNotificacoes.setText("Habilite nossas mensagens diárias aparecerem para você :D ");
        else
            tvHelpNotificacoes.setText("Não quer ver nossas mensagens? :/");


        //listeners

        switchAlarmeLeitura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tvHelpAlarme.setText("Alarme ligado :)");
                    etAlarme.setEnabled(true);

                }else{
                    tvHelpAlarme.setText("Configure um alarme para sua leitura");
                    etAlarme.setEnabled(false);
                }
            }
        });

        switchNotificacoes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    tvHelpNotificacoes.setText("Habilitado! Nossas mensagens diárias apareceram para você :D ");

                }else{
                    tvHelpNotificacoes.setText("Não quer ver nossas mensagens? :/");
                }
            }
        });

        etAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etAlarme.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });

        btSalvarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Configurações foram salvas.", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
