package com.example.apptransporte;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class API extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la cola de solicitudes
        requestQueue = Volley.newRequestQueue(this);

        // Hacer una solicitud HTTP GET a una API de ejemplo
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Manejar la respuesta de la API
                        Log.d(TAG, "Respuesta: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud
                        Log.e(TAG, "Error: " + error.getMessage());
                    }
                }
        );

        // Agregar la solicitud a la cola de solicitudes
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    protected void onStop() {
        super.onStop();
        // Cancelar todas las solicitudes en la cola cuando la actividad se detiene
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }
}

