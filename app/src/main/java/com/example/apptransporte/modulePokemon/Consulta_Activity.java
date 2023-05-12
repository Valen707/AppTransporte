package com.example.apptransporte.modulePokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptransporte.R;
import com.squareup.picasso.Picasso;

public class Consulta_Activity extends AppCompatActivity {
    TextView txt_info;
    ImageView img1;
    Button btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        txt_info = findViewById(R.id.txt_info);
        img1 = findViewById(R.id.imageView);
        btn_volver = findViewById(R.id.btn_volver);

        Bundle extra = getIntent().getExtras();
        String informacion = extra.getString("informacion");
        String imagen = extra.getString("imagen");
        txt_info.setText(informacion);
        //System.out.println(imagen);
        Picasso.get().load(imagen).error(R.mipmap.ic_launcher).into(img1);
    }
    public void volver(View n){
        Intent ir = new Intent(this, Pokedex.class);

        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TASK | ir.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ir);
    }

    public void muestraToast(String mensaje){
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}