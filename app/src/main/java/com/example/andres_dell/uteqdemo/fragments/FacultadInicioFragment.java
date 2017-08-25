package com.example.andres_dell.uteqdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.andres_dell.uteqdemo.adapters.NoticiaAdapter;
import com.example.andres_dell.uteqdemo.model.Noticia;
import com.example.andres_dell.uteqdemo.utils.Constants;
import com.example.andres_dell.uteqdemo.utils.UIUtil;
import com.example.andres_dell.uteqdemo.utils.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.utils.WebServ.WebService;
import com.example.andres_dell.uteqdemo.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacultadInicioFragment extends Fragment implements Asynchtask {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    String IdFacultad;
    View view;
    ImageView imageView;
    String ip;

    public FacultadInicioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        IdFacultad = getArguments().getString("id");
        view = inflater.inflate(R.layout.fragment_facultad_inicio, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvNoticia);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        imageView = (ImageView) view.findViewById(R.id.imgLogo);
        ConectWSNoticia();
        setBanner();
        //"refrescar deslizando"
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayoutN);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);//Aqui podemos cponer los colores que queremos
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ConectWSNoticia();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
        return view;
    }

    private void ConectWSNoticia() {
        if (!UIUtil.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(), "Comprueba tu conexión a Internet", Toast.LENGTH_LONG).show();
        } else {
            ip = "http://" + UIUtil.ipAConetarse(view.getContext());
            Map<String, String> params = new HashMap<String, String>();
            WebService ws;
            ws = new WebService(ip + Constants.WS_NOTICIA_UNIDAD + IdFacultad, params, view.getContext(), FacultadInicioFragment.this);
            ws.execute("");
        }
    }

    public void setBanner() {
        String url = "";
        switch (IdFacultad.charAt(0)) {
            case '2':
                url = "http://www.uteq.edu.ec/images/slider/Ufca.jpg";
                break;
            case '3':
                url = "http://www.uteq.edu.ec/images/slider/Ufci.jpg";
                break;
            case '4':
                url = "http://www.uteq.edu.ec/images/slider/Ufcp.jpg";
                break;
            case '5':
                url = "http://www.uteq.edu.ec/images/slider/Ufcag.jpg";
                break;
            case '6':
                url = "http://www.uteq.edu.ec/images/slider/Ufce.jpg";
                break;
            case '7':
                url = "http://www.uteq.edu.ec/images/slider/Uued.jpg";
                break;
            case '8':
                url = "http://www.uteq.edu.ec/images/slider/Uup.jpg";
                break;
        }
        Glide.with(view.getContext())
                .load(url)
                .crossFade()
                .error(R.drawable.logouteqminres)
                .into(imageView);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray objdataarray = new JSONArray(result);
        ArrayList<Noticia> noticias = Noticia.JsonObjectsBuild(objdataarray);
        NoticiaAdapter noticiaAdapter = new NoticiaAdapter(view.getContext(), noticias, getFragmentManager());
        recyclerView.setAdapter(noticiaAdapter);
        ScrollView sv = (ScrollView) view.findViewById(R.id.scrollView);
        sv.scrollTo(0, 0);
    }
}