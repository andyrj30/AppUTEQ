package com.example.andres_dell.uteqdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andres_dell.uteqdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarreraDescFragment extends Fragment {


    View view;
    String id;
    TextView txtCarrera;
    TextView txtDescripcion;
    ImageView imgLogo;

    public CarreraDescFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_carrera_desc, container, false);

        txtCarrera = (TextView) view.findViewById(R.id.txtCarrera);
        txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
        imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        txtCarrera.setText(getArguments().getString("nombre"));
        txtDescripcion.setText(Html.fromHtml(getArguments().getString("descripcion")));
        Glide.with(view.getContext())
                .load(getArguments().getString("URLImg"))
                .crossFade()
                .error(R.drawable.logouteqminres)
                .into(imgLogo);
        imgLogo.setScaleType(ImageView.ScaleType.FIT_XY);

        return view;
    }

}
