package com.example.myescuela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    Button estudiantesbtn, materiasbtn, carrerasbtn, salirbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        estudiantesbtn = findViewById(R.id.btnestudiantes);
        materiasbtn = findViewById(R.id.btnmaterias);
        carrerasbtn = findViewById(R.id.btncarreras);
        salirbtn = findViewById(R.id.btnsalir);

        estudiantesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aEstudiantes = new Intent(v.getContext(),MainActivity.class);
                startActivity(aEstudiantes);
            }
        });

        materiasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aMaterias = new Intent(v.getContext(),ListaMaterias.class);
                startActivity(aMaterias);
            }
        });

        carrerasbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aCarreras = new Intent(v.getContext(),ListaCarreras.class);
                startActivity(aCarreras);
            }
        });

        salirbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
