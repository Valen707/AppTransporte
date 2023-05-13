package com.example.apptransporte.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptransporte.R;
import com.example.apptransporte.transporte.Bus;
import com.example.apptransporte.transporte.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginUsuario extends AppCompatActivity {

    EditText CapCorreo;
    EditText CapContraseña;

    private boolean verificar = true;

    private FirebaseAuth mAuth;

    private String Lemail;
    private String Lcontraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        mAuth = FirebaseAuth.getInstance();
        CapCorreo = findViewById(R.id.CampCorreo);
        CapContraseña = findViewById(R.id.CampContraseña);
    }

    private boolean validar(){
        Lemail = CapCorreo.getText().toString().trim();
        Lcontraseña = CapContraseña.getText().toString().trim();

        if(Lemail.isEmpty()){
            Toast.makeText(this, "Ingrese un correo", Toast.LENGTH_SHORT).show();
            verificar = false;
        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Lemail).matches()) {
            Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show();
            verificar = false;
        } else if(Lcontraseña.isEmpty()){
            Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
            verificar = false;
        } else if(Lcontraseña.length() < 6){
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            verificar = false;
        }
        return verificar;
    }

    public void transporte(View n){
        if(validar()) {
            mAuth = FirebaseAuth.getInstance();

            mAuth.signInWithEmailAndPassword(Lemail, Lcontraseña)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                                Toast.makeText(LoginUsuario.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                                Intent ir = new Intent(this, MainActivity.class);
                                ir.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(ir);
                                finish();
                            } else {

                                Toast.makeText(LoginUsuario.this, "Usuario no exite.",
                                        Toast.LENGTH_SHORT).show();
                            Intent ir = new Intent(this, RegistrarUsusario.class);
                            ir.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(ir);
                            finish();
                            }

                    });
        }
    }

    public void Registrar(View n){
        Intent ir = new Intent(this, RegistrarUsusario.class);

        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ir);
    }
}