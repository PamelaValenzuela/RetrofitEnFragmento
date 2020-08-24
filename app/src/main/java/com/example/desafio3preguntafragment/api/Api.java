package com.example.desafio3preguntafragment.api;

import com.example.desafio3preguntafragment.beans.PreguntasLista;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("api.php?amount=1&category=18&difficulty=medium&type=boolean")
    Call<PreguntasLista> getAllQuestions();
}
