package com.example.myescuela;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myescuela.entidad.Estudiante;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

    private List<Estudiante> estudiantes;
    private OnEstudianteListener mOnEstudianteListener;

    public Adaptador(List<Estudiante> estudiantes, OnEstudianteListener onEstudianteListener)
    {
        this.estudiantes = estudiantes;
        this.mOnEstudianteListener = onEstudianteListener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView tvnombre,tvmatricula,tvcarrera;
        public LinearLayout lnlayout;
        OnEstudianteListener onEstudianteListener;

        public MyViewHolder(View v, OnEstudianteListener onEstudianteListener) {
            super(v);
            tvnombre = v.findViewById(R.id.textViewnombre);
            tvmatricula = v.findViewById(R.id.textViewmatricula);
            tvcarrera = v.findViewById(R.id.textViewcarrera);
            lnlayout = v.findViewById(R.id.linear_RowEstudiantes);
            this.onEstudianteListener = onEstudianteListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onEstudianteListener.onEstudianteClick(getAdapterPosition());
        }
    }

    //items onclick
    public interface OnEstudianteListener{
        void onEstudianteClick(int position);
    }


    // Create new views (invoked by the layout manager)
    @Override
    public Adaptador.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row, null);
        return new MyViewHolder(view,mOnEstudianteListener);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Estudiante estudiante = estudiantes.get(position);
        holder.tvnombre.setText(estudiante.getNombre());
        holder.tvmatricula.setText(estudiante.getMatricula());
        holder.tvcarrera.setText(estudiante.getCarrera().getNombre());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

}
