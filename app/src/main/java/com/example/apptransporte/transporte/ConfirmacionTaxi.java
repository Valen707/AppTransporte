package com.example.apptransporte.transporte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apptransporte.R;

import java.util.Random;

public class ConfirmacionTaxi extends AppCompatActivity {
    TextView Dcliente;
    TextView Ddestino;
    TextView NumT;
    TextView linea;
    TextView persona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_taxi);

        Dcliente = findViewById(R.id.DirrecionCliente);
        Ddestino = findViewById(R.id.DireccionDestino);
        NumT = findViewById(R.id.NumTaxi);
        linea = findViewById(R.id.PagoLinea);
        persona = findViewById(R.id.PagoPersona);

        Bundle RecibeDCliente = getIntent().getExtras();
        Bundle RecibeDdestino = getIntent().getExtras();
        Bundle PagoLinea = getIntent().getExtras();
        Bundle PagoPersona = getIntent().getExtras();

        String InfoDcliente = RecibeDCliente.getString("keyDatosDcliente");
        String InfoDdestino = RecibeDdestino.getString("keyDatosDdestino");
        String InfoPLinea = PagoLinea.getString("keyDatosDLinea");
        String InfoPersona = PagoPersona.getString("keyDatosPersona");


        Dcliente.setText("Dirección del Cliente: " + InfoDcliente);
        Ddestino.setText("Dirección destino: " + InfoDdestino);

        NumT.setText("Numero del Taxi:" + NumeroAleatorio());
        linea.setText("Pago en linea:" + InfoPLinea);
        persona.setText("Pago en Persona:" + InfoPersona);

    }

    public int NumeroAleatorio(){
        Random random = new Random();
        int numero = random.nextInt(999) + 1;
        return numero;
    }

    public void volver(View n){
        Intent ir = new Intent(this, MainActivity.class);

        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ir);
    }
}