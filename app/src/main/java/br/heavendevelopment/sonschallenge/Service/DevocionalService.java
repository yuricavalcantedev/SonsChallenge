package br.heavendevelopment.sonschallenge.Service;

import com.activeandroid.query.Select;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.heavendevelopment.sonschallenge.Model.Devocional;

/**
 * Created by yuri on 09/03/18.
 */

public class DevocionalService {

    public boolean salvarDevocional(Devocional devocional){

        boolean salvou;

        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        int dia = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);
        int mes = gregorianCalendar.get(gregorianCalendar.MONTH) + 1;
        int ano = 2018;


        devocional.setData(dia + "/" + mes + "/" + ano);

        try{
            devocional.save();
            salvou = true;
        }catch (Exception ex){
            salvou = false;
        }

        return salvou;
    }

    public Devocional getDevocional(int id){

        return new Select()
                .from(Devocional.class)
                .where("id= ?", id)
                .executeSingle();
    }

    public List<Devocional> getAllDevocionais() {
        return new Select()
                .from(Devocional.class)
                .execute();
    }

    public boolean deleteDevocional(Devocional devocional) {

        boolean deletou;

        try{
            devocional.delete();
            deletou = true;
        }catch (Exception ex){
            deletou = false;
        }

        return deletou;

    }

}
