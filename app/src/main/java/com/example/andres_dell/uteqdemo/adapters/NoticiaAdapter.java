package com.example.andres_dell.uteqdemo.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andres_dell.uteqdemo.fragments.VerNoticiaFragment;
import com.example.andres_dell.uteqdemo.model.Noticia;
import com.example.andres_dell.uteqdemo.utils.Constants;
import com.example.andres_dell.uteqdemo.utils.UIUtil;
import com.example.andres_dell.uteqdemo.R;

import java.util.List;

/**
 * Created by ANDRES-DELL on 01/08/2017.
 */
public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {

    Context context;
    List<Noticia> noticiasList;

    private FragmentManager fragmentManager;

    public NoticiaAdapter(Context context, List<Noticia> noticiasList, FragmentManager fragmentManager) {
        this.context = context;
        this.noticiasList = noticiasList;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_noticia, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.titulo.setText(Html.fromHtml(noticiasList.get(position).getTitulo()));
        holder.subtitulo.setText(Html.fromHtml(noticiasList.get(position).getSubtitulo() + "... <b>Leer mas.</b>"));
        String fecha = noticiasList.get(position).getFecha().toString().length() > 10 ? noticiasList.get(position).getFecha().toString().substring(0, 10) : noticiasList.get(position).getFecha().toString();
        holder.fechaCategoria.setText(fecha + " | " + noticiasList.get(position).getCategoria());
        holder.id.setText(noticiasList.get(position).getIdNoticia());
        //cargar la imagen para cada noticia
        String URLImg = Constants.URL_UTEQ.concat("/");
        String url = noticiasList.get(position).getPath();

        Glide.with(this.context)
                .load(URLImg.concat(url))
                .crossFade()
                .error(R.drawable.logouteqminres)
                .into(holder.imgNoticia);
    }

    @Override
    public int getItemCount() {
        return noticiasList.size();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        UIUtil.animateCircularReveal(holder.itemView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView titulo, subtitulo, fechaCategoria, id;
        ImageView imgNoticia;

        public ViewHolder(View item){
            super(item);
            id = (TextView) item.findViewById(R.id.LblId);
            cardView=(CardView) item.findViewById(R.id.cvNoticia);
            titulo=(TextView) item.findViewById(R.id.LblTitulo);
            subtitulo=(TextView) item.findViewById(R.id.LblSubTitulo);
            fechaCategoria=(TextView) item.findViewById(R.id.LblFecha);
            imgNoticia=(ImageView)item.findViewById(R.id.imgNoti);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titulo = (TextView) v.findViewById(R.id.LblTitulo);
                    FragmentTransaction fragmentTransaction = Constants.fragmentManager.beginTransaction();
                    VerNoticiaFragment fragment = new VerNoticiaFragment();
                    Bundle b = new Bundle();
                    b.putString("titulo", "Noticias");
                    b.putString("id",id.getText().toString());
                    fragment.setArguments(b);
                    fragmentTransaction.replace(R.id.fragment, fragment).addToBackStack(null);
                    fragmentTransaction.commit();
                    try {
                        finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            });
        }
    }
}
