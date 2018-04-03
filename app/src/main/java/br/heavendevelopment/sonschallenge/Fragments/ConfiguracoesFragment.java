package br.heavendevelopment.sonschallenge.Fragments;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
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

import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.Calendar;
import java.util.Locale;

import br.heavendevelopment.sonschallenge.Alarm.AlarmReceiver;
import br.heavendevelopment.sonschallenge.Alarm.AlarmReceiverTuesdayChallenge;
import br.heavendevelopment.sonschallenge.Alarm.NotificationScheduler;
import br.heavendevelopment.sonschallenge.R;

import static android.content.Context.MODE_PRIVATE;



public class ConfiguracoesFragment extends Fragment {

    private EditText etAlarme;
    private TextView tvHelpAlarme;
    private TextView tvHelpNotificacoes;
    private Context context;

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

        context = getContext();
        sharedPreferences = getContext().getSharedPreferences("preferencesMain",MODE_PRIVATE);


        final Switch switchAlarmeLeitura = (Switch) view.findViewById(R.id.switch_alarme_leitura);
        final Switch switchDesafioTerca = (Switch) view.findViewById(R.id.switch_desafio_terca);
        Button btSalvarConfig = (Button) view.findViewById(R.id.bt_salvar_configuracoes);
        etAlarme = (EditText) view.findViewById(R.id.et_alarme);
        tvHelpAlarme = (TextView) view.findViewById(R.id.tv_help_alarme);
        tvHelpNotificacoes = (TextView) view.findViewById(R.id.tv_help_notificacoes_diarias);

        boolean alarmeLigado = sharedPreferences.getBoolean("alarmeLeitura",true);
        boolean notificacoesLigadas = sharedPreferences.getBoolean("alarmeDesafioTerca",true);
        String alarme = sharedPreferences.getString("alarme","07:00");

        switchAlarmeLeitura.setChecked(alarmeLigado);
        switchDesafioTerca.setChecked(notificacoesLigadas);
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
            tvHelpNotificacoes.setText("Vamos lembrar você de dizer quanto o Espírito Santo é lindão! :D ");
        else
            tvHelpNotificacoes.setText("Tem certeza que não quer se esforçar mais? :/");


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

        switchDesafioTerca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    tvHelpNotificacoes.setText("Vamos lembrar você de dizer quanto o Espírito Santo é lindão! :D ");


                }else{
                    tvHelpNotificacoes.setText("Tem certeza que não quer se esforçar mais? :/");
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

                MDToast.makeText(getContext(),"Configurações foram salvas",Toast.LENGTH_SHORT,MDToast.TYPE_SUCCESS).show();

                editor = sharedPreferences.edit();

                if(switchAlarmeLeitura.isChecked()){

                    String texto = etAlarme.getText().toString();
                    String x [] = texto.split(":");
                    int hora = Integer.parseInt(x[0]);
                    int minutos = Integer.parseInt(x[1]);

                    NotificationScheduler.setReminder(getContext(), AlarmReceiver.class, hora, minutos);
                }else{
                    NotificationScheduler.cancelReminder(getContext(), AlarmReceiver.class);
                }

                if(switchDesafioTerca.isChecked()){
                    NotificationScheduler.setReminderTuesdayChallenge(context,AlarmReceiverTuesdayChallenge.class);
                }else{
                    NotificationScheduler.cancelReminderTuesdayChallenge(context,AlarmReceiverTuesdayChallenge.class);
                }

                String textoAlarme = etAlarme.getText().toString();
                boolean alarmeLeitura = switchAlarmeLeitura.isChecked();
                boolean alarmeDesafioTerca = switchDesafioTerca.isChecked();

                editor.putString("alarme",textoAlarme);
                editor.putBoolean("alarmeLeitura",alarmeLeitura);
                editor.putBoolean("alarmeDesafioTerca",alarmeDesafioTerca);

                editor.apply();

            }
        });


        return view;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }
}
