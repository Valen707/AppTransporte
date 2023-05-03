package com.example.apptransporte;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class API extends AppCompatActivity {
    private static final String TAG = "API";
    private RequestQueue requestQueue;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        // Inicializar la cola de solicitudes
        requestQueue = Volley.newRequestQueue(this);

        // Inicializar el ListView
        listView = findViewById(R.id.listView);

        // Hacer una solicitud HTTP GET a una API de ejemplo
        String url = "https://jsonplaceholder.typicode.com/posts";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Manejar la respuesta de la API
                        Log.d(TAG, "Respuesta: " + response.toString());

                        // Procesar los datos y agregarlos al ListView
                        ArrayList<String> dataList = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String body = jsonObject.getString("body");
                                dataList.add(title + "\n" + body);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "Datos procesados: " + dataList.toString());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                                android.R.layout.simple_list_item_1, dataList);
                        listView.setAdapter(adapter);
                        Log.d(TAG, "ListView inicializado correctamente.");
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
        requestQueue.add(jsonArrayRequest);
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
