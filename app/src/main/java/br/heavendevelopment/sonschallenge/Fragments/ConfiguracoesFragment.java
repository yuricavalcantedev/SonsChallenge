package br.heavendevelopment.sonschallenge.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.heavendevelopment.sonschallenge.R;

public class ConfiguracoesFragment extends Fragment {

    public static ConfiguracoesFragment newInstance() {

        ConfiguracoesFragment fragment = new ConfiguracoesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configuracoes, container, false);
    }
}
