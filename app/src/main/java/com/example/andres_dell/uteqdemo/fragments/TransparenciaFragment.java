package com.example.andres_dell.uteqdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andres_dell.uteqdemo.R;
import com.example.andres_dell.uteqdemo.adapters.ExpandableListAdapter;
import com.example.andres_dell.uteqdemo.utils.Constants;
import com.example.andres_dell.uteqdemo.utils.UIUtil;
import com.example.andres_dell.uteqdemo.utils.WebServ.Asynchtask;
import com.example.andres_dell.uteqdemo.utils.WebServ.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransparenciaFragment extends Fragment implements Asynchtask {

    private ExpandableListView listView;
    private ListView listViewA;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    private ArrayList<String> lsAniosS=new ArrayList<>();
    private ArrayList<String> lsMeses=new ArrayList<>();
    private ArrayList<List<String>> lstMeses=new ArrayList<>();
    private ArrayAdapter<String> adp2;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    int idGrupo;
    View view;
    String ip;
    String tipoFragment;
    Bundle bundle;

    TextView txtTituloTranspa;

    public TransparenciaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.layout_listview, container, false);

        bundle=getArguments();
        ip="http://"+UIUtil.ipAConetarse(view.getContext());
        tipoFragment=bundle.getString("tipoFragment");

        listView = (ExpandableListView)view.findViewById(R.id.lvExp);
        //linearLayoutA=(LinearLayout)view.findViewById(R.id.lyCategoriasA);
        listViewA=(ListView)view.findViewById(R.id.lstCategoriasA);
        ConectWSAnios();
        //initData();

        return view;
    }

    private void ConectWSAnios() {
        if (!UIUtil.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(), "Comprueba tu conexión a Internet", Toast.LENGTH_LONG).show();
        } else {
            ip= "http://"+UIUtil.ipAConetarse(view.getContext());
            String url="";
            if(tipoFragment.equals("tr")){
                url=ip+ Constants.WS_LOTAIP_ANIOS;
                listView.setVisibility(View.VISIBLE);
                listViewA.setVisibility(View.INVISIBLE);
            }
            else if(tipoFragment.equals("rc")){
                url=ip+ Constants.WS_RC_ANIOS;
                listView.setVisibility(View.VISIBLE);
                listViewA.setVisibility(View.INVISIBLE);
            }
            else if(tipoFragment.equals("ar")) {
                url = ip + Constants.WS_ARCHIVOS;
                listView.setVisibility(View.INVISIBLE);
                listViewA.setVisibility(View.VISIBLE);
            }
            Map<String, String> params = new HashMap<String, String>();
            WebService ws = new WebService(url, params, view.getContext(), TransparenciaFragment.this);
            ws.execute("");
        }
    }

    private void ConectWSMeses(String anio) {
        if (!UIUtil.verificaConexion(view.getContext())) {
            Toast.makeText(view.getContext(), "Comprueba tu conexión a Internet", Toast.LENGTH_LONG).show();
        } else {
            ip= "http://"+UIUtil.ipAConetarse(view.getContext());
            Map<String, String> params = new HashMap<String, String>();
            WebService ws = new WebService(ip+ Constants.WS_LOTAIP_ANIOS, params, view.getContext(), TransparenciaFragment.this);
            ws.execute("");
        }
    }

    private void initData(ArrayList<String> lsAnios, final ArrayList<List<String>> lstMeses) {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.addAll(lsAnios);

        int nDeAnios=lsAnios.size();

        for(int i =0;i<nDeAnios;i++){
            listHash.put(listDataHeader.get(i),lstMeses.get(i));
        }
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(!parent.isGroupExpanded(groupPosition)){
                    parent.expandGroup(groupPosition);
                   // ConectWSMeses(lsAniosS.get(groupPosition));
                }
                else
                    parent.collapseGroup(groupPosition);
                idGrupo=groupPosition;
                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(view.getContext(), "Grupo: "+lsAniosS.get(groupPosition) +" - Item: "+ lstMeses.get(groupPosition).get(childPosition)
                        , Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent(getView().getContext(), TransparenciaActivity.class);

                Bundle b = new Bundle();
                b.putString("encabezado","Transparencia"+" - "+lsAniosS.get(groupPosition) +" - "+lstMeses.get(groupPosition).get(childPosition));
                b.putString("idAno",lsAniosS.get(groupPosition));
                b.putString("tipoFragment",tipoFragment);
                if(tipoFragment.equals("tr"))
                    b.putInt("idMes",lstMeses.get(groupPosition).size()-childPosition);
                else
                    b.putString("idMes",lstMeses.get(groupPosition).get(childPosition));

               // intent.putExtras(b);

                //startActivity(intent);

                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = new ArchivosTransparenciaFragment();
                fragment.setArguments(b);
                fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
                return true;
            }
        });
    }

    private void initDataSL(List<String> datos){
        adp2=new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, datos);

        listViewA.setAdapter(adp2);

        listViewA.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Bundle b = new Bundle();
                b.putString("encabezado",a.getItemAtPosition(position).toString());
                b.putString("idCategoria",String.valueOf(position+1));
                b.putString("tipoFragment",tipoFragment);
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = new ArchivosTransparenciaFragment();
                fragment.setArguments(b);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray objdataarray = new JSONArray(result);

        String[] months = new DateFormatSymbols().getMonths();
        int m;
        String mes;
        try {

            if(!tipoFragment.equals("ar")){
            for (int i = 0 ; i < objdataarray.length(); i++) {
                List<String> meses=new ArrayList<>();
                String anio=objdataarray.getJSONObject(i).getString("nivel1");

                lsAniosS.add(anio);

                //objdataarray.getJSONArray(0).getJSONObject(0).getString("");
                JSONArray objArrayMeses=new JSONArray(objdataarray.getJSONObject(i).getString("nivel2"));
                for(int j=0;j<objArrayMeses.length();j++){
                    //lstMeses.add(objArrayMeses.getJSONObject(j).getString("mes"));
                    mes=objArrayMeses.getJSONObject(j).getString("detalle");
                    if(tipoFragment.equals("tr")){
                        if(UIUtil.isNumeric(mes)){
                            m=Integer.valueOf(mes);
                            mes=months[m-1].toUpperCase();
                        }
                    }
                    else
                        mes="Fase "+mes;
                    meses.add(mes);
                }
                lstMeses.add(meses);

            }
            initData(lsAniosS,lstMeses);
            listAdapter = new ExpandableListAdapter(view.getContext(),listDataHeader,listHash);
            listView.setAdapter(listAdapter);
            }else{
                List<String> datos=new ArrayList<>();
                for (int i = 0 ; i < objdataarray.length(); i++) {
                    datos.add(objdataarray.getJSONObject(i).getString("descripcion"));
                }
                initDataSL(datos);
            }
        }catch (Exception e){

        }

    }


    @Override
    public void onResume() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Transparencia - UTEQ");
        txtTituloTranspa= (TextView) view.findViewById(R.id.txtTransparencia);
        txtTituloTranspa.setText("Transparencia");
        super.onResume();
    }


}