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
import com.example.andres_dell.uteqdemo.adapters.NoticiaAdapter;
import com.example.andres_dell.uteqdemo.model.Noticia;
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
public class FacultadNoticiasFragment extends Fragment implements Asynchtask {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    View view;
    private String id;
    String ip;

    public FacultadNoticiasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_facultad_noticias, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvListNoticias);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle b = getArguments();
        id = b.getString("id");
        ConectWSNoticias();
        //"refrescar deslizando"
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayoutN1);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorAccent);//Aqui podemos cponer los colores que queremos
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ConectWSNoticias();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
        return view;
    }

    private void ConectWSNoticias() {
        if (!UIUtil.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(), "Comprueba tu conexión a Internet", Toast.LENGTH_LONG).show();
        } else {
            ip= "http://"+UIUtil.ipAConetarse(view.getContext());
            Map<String, String> params = new HashMap<String, String>();
            WebService ws;
            ws = new WebService(ip+Constants.WS_NOTICIA_UNIDAD.concat(id), params, view.getContext(), FacultadNoticiasFragment.this);
            ws.execute("");

        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray objdataarray = new JSONArray(result);
        ArrayList<Noticia> noticias = Noticia.JsonObjectsBuild(objdataarray);
        NoticiaAdapter noticiaAdapter = new NoticiaAdapter(view.getContext(), noticias, getFragmentManager());
        recyclerView.setAdapter(noticiaAdapter);
    }
}
