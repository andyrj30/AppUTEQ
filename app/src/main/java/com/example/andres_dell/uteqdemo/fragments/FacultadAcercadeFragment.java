package com.example.andres_dell.uteqdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.andres_dell.uteqdemo.utils.Constants;
import com.example.andres_dell.uteqdemo.utils.UIUtil;
import com.example.andres_dell.uteqdemo.utils.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.utils.WebServ.WebService;
import com.example.andres_dell.uteqdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacultadAcercadeFragment extends Fragment implements Asynchtask {

    View view;
    String id;
    TextView txtFacultad;
    TextView txtMision;
    TextView txtVision;
    TextView txtObjetivo;
    ImageView imgLogo;
    String ip;

    public FacultadAcercadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_facultad_acercade, container, false);
        Bundle b = getArguments();
        id = b.getString("id");
        ConectWSUnidad();
        txtFacultad = (TextView) view.findViewById(R.id.txtFacultad);
        txtMision = (TextView) view.findViewById(R.id.txtMision);
        txtVision = (TextView) view.findViewById(R.id.txtVision);
        txtObjetivo = (TextView) view.findViewById(R.id.txtObjetivo);
        imgLogo = (ImageView) view.findViewById(R.id.imgLogo);

        return view;
    }

    private void ConectWSUnidad() {
        if (!UIUtil.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(), "Comprueba tu conexión a Internet", Toast.LENGTH_LONG).show();
        } else {
            ip= "http://"+UIUtil.ipAConetarse(view.getContext());
            Map<String, String> params = new HashMap<String, String>();
            WebService ws;
            ws = new WebService(ip+Constants.WS_UNIDAD_ID + id, params, view.getContext(), FacultadAcercadeFragment.this);
            ws.execute("");
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray objdataarray = new JSONArray(result);
        JSONObject jsonObj = objdataarray.getJSONObject(0);
        if (jsonObj != null) {
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            String titulo = jsonObj.getString("titulo");
            if (id!="12" && id!="9")
                toolbar.setTitle(titulo != "null" ? titulo : "Facultad");
            txtFacultad.setText(Html.fromHtml(jsonObj.getString("titulo")));
            txtMision.setText(Html.fromHtml(jsonObj.getString("mision")));
            txtVision.setText(Html.fromHtml(jsonObj.getString("vision")));
            if (jsonObj.getString("objetivos") != "null") {
                txtObjetivo.setText(Html.fromHtml(jsonObj.getString("objetivos")));
            } else {
                TextView lblObjetivo = (TextView) view.findViewById(R.id.lblObjetivo);
                lblObjetivo.setVisibility(View.INVISIBLE);
                txtObjetivo.setVisibility(View.INVISIBLE);
            }
            Glide.with(view.getContext())
                    .load("http://www.uteq.edu.ec/" + jsonObj.getString("url"))
                    .crossFade()
                    .error(R.drawable.logouteqminres)
                    .into(imgLogo);
        }
    }
}