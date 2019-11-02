package com.example.myescuela;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Materia;

import java.util.List;

public class MateriaAdaptador extends RecyclerView.Adapter<MateriaAdaptador.MyViewHolder>{

    private List<Materia> materias;

    public MateriaAdaptador(List<Materia> materias)
    {

        this.materias = materias;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvmateria,tvcreditos;

        public MyViewHolder(View v) {
            super(v);
            tvmateria = v.findViewById(R.id.textViewnombrematerias_ListaMaterias);
            tvcreditos = v.findViewById(R.id.textViewcantidades_ListaMaterias);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MateriaAdaptador.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.materia_row, null);
        return new MateriaAdaptador.MyViewHolder(view);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MateriaAdaptador.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Materia materia = materias.get(position);
        holder.tvmateria.setText(materia.getNombre());
        holder.tvcreditos.setText(materia.getCreditos().toString()+" creditos");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return materias.size();
    }

}
