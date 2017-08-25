package com.example.andres_dell.uteqdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andres_dell.uteqdemo.activity.MainActivity;
import com.example.andres_dell.uteqdemo.fragments.FacultadFragment;
import com.example.andres_dell.uteqdemo.model.Oferta;
import com.example.andres_dell.uteqdemo.utils.Constants;
import com.example.andres_dell.uteqdemo.utils.UIUtil;
import com.example.andres_dell.uteqdemo.R;

import java.util.List;

/**
 * Created by ANDRES on 01/08/2017.
 */
public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.ViewHolder> {

    Context context;
    List<Oferta> facultades;
    private MainActivity mainActivity;

    public OfertaAdapter(Context context, List<Oferta> facultades, MainActivity mainActivity) {
        this.context=context;
        this.facultades=facultades;
        this.mainActivity =mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu,parent,false);
        ViewHolder viewHolder= new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titulo.setText(facultades.get(position).getTitulo());
        holder.id.setText(facultades.get(position).getRouting());
    }

    @Override
    public int getItemCount() {
        return facultades.size();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
       UIUtil.animateCircularReveal(holder.itemView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView titulo;
        TextView id;

        public ViewHolder(View item){
            super(item);
            cardView=(CardView) item.findViewById(R.id.cvMenu);
            titulo=(TextView) item.findViewById(R.id.LblTitulo);
            id=(TextView) item.findViewById(R.id.LblUrl);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = (TextView) v.findViewById(R.id.LblUrl);
                    Bundle b = new Bundle();
                    b.putString("id", (String) id.getText());
                    FacultadFragment fragment = new FacultadFragment();
                    fragment.setArguments(b);
                    FragmentTransaction fragmentTransaction = Constants.fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, fragment).addToBackStack(null);
                    fragmentTransaction.commit();;
                }
            });
        }
    }
}