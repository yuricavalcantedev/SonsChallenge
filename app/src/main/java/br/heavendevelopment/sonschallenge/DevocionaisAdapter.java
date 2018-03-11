package br.heavendevelopment.sonschallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.heavendevelopment.sonschallenge.Model.Devocional;

/**
 * Created by yuri on 23/02/18.
 */

public class DevocionaisAdapter extends BaseAdapter {

    private Context context;
    private List<Devocional> devocionalList;

    public DevocionaisAdapter(Context context, List<Devocional> devocionalList){

        this.context = context;
        this.devocionalList = devocionalList;
    }

    @Override
    public int getCount() {
        return devocionalList.size();
    }

    @Override
    public Object getItem(int position) {
        return devocionalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return devocionalList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder holder;

        if( convertView == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_lv_devocionais, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {

            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        Devocional devocional = devocionalList.get(position);

        holder.tvTituloDevocional.setText(devocional.getTitulo());
        holder.tvDiaDevocional.setText(String.valueOf(devocional.getDia()));
        holder.tvDataDevocional.setText(devocional.getData());
        holder.tvIdDevocional.setText(devocional.getId()+"");

        return view;
    }

    public class ViewHolder {

        final TextView tvTituloDevocional;
        final TextView tvDiaDevocional;
        final TextView tvDataDevocional;
        final TextView tvIdDevocional;

        public ViewHolder(View view) {

            tvTituloDevocional = (TextView) view.findViewById(R.id.tv_nome_item_devocional);
            tvDiaDevocional = (TextView) view.findViewById(R.id.tv_dia_item_devocional);
            tvDataDevocional = (TextView) view.findViewById(R.id.tv_data_item_devocional);
            tvIdDevocional = (TextView) view.findViewById(R.id.tv_id_item_devocional);
        }

    }
}
