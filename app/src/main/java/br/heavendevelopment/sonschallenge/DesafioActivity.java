package br.heavendevelopment.sonschallenge;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.imangazaliev.circlemenu.CircleMenu;
import com.imangazaliev.circlemenu.CircleMenuButton;
import com.imangazaliev.circlemenu.CircleMenuText;

import java.util.List;

public class DesafioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio);

        CircleMenu circleMenuMultiple = (CircleMenu) findViewById(R.id.circle_menu_multiple_border);
        circleMenuMultiple.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        //quando apertar no mes, ele terá que ir para o dia atual da leitura, ou se ele já tiver acabado
        //esse mês e estiver querendo apenas ver as leituras que se passaram, ele vai pro primeiro dia do mês

        //a ordem que a view mostra os itens é diferente.
        CircleMenuText circleMenuTextFogo = new CircleMenuText(this);


        circleMenuTextFogo.setIconResId(R.drawable.ic_fogo_main);
        circleMenuTextFogo.setEnableBorder(true);
        circleMenuTextFogo.setClickable(true);
        circleMenuTextFogo.setMetaData(1);

        circleMenuTextFogo.setTitle("Mês 1" );
        circleMenuTextFogo.setTitleColor(Color.BLACK);

        CircleMenuText circleMenuTextPaixao = new CircleMenuText(this);

        circleMenuTextPaixao.setIconResId(R.drawable.ic_favorite);
        circleMenuTextPaixao.setEnableBorder(true);
        circleMenuTextPaixao.setClickable(true);
        circleMenuTextPaixao.setMetaData(2);

        circleMenuTextPaixao.setTitle("Mês 2" );
        circleMenuTextPaixao.setTitleColor(Color.BLACK);

        CircleMenuText circleMenuTextLagrimas = new CircleMenuText(this);

        circleMenuTextLagrimas.setIconResId(R.drawable.ic_lagrimas_main);
        circleMenuTextLagrimas.setEnableBorder(true);
        circleMenuTextLagrimas.setClickable(true);
        circleMenuTextLagrimas.setMetaData(3);

        circleMenuTextLagrimas.setTitle("Mês 3" );
        circleMenuTextLagrimas.setTitleColor(Color.BLACK);


        circleMenuMultiple.addButton(circleMenuTextPaixao);
        circleMenuMultiple.addButton(circleMenuTextLagrimas);
        circleMenuMultiple.addButton(circleMenuTextFogo);


        circleMenuMultiple.setOnItemClickListener(new CircleMenu.OnItemClickListener() {
            @Override
            public void onItemClick(CircleMenuButton menuButton) {

                int mes = Integer.parseInt(menuButton.getMetaData().toString());
                menuButton.getMetaData();

                Intent intent = new Intent(DesafioActivity.this, DesafiosListActivity.class);
                intent.putExtra("mes",mes);
                startActivity(intent);
            }
        });


    }
}
