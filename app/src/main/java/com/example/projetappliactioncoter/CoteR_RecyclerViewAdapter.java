package com.example.projetappliactioncoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoteR_RecyclerViewAdapter extends RecyclerView.Adapter<CoteR_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<CoteRModel> coteRModels;
    private OnItemClickListener mListener;

    public CoteR_RecyclerViewAdapter(Context context, ArrayList<CoteRModel> coteRModels){
        this.context = context;
        this.coteRModels = coteRModels;
    }



    @NonNull
    @Override
    public CoteR_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);


        return new CoteR_RecyclerViewAdapter.MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CoteR_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.coterTextView.setText(coteRModels.get(position).getCoteR());
        holder.matiereTextView.setText(coteRModels.get(position).getMatière());
    }

    @Override
    public int getItemCount() {
        return coteRModels.size();
    }

    public interface OnItemClickListener{
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView matiereTextView, coterTextView;
        ImageView editImageView, deleteImageView;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            matiereTextView = (TextView) itemView.findViewById(R.id.matiere_coteR_calculatrice);
            coterTextView = (TextView) itemView.findViewById(R.id.coteR_calculatrice);
            editImageView = (ImageView) itemView.findViewById(R.id.edit_calculatrice);
            deleteImageView = (ImageView) itemView.findViewById(R.id.delete_calculatrice);

            editImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                        }
                    }
                }
            });

            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });


        }
    }
}
