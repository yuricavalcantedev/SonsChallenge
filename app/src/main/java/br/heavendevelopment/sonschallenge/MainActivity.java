package br.heavendevelopment.sonschallenge;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.heavendevelopment.sonschallenge.Fragments.ConfiguracoesFragment;
import br.heavendevelopment.sonschallenge.Fragments.DesafiosFragment;
import br.heavendevelopment.sonschallenge.Fragments.DevocionaisFragment;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("preferencesMain",MODE_PRIVATE);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.bottom_item_devocionais:
                                selectedFragment = DevocionaisFragment.newInstance();
                                break;
                            case R.id.bottom_item_desafios:
                                selectedFragment = DesafiosFragment.newInstance();
                                break;
                            case R.id.bottom_item_configuracoes:
                                selectedFragment = ConfiguracoesFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });


        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, DesafiosFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        bottomNavigationView.getMenu().getItem(1).setChecked(true);

    }


    private void setUpConfiguracoesView(){

            Switch switchAlarmeLeitura = (Switch) findViewById(R.id.switch_alarme_leitura);
            Switch switchNotificacoes = (Switch) findViewById(R.id.switch_notificacoes);
            Button btSalvarConfig = (Button) findViewById(R.id.bt_salvar_configuracoes);
            final EditText etAlarme = (EditText) findViewById(R.id.et_alarme);
            final TextView tvHelpAlarme = (TextView) findViewById(R.id.tv_help_alarme);
            final TextView tvHelpNotificacoes = (TextView) findViewById(R.id.tv_help_notificacoes_diarias);

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
                    mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                    Toast.makeText(MainActivity.this, "Configurações foram salvas.", Toast.LENGTH_SHORT).show();
                }
            });


        }


}