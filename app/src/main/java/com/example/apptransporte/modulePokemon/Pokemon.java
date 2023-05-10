package com.example.apptransporte.modulePokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apptransporte.R;
import com.example.apptransporte.modulePokemon.ModeloRetorno;
import com.google.android.material.textfield.TextInputEditText;

public class Pokemon extends AppCompatActivity {
    public Button btn_Consulta;
    public TextInputEditText txtConsulta;
    public String respuesta = "";
    public String imagen = "";
    public static ModeloRetorno pokedex = new ModeloRetorno();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        btn_Consulta = findViewById(R.id.btnConsultar);
        txtConsulta = findViewById(R.id.in_consulta);
        btn_Consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarApi rg = new consultarApi();
                try {
                    rg.respuesta(txtConsulta.getText().toString());
                    muestraToast("Prosesando...");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pokedex = rg.modeloRetorno;
                            respuesta = "ID: " + pokedex.getId() + "\n" +
                                    "Nombre: " + pokedex.getName() + "\n" +
                                    "Altura: " + pokedex.getHeight() + "\n" +
                                    "Peso: " + pokedex.getWeight();

                            imagen = pokedex.getFront_default();
                            if(!respuesta.equals("")){
                                Intent intent = new Intent(Pokemon.this, Consulta_Activity.class);


                                intent.putExtra("informacion", respuesta);
                                intent.putExtra("imagen", imagen);
                                startActivity(intent);
                            }
                        }
                    }, 5000);
                }catch (Exception e){
                    muestraToast("Error" + e);
                }
            }
        });
    }
    public void muestraToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}