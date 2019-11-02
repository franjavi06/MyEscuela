package com.example.myescuela;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myescuela.entidad.Materia;

import java.util.List;

public class MateriaAdaptadorC extends RecyclerView.Adapter<MateriaAdaptadorC.MyViewHolder>{

    private List<Materia> materias;
    private OnMateriaListener  mOnMateriaListener;
    private int seleccionado;

    public MateriaAdaptadorC(List<Materia> materias, OnMateriaListener onMateriaListener, int seleccionado)
    {
        this.materias = materias;
        this.mOnMateriaListener = onMateriaListener;
        this.seleccionado = seleccionado;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView tvmateria,tvcreditos;
        public OnMateriaListener onMateriaListener;
        public LinearLayout lnrowmateria;

        public MyViewHolder(View v, OnMateriaListener onMateriaListener) {
            super(v);
            tvmateria = v.findViewById(R.id.textViewnombrematerias_ListaMateriasC);
            tvcreditos = v.findViewById(R.id.textViewcantidades_ListaMateriasC);
            lnrowmateria = v.findViewById(R.id.linear_row_materia_AddCarrera);
            this.onMateriaListener = onMateriaListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onMateriaListener.onMateriaClick(getAdapterPosition());
        }
    }

    //items onclick
    public interface OnMateriaListener{
        void onMateriaClick(int position);
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MateriaAdaptadorC.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.materia_rowc, null);
        return new MateriaAdaptadorC.MyViewHolder(view,mOnMateriaListener);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MateriaAdaptadorC.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Materia materia = materias.get(position);
        holder.tvmateria.setText(materia.getNombre());
        holder.tvcreditos.setText(materia.getCreditos().toString()+" creditos");
        if (position == seleccionado)
            holder.lnrowmateria.setBackgroundColor(Color.rgb(145,240,255));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return materias.size();
    }
}
