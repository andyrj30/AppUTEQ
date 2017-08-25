package com.example.andres_dell.uteqdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.R;
import com.example.andres_dell.uteqdemo.activity.MainActivity;
import com.example.andres_dell.uteqdemo.adapters.CarreraAdapter;
import com.example.andres_dell.uteqdemo.model.Carrera;
import com.example.andres_dell.uteqdemo.model.Oferta;
import com.example.andres_dell.uteqdemo.utils.Constants;
import com.example.andres_dell.uteqdemo.utils.UIUtil;
import com.example.andres_dell.uteqdemo.utils.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.utils.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacultadCarrerasFragment extends Fragment implements Asynchtask {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<Carrera> carreras;
    View view;
    private String id;
    String ip;


    public FacultadCarrerasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       id = getArguments().getString("id");
        view = inflater.inflate(R.layout.fragment_facultad_carreras, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvMenu);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ConectWSMenu();
        //"refrescar deslizando"
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayoutC);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary,R.color.colorPrimaryDark);//Aqui podemos cponer los colores que queremos
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ConectWSMenu();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
        return view;
    }

    private void ConectWSMenu() {
        if (!UIUtil.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(), "Comprueba tu conexión a Internet", Toast.LENGTH_LONG).show();
        } else {
            ip= "http://"+UIUtil.ipAConetarse(view.getContext());
            Map<String, String> params = new HashMap<String, String>();
            WebService ws = new WebService(ip+Constants.WS_CARRERA_UNIDAD +id, params, view.getContext(), FacultadCarrerasFragment.this);
            ws.execute("");
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray objdataarray = new JSONArray(result);
        carreras = Carrera.JsonObjectsBuild(objdataarray);
        CarreraAdapter adapter = new CarreraAdapter(view.getContext(), carreras, (MainActivity) getActivity());
        recyclerView.setAdapter(adapter);

    }
}