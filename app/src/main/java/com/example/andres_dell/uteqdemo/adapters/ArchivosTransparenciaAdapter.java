package com.example.andres_dell.uteqdemo.adapters;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.andres_dell.uteqdemo.R;
import com.example.andres_dell.uteqdemo.model.ArchivoTransparencia;
import com.example.andres_dell.uteqdemo.utils.Constants;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ANDRES-DELL on 12/06/2017.
 */
public class ArchivosTransparenciaAdapter extends ArrayAdapter<ArchivoTransparencia> {

    String tipoFragment;
public ArchivosTransparenciaAdapter(Context context, ArrayList<ArchivoTransparencia> datos, String tipoFragment) {
        super(context, R.layout.ly_listaarchivos_transparencia, datos);
        this.tipoFragment=tipoFragment;
        }

public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_listaarchivos_transparencia, null);


        String urlArchivo;
        String[] months = new DateFormatSymbols().getMonths();
        if(tipoFragment.equals("tr")) {
            String anio=getItem(position).getAno();
            String mes = months[Integer.valueOf(getItem(position).getMes()) - 1];
            String mesPL = mes.substring(0, 1).toUpperCase();
            String mesS = mesPL + mes.substring(1, mes.length());
            urlArchivo = Constants.URL_ARCHIVO_LOTAIP + anio + "/" + mesS + "/" + getItem(position).getURLArchivo();
        }else if(tipoFragment.equals("rc")) {
            String anio=getItem(position).getAno();
            urlArchivo = Constants.URL_ARCHIVO_RENDICION + anio + "/" + getItem(position).getURLArchivo();
        }else
            urlArchivo=Constants.URL_UTEQ+"/"+getItem(position).getURLArchivo();

        TextView lblTitulo = (TextView)item.findViewById(R.id.LblTituloT);
        lblTitulo.setMovementMethod(LinkMovementMethod.getInstance());
        lblTitulo.setText(Html.fromHtml("<a href=\""+ urlArchivo+"\">"+getItem(position).getTituloArchivo()+"</a>"));

        TextView lblFecha = (TextView)item.findViewById(R.id.LblFechaT);
        lblFecha.setText(getItem(position).getFechaArchivo());

        return(item);
        }
}
