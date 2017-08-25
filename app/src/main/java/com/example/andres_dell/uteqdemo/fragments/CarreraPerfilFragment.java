package com.example.andres_dell.uteqdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andres_dell.uteqdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarreraPerfilFragment extends Fragment {


    View view;
    TextView txtCarrera;
    TextView txtPerfil;

    public CarreraPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_carrera_perfil, container, false);
        txtCarrera = (TextView) view.findViewById(R.id.txtCarrera);
        txtPerfil = (TextView) view.findViewById(R.id.txtPerfil);
        txtCarrera.setText(getArguments().getString("nombre"));
        txtPerfil.setText(Html.fromHtml(getArguments().getString("perfilprofesional")));


        return view;
    }

}
