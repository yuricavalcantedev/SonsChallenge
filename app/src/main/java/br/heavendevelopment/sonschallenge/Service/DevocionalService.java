package br.heavendevelopment.sonschallenge.Service;

import android.content.Context;

import java.util.List;

import br.heavendevelopment.sonschallenge.DataBaseAccess.DatabaseAccess;
import br.heavendevelopment.sonschallenge.Model.Devocional;

/**
 * Created by Yuri on 22/11/2016.
 */

public class DevocionalService {

    private Context context;

    public DevocionalService(Context context){
        super();
        this.context = context;
    }

    public boolean criarDevocional(Devocional devocional){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean cadastrado = databaseAccess.cadastrarDevocional(devocional);

        databaseAccess.close();

        return cadastrado;

    }

    public List<Devocional> getDevocionais(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        List<Devocional> listaDevocionais = databaseAccess.getDevocionais();

        databaseAccess.close();

        return listaDevocionais;
    }

    public Devocional getDevocionalById(int idDevocional){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        Devocional devocional = databaseAccess.getDevocionalById(idDevocional);

        databaseAccess.close();

        return devocional;
    }

    public boolean atualizarDevocional(Devocional devocional){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean devocionalAtualizado = databaseAccess.atualizarDevocional(devocional);

        databaseAccess.close();
        return devocionalAtualizado;
    }

    public boolean deletarDevocional(int idDevocional){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean deletado = databaseAccess.deletarDevocional(idDevocional);

        databaseAccess.close();

        return deletado;
    }

}
