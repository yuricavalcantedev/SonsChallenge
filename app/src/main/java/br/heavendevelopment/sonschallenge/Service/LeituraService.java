package br.heavendevelopment.sonschallenge.Service;

import android.content.Context;

import java.util.List;

import br.heavendevelopment.sonschallenge.DataBaseAccess.DatabaseAccess;
import br.heavendevelopment.sonschallenge.Model.Leitura;

/**
 * Created by yuri on 09/03/18.
 */

public class LeituraService {

    private Context context;

    public LeituraService(Context context){
        this.context = context;
    }

    public List<Leitura> getLeiturasPorMes(int mes){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        List<Leitura> leituraList = databaseAccess.getLeiturasPorMes(mes);

        databaseAccess.close();

        return leituraList;
    }

    public Leitura getLeituraDia(int dia){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        Leitura leitura = databaseAccess.getLeituraDia(dia);

        databaseAccess.close();

        return leitura;
    }

    public boolean setAtualizarLeituraDia(Leitura leitura){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean atualizado = databaseAccess.setAtualizarLeituraDia(leitura);

        databaseAccess.close();

        return atualizado;
    }

}
