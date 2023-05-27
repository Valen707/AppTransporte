package com.example.apptransporte.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptransporte.R;
import com.example.apptransporte.transporte.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarUsusario extends AppCompatActivity {

    private EditText email;
    private EditText contraseña;

    private boolean verificar = true;

    private FirebaseAuth mAuth;

    private String Remail;
    private String Rcontraseña;

    private FirebaseUser usuario;

    String llave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_ususario);

        email = findViewById(R.id.RGmail);
        contraseña = findViewById(R.id.RContraseña);
    }

    private boolean validar(){
        Remail = email.getText().toString().trim();
        Rcontraseña = contraseña.getText().toString().trim();

        if(Remail.isEmpty()){
            Toast.makeText(this, "Ingrese un correo", Toast.LENGTH_SHORT).show();
            verificar = false;
        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Remail).matches()) {
            Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show();
            verificar = false;
        } else if(Rcontraseña.isEmpty()){
            Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
            verificar = false;
        } else if(Rcontraseña.length() < 6){
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            verificar = false;
        }
        return verificar;
    }

    public void VLogin(View n){

        Remail = email.getText().toString().trim();
        Rcontraseña = contraseña.getText().toString().trim();


        if(validar()){


            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(Remail, Rcontraseña)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            usuario = mAuth.getCurrentUser();
                            FirebaseDatabase fd = FirebaseDatabase.getInstance();
                            DatabaseReference dr = fd.getReference("Usuarios");
                            dr.child(usuario.getUid()).setValue(new BaseDatos(Remail, Rcontraseña));

                            Toast.makeText(this, "Nuevo usuario creado.", Toast.LENGTH_SHORT).show();
                            Intent ir = new Intent(this, LoginUsuario.class);
                            ir.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(ir);
                            finish();
                        } else {
                            Toast.makeText(this, "Falló la autenticación.", Toast.LENGTH_SHORT).show();
                        }
                    });


        }

    }


}
