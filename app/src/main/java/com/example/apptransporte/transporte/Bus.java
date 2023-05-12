package com.example.apptransporte.transporte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.apptransporte.R;

public class Bus extends AppCompatActivity {

    EditText CampDestino;
    EditText CampTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        CampDestino = findViewById(R.id.TextDestino);
        CampTime = findViewById(R.id.TextTime);
    }

    public void confirmarBus(View b){
        Intent ir = new Intent(this, ConfirmacionBus.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ir);

    }

    public static class ModeloRetorno {
    }
}