package com.example.desafio3preguntafragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.desafio3preguntafragment.api.Api;
import com.example.desafio3preguntafragment.api.RetrofitClient;
import com.example.desafio3preguntafragment.beans.Pregunta;
import com.example.desafio3preguntafragment.beans.PreguntasLista;
import com.example.desafio3preguntafragment.fragment.Fragmento;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Pregunta:";
    private static final String TAG2 = "ONCREATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG2, "onCreate: Creando la actividad");

        Api service = RetrofitClient.getRetrofitInstance().create(Api.class);
        Log.i("obtuvoprimero", "si se hace");
        Call<PreguntasLista> call = service.getAllQuestions();
    Log.i("segundo","yapor fin estee se obtiene");
        call.enqueue(new Callback<PreguntasLista>() {
            @Override
            public void onResponse(Call<PreguntasLista> call, Response<PreguntasLista> response) {
                Log.d("Respuesta api", response.toString());
               PreguntasLista preguntas = response.body();

                if (preguntas != null) {
                    Pregunta pregunta;
                    pregunta =  preguntas.getResults().get(0);

                    Fragmento frag = Fragmento.newInstance(
                            pregunta.getQuestion(),
                            pregunta.getCategory(),
                            pregunta.getDifficulty(),
                            pregunta.getCorrect_answer()
                    );

                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragment_container, frag, "FRAGMENTO")
                            .commit();
                }
            }
            @Override
            public void onFailure(Call<PreguntasLista> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: no se pudo recuperar los datos",
                        Toast.LENGTH_LONG).show();}
        }
                        ); }}
