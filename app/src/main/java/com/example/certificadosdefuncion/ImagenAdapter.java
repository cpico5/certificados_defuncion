package com.example.certificadosdefuncion;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.certificadosdefuncion.model.Imagen;

import java.util.List;


public class ImagenAdapter extends RecyclerView.Adapter<ImagenAdapter.ImagenViewHolder> {

    private List<Imagen> listaImagen;
    private View.OnClickListener mOnItemClickListener;
    private View.OnClickListener mOnItemClickListenerDelete;


    public ImagenAdapter(List<Imagen> listaImagenActa) {
        this.listaImagen = listaImagenActa;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public void setOnItemClickListenerDelete(View.OnClickListener itemClickListener) {
        mOnItemClickListenerDelete = itemClickListener;
    }

    @NonNull
    @Override
    public ImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_fotos, parent, false);
        ImagenViewHolder viewHolder = new ImagenViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ImagenViewHolder holder, int position) {
        //holder.txtNombre.setText(listaImagen.get(position).getPathImagen());
        position++;

        holder.txtNombre.setText("Imagen " + position);

    }

    @Override
    public int getItemCount() {
        return listaImagen.size();
    }


    public class ImagenViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        //ImageButton btnDelete;
        Button btnDelete;

        public ImagenViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre =     itemView.findViewById(R.id.idNombre);
            //btnDelete =   (ImageButton)  itemView.findViewById(R.id.btnDelete);
            btnDelete =   (Button)  itemView.findViewById(R.id.btnDelete);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

            btnDelete.setTag(this);
            btnDelete.setOnClickListener(mOnItemClickListenerDelete);

        }
    }
}
