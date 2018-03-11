package br.heavendevelopment.sonschallenge.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.fragment_desafios, container, false);
    }
}
