package com.example.projetappliactioncoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LangueAdapter extends ArrayAdapter<LangueModelClass> {

    public LangueAdapter(Context context, ArrayList<LangueModelClass> langueList){
        super(context, 0, langueList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initierView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initierView(position, convertView, parent);
    }

    private View initierView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pays_layout_row, parent, false);
        }

        ImageView imageViewPays = convertView.findViewById(R.id.imageViewPaysDrapeau);
        TextView textViewLangue = convertView.findViewById(R.id.langueTextView);

        LangueModelClass langueItem = getItem(position);

        if(langueItem != null) {
            imageViewPays.setImageResource(langueItem.getDrapeauImage());
            textViewLangue.setText(langueItem.getNomPays());
        }

        return convertView;
    }
}
