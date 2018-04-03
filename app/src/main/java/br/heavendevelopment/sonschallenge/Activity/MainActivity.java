package br.heavendevelopment.sonschallenge.Activity;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.valdesekamdem.library.mdtoast.MDToast;

import br.heavendevelopment.sonschallenge.Fragments.ConfiguracoesFragment;
import br.heavendevelopment.sonschallenge.Fragments.DesafiosFragment;
import br.heavendevelopment.sonschallenge.Fragments.DevocionaisFragment;
import br.heavendevelopment.sonschallenge.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
//
//        MDToast.makeText(getBaseContext(),"Você é tão especial para mim!", Toast.LENGTH_LONG,MDToast.TYPE_INFO).show();

        //Used to select an item programmatically
        bottomNavigationView.getMenu().getItem(1).setChecked(true);



    }

}