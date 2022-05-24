package com.example.projetappliactioncoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CoteR_RecyclerViewAdapter extends RecyclerView.Adapter<CoteR_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<CoteRModel> coteRModels;
    private OnItemClickListener mListener;

    /**
     * Conctructeur
     * @param context
     * @param coteRModels
     */
    public CoteR_RecyclerViewAdapter(Context context, ArrayList<CoteRModel> coteRModels){
        this.context = context;
        this.coteRModels = coteRModels;

    }


    /**
     * Méthode qui permet de montrer ce qu’il y a dans le recyclerview
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public CoteR_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);


        return new CoteR_RecyclerViewAdapter.MyViewHolder(view, mListener);
    }

    /**
     * Méthode qui permet de faire le lien entre le recyclerview et le array list des cote r
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CoteR_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.coterTextView.setText(coteRModels.get(position).getCoteR());
        holder.matiereTextView.setText(coteRModels.get(position).getMatière());
    }

    /**
     * Méthode qui permet de compter le size du arraylist
     * @return
     */
    @Override
    public int getItemCount() {
        return coteRModels.size();
    }

    /**
     * interface pour les méthodes qui vont être utilisées une fois que l’on click sur un item du recyclerview
     */
    public interface OnItemClickListener{
        /**
         * Méthode qui permet de edit une cote r
         * @param position
         */
        void onEditClick(int position);

        /**
         * Méthode qui permet de delete une cote r
         * @param position
         */
        void onDeleteClick(int position);
    }

    /**
     * Méthode qui permet de set une interface pour pouvoir l’utiliser
     * @param listener
     */
    public void setOnItemListener(OnItemClickListener listener){
        mListener = listener;
    }

    /**
     * Class static du recycler view
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView matiereTextView, coterTextView;
        ImageView editImageView, deleteImageView;

        /**
         * Permet de lier les objet du fichier .xml à la class .java
         * @param itemView
         * @param listener
         */
        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            //Initialisation de toutes les différents objets dans notre fichier .xml
            matiereTextView = (TextView) itemView.findViewById(R.id.matiere_coteR_calculatrice);
            coterTextView = (TextView) itemView.findViewById(R.id.coteR_calculatrice);
            editImageView = (ImageView) itemView.findViewById(R.id.edit_calculatrice);
            deleteImageView = (ImageView) itemView.findViewById(R.id.delete_calculatrice);

            //Set on click si l’utilisateur appuie sur l’image du crayon pour edit une cote r
            editImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){

                        //Get la position pour savoir quelle cote r a été modifiée
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                        }
                    }
                }
            });

            //Set on click si l’utilisateur appuie sur l’image de la poubelle pour delete une cote r
            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        //Get la position pour savoir quelle cote r a été modifiée
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
