package br.heavendevelopment.sonschallenge.Service;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import br.heavendevelopment.sonschallenge.Model.Leitura;

/**
 * Created by yuri on 09/03/18.
 */

public class LeituraService {

    public boolean salvarLeitura(Leitura leitura){

        boolean salvou;

        try{
            leitura.save();
            salvou = true;
        }catch (Exception ex){
            salvou = false;
        }

        return salvou;
    }

    public Leitura getLeitura(int dia){

        return new Select()
                .from(Leitura.class)
                .where("dia = ?", dia)
                .executeSingle();
    }

    public List<Leitura> getAllLeituras() {
        List<Leitura> x = new Select()
                .from(Leitura.class)
                .execute();

        return x;
    }

    public boolean deleteLeitura(Leitura leitura) {

        boolean deletou;

        try{
            leitura.delete();
            deletou = true;
        }catch (Exception ex){
            deletou = false;
        }

        return deletou;

    }

    public int getDiaAtual(){

        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        int dia = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);
        int mes = gregorianCalendar.get(gregorianCalendar.MONTH) + 1;
        int ano = 2018;

        String dataAtual = dia + "/" + mes + "/" + ano;

        Leitura leitura = new Select()
                .from(Leitura.class)
                .where("data = ?", dataAtual)
                .executeSingle();

        return leitura.getDia();
    }

    public List<Integer> getDias(Leitura leitura) {

        List<Integer> listaDias = new ArrayList<>();

        List<Leitura> lista=
                new Select()
                .from(Leitura.class)
                .execute();

        for(int i = 0; i < 80; i++)
            listaDias.add(i);

        return listaDias;
    }

}
