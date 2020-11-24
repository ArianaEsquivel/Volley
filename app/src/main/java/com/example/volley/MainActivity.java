package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private RequestQueue cartero;
    private RecyclerView mRvListaResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                //mando llamar objeto persona                       //mando llamar solo la uña
        cartero = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        //Saque el perrito de la caja

        //obtenerObjeto();
        ObtenerDatos();
        obtenerObjeto_lista();

    }

    //SERIALIZACÓN = Convertir a JSON
    //DESERIALIZACIÓN = Convertir de JSON al objeto

    private void obtenerObjeto_lista(){
        mRvListaResultados = findViewById(R.id.resultados);
        mRvListaResultados.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvListaResultados.setLayoutManager(linearLayoutManager);

        String url = "http://jsonplaceholder.typicode.com/posts";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson convertidor = new Gson();
                Type tipoListaResultado = new TypeToken<List<Resultado>>(){}.getType();
                List<Resultado> resultados = convertidor.fromJson(response.toString(), tipoListaResultado);

                //for (int i = 0; i < resultados.size(); i++ ) {
                    //try {
                        //JSONObject posts = response.getJSONObject(i);
                        //int userId = resultados.get(i).getUserId();
                        //int id = resultados.get(i).getId();
                        //String title = resultados.get(i).getTitle();
                        //String body = resultados.get(i).getBody();

                        //TextView text = findViewById(R.id.res);
                        //text.append(resultados.get(i).getUserId() +"\n" +
                                //resultados.get(i).getBody() +"\n" +
                                //resultados.get(i).getTitle() +"\n" +
                                //resultados.get(i).getBody() +"\n" );
                    //} catch (JSONException e) {
                      //  e.printStackTrace();
                    //}
                //}

                AdapterResultado mAdapter = new AdapterResultado(resultados);
                mRvListaResultados.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //cartero.add(request);
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void obtenerObjeto() {
        String url = "http://www.ramiro174.com/api/persona";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String nombre = response.getString("nombre");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Gson convertidor = new Gson();

                //Es una deserialización porque convierto un estandar json a un objeto java
                Persona persona = convertidor.fromJson(response.toString(), Persona.class); //Nos devuelve un objeto java

                Toast.makeText(MainActivity.this, persona.getNombre(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        cartero.add(request);
    }


    private void ObtenerDatos() {
        String url = "https://api.androidhive.info/contacts/"; //api public
        //Tipos de peticiones
        //JsonArrayRequest
        //StringRequest
        //ImageRequest
        //debe tregresar un JsonObjectRequest (debe llevar llaves {}) (los [] son arrays)
        JsonObjectRequest carta1 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray lista_contactos = response.getJSONArray("contacts");

                    for (int i = 0; i < lista_contactos.length(); i++ ) {
                        JSONObject contactos = lista_contactos.getJSONObject(i);

                        JSONObject phone = contactos.getJSONObject("phone");

                        String home = phone.getString("home");

                        String name = contactos.getString("name");//PASANDO AL TEXT VIEW
                        //TextView text = findViewById(R.id.name);
                        //text.append(name +"\n");

                        //Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        StringRequest carta2 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        cartero.add(carta1); //cartero = cola de peticciones
        cartero.add(carta2);


        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(carta1);
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(carta2);

    }
}