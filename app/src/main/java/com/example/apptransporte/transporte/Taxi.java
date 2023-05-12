package com.example.apptransporte.transporte;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.apptransporte.R;
import com.example.apptransporte.transporte.ConfirmacionTaxi;
import com.example.apptransporte.transporte.MainActivity;

public class Taxi extends AppCompatActivity {
    EditText CampClient;
    EditText CampDest;
    CheckBox checkBoxPagoEnLinea;
    CheckBox checkBoxPagoEnPersona;
    RadioButton R_Aceptar;
    boolean pagoSeleccionado = false;
    boolean AceptarT = false;
    String PagoLinea ="No";
    String PagoPersona = "No";
    String Aceptar = "No";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);

        CampClient = (EditText)findViewById(R.id.CampCliente);
        CampDest = (EditText)findViewById(R.id.CampDestino);
        checkBoxPagoEnLinea = (CheckBox)findViewById(R.id.Linea);
        checkBoxPagoEnPersona = (CheckBox)findViewById(R.id.Persona);
        R_Aceptar = findViewById(R.id.RadioAceptar);

        checkBoxPagoEnLinea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxPagoEnPersona.setChecked(false);
                    pagoSeleccionado = true;
                    PagoLinea = "Si";
                }else{
                    PagoLinea = "No";
                }
            }
        });

        checkBoxPagoEnPersona.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxPagoEnLinea.setChecked(false);
                    pagoSeleccionado = true;
                    PagoPersona = "Si";
                }else{
                    PagoPersona = "No";
                }
            }
        });

        R_Aceptar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AceptarT = true;
                    Aceptar = "Si";
                }
            }
        });
    }

    public void confirmar(View c){
        if (validar()){

            Bundle DatosCcliente = new Bundle();
            Bundle DatosCDestino = new Bundle();
            Bundle DatosPLinea = new Bundle();
            Bundle DatosPersona = new Bundle();
            Bundle Terminos = new Bundle();

            DatosCcliente.putString("keyDatosDcliente", CampClient.getText().toString());
            DatosCDestino.putString("keyDatosDdestino", CampDest.getText().toString());
            DatosPLinea.putString("keyDatosDLinea", PagoLinea);
            DatosPersona.putString("keyDatosPersona", PagoPersona);
            Terminos.putString("keyDatosTerminos", Aceptar);


            Intent ir = new Intent(this, ConfirmacionTaxi.class);
            ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
            ir.putExtras(DatosCcliente);
            ir.putExtras(DatosCDestino);
            ir.putExtras(DatosPLinea);
            ir.putExtras(DatosPersona);
            ir.putExtras(DatosPersona);
            startActivity(ir);
        }
    }

    public boolean validar(){
        boolean verificar = true;
        String Cclient = CampClient.getText().toString();
        String Cdest = CampDest.getText().toString();

        if (Cclient.isEmpty()){
            CampClient.setError("Campo obligatorio");
            verificar = false;
        }
        if (Cdest.isEmpty()){
            CampDest.setError("Campo obligatorio");
            verificar = false;
        }

        if (!pagoSeleccionado) {
            Toast.makeText(getApplicationContext(), "Debe seleccionar una opción de pago", Toast.LENGTH_SHORT).show();
            verificar = false;
        }
        if (!AceptarT) {
            Toast.makeText(getApplicationContext(), "Debe Aceptar los Terminos y Condiciones", Toast.LENGTH_SHORT).show();
            verificar = false;
        }


        return verificar;
    }
    public void volver(View n){
        Intent ir = new Intent(this, MainActivity.class);

        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ir);
    }
}
