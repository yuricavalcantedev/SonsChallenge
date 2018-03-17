package br.heavendevelopment.sonschallenge.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.heavendevelopment.sonschallenge.DesafioActivity;
import br.heavendevelopment.sonschallenge.R;

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

        return view;
    }

}

