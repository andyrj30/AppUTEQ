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
public class CarreraAcercadeFragment extends Fragment {


    View view;
    String id;
    TextView txtCarrera;
    TextView txtMisionVision;
    TextView txtObjetivos;
    public CarreraAcercadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_carrera_acercade, container, false);

        txtCarrera = (TextView) view.findViewById(R.id.txtCarrera);
        txtMisionVision = (TextView) view.findViewById(R.id.txtMisionVision);
        txtObjetivos = (TextView) view.findViewById(R.id.txtObjetivos);
        txtCarrera.setText(Html.fromHtml(getArguments().getString("nombre")));
        txtMisionVision.setText(Html.fromHtml(getArguments().getString("mision")));
        txtObjetivos.setText(Html.fromHtml(getArguments().getString("objetivos")));
        return view;
    }

}
