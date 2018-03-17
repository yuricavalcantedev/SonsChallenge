package br.heavendevelopment.sonschallenge.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.heavendevelopment.sonschallenge.Model.Leitura;
import br.heavendevelopment.sonschallenge.R;

/**
 * Created by yuri on 16/03/18.
 */

public class DesafiosLeituraAdapter extends BaseAdapter{

    private Context context;
    private List<Leitura> leituraList;

    public DesafiosLeituraAdapter(Context context,List<Leitura> leituraList) {

        this.context = context;
        this.leituraList = leituraList;
    }

    @Override
    public int getCount() {
        return leituraList.size();
    }

    @Override
    public Leitura getItem(int position) {
        return leituraList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_lv_desafios, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        Leitura leitura = leituraList.get(position);
        holder.img_item_desafios_list.setText("Dia " + leitura.getDia());
        boolean statusLeitura = statusLeitura(leitura);
        //se true ele já realizou a leitura
        //se false, ele ainda precisa terminar a leitura
        if(statusLeitura)
            holder.img_item_desafios_list.setBackground(context.getResources().getDrawable(R.drawable.rounded_textview_done));
        else
            holder.img_item_desafios_list.setBackground(context.getResources().getDrawable(R.drawable.rounded_textview));

        return convertView;
    }

    private boolean statusLeitura(Leitura leitura){

        boolean status = false;

        if(leitura.getDesafio() == 0){

            if(leitura.getOracao() == 1 && leitura.getDesafio() == 1){

                status = true;
            }else{
                //fazer a verificação se a leitura já passou também
                status = false;
            }
        }else{ //se não é 0, é porque esse dia tem desafio

            if(leitura.getOracao() == 1 && leitura.getDesafio() == 1 && leitura.getDesafio() == 7){

                status = true;
            }else{
                status = false;
            }
        }


        return status;
    }

    private class ViewHolder {

        private TextView img_item_desafios_list;


        public ViewHolder(View v) {

            img_item_desafios_list = (TextView) v.findViewById(R.id.img_item_desafios_list);

        }
    }
}

