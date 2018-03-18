package br.heavendevelopment.sonschallenge.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valdesekamdem.library.mdtoast.MDToast;

import br.heavendevelopment.sonschallenge.Activity.DesafioActivity;
import br.heavendevelopment.sonschallenge.R;

import static com.valdesekamdem.library.mdtoast.MDToast.LENGTH_LONG;
import static com.valdesekamdem.library.mdtoast.MDToast.TYPE_INFO;
import static com.valdesekamdem.library.mdtoast.MDToast.TYPE_SUCCESS;

public class DesafiosFragment extends Fragment {

    public static DesafiosFragment newInstance() {

        DesafiosFragment fragment = new DesafiosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desafios, container, false);

        TextView tvCloser = (TextView) view.findViewById(R.id.tv_desafio_closer);
        tvCloser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DesafioActivity.class));
            }
        });

        TextView tv40dias= (TextView) view.findViewById(R.id.tv_desafio_40dias);
        tv40dias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MDToast mdToast = MDToast.makeText(getContext(), "Desafio indisponível no momento", LENGTH_LONG, TYPE_INFO);
                mdToast.show();
            }
        });


        TextView tvDeeper = (TextView) view.findViewById(R.id.tv_desafio_deeper);
        tvDeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MDToast mdToast = MDToast.makeText(getContext(), "Desafio indisponível no momento", LENGTH_LONG, TYPE_INFO);
                mdToast.show();
            }
        });


        return view;
    }

}

