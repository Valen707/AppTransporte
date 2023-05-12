package com.example.apptransporte.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptransporte.R;
import com.example.apptransporte.transporte.MainActivity;

public class RegistrarUsusario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_ususario);
    }
    public void VLogin(View n){
        Intent ir = new Intent(this, LoginUsuario.class);

        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ir);
    }
}