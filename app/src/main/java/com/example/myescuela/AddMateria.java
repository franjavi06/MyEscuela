package com.example.myescuela;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myescuela.entidad.Carrera;
import com.example.myescuela.entidad.Materia;
import com.example.myescuela.repositorio.MateriaRepositorioDbImpl;

public class AddMateria extends AppCompatActivity {

    EditText nombreMateria;
    EditText creditosMateria;
    Button btncancelar;
    Button btnguardar;
    MateriaRepositorioDbImpl materiaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materia);
        nombreMateria = findViewById(R.id.editTextNombreMateria_AddMateria);
        creditosMateria = findViewById(R.id.editTextCreditosMateria_AddMateria);
        btncancelar = findViewById(R.id.buttonCancelar_AddMateria);
        btnguardar = findViewById(R.id.buttonGuardar_AddMateria);
        materiaRepositorio = new MateriaRepositorioDbImpl(this.getBaseContext());

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombreMateria.getText().toString().length() == 0 ||  creditosMateria.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    String mensaje = "";
                    //Toast.makeText(getApplicationContext(), cs.getId().toString() , Toast.LENGTH_SHORT).show();
                    long respuesta = materiaRepositorio.crear(new Materia(nombreMateria.getText().toString(),Integer.parseInt(creditosMateria.getText().toString())));
                    if (respuesta==-1)
                        mensaje = "Error al Crear Materia";
                    else
                    {
                        mensaje = "Materia Creada! Id: "+respuesta;

                    }
                    //mensaje=nombreMateria.getText().toString()+" "+creditosMateria.getText().toString();
                    Toast.makeText(getApplicationContext(), mensaje , Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }
}
