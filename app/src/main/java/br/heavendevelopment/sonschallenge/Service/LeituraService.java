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

    public double getPorCentagemDesafio(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        double porcentagem = databaseAccess.getPorCentagemDesafio();

        databaseAccess.close();

        return porcentagem ;

    }

    public  boolean checkUpdateDesafios(){


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean updated = databaseAccess.checkUpdateDesafios();

        databaseAccess.close();

        return updated;

    }

    public boolean updateDesafios(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean updated = databaseAccess.updateDesafios();

        databaseAccess.close();

        return updated ;

    }

    public int getPosicaoLeitura(int mes){

        int posicaoDiaLeitura = 0;

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        posicaoDiaLeitura = databaseAccess.getPosicaoLeitura(mes);

        databaseAccess.close();

        return posicaoDiaLeitura;
    }

}
