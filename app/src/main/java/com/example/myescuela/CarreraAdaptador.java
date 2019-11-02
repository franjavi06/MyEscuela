package com.example.myescuela;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Estudiante;

import java.util.List;

public class CarreraAdaptador extends RecyclerView.Adapter<CarreraAdaptador.MyViewHolder> {


    private List<Carrera> carreras;

    public CarreraAdaptador(List<Carrera> carreras)
    {

        this.carreras = carreras;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvcarrera,tvcantidades;

        public MyViewHolder(View v) {
            super(v);
            tvcarrera = v.findViewById(R.id.textViewnombrecarrera);
            tvcantidades = v.findViewById(R.id.textViewcantidades_ListaCarreras);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CarreraAdaptador.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.carrerra_row, null);
        return new CarreraAdaptador.MyViewHolder(view);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Carrera carrera = carreras.get(position);
        holder.tvcarrera.setText(carrera.getNombre());
        holder.tvcantidades.setText(String.valueOf(carrera.getCantMaterias())+" materias, "+String.valueOf(carrera.getCantCreditos())+" creditos");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return carreras.size();
    }

}
