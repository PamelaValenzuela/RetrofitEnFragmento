package com.example.desafio3preguntafragment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desafio3preguntafragment.R;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento extends Fragment {

    int radioButtonValue = 0;
    private TextView preguntaView, categoriaView,dificultadView;
    private RadioGroup grupoRespuestasView;
    private RadioButton respuestaUno, respuestaDos;

    public static Fragmento newInstance(String pregunta, String categoria,
                                 String dificultad, String respuestaCorrecta) {

            Fragmento mFragmento= new Fragmento();
            Bundle mArguments= new Bundle();

            mArguments.putString("PREGUNTA",pregunta);
            mArguments.putString("CATEGORIA",categoria);
            mArguments.putString("DIFICULTAD",dificultad);
            mArguments.putString("RESPUESTA_CORRECTA",respuestaCorrecta);
            mFragmento.setArguments(mArguments);
            return mFragmento;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragmento.
     */
    // TODO: Rename and change types and number of parameters
   /* public static Fragmento newInstance(String param1, String param2) {
        Fragmento fragment = new Fragmento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);}  }*/
//he agregado nonnull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,@NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment, paso uno en oncreateView
        View mView = inflater.inflate(R.layout.fragment_layout, container, false);
        //deberiamos inicializar las vistas en este metodo utilizando el que hicimos
        //para enlazar las vistas, pero tenemos que asegurarle que no serán null, esto
        //lo conseguimos utilizando los metodos de la clase fragment,requierenonnull getArguments, getPut

//Paso 3: asegurarnos que nuestras variables no son nulas.

        final String pregunta = Objects.requireNonNull(getArguments().getString("PREGUNTA"));
        final String categoria = Objects.requireNonNull(getArguments().getString("CATEGORIA"));
        final String dificultad = Objects.requireNonNull(getArguments().getString("DIFICULTAD"));
        final String respuestaCorrecta = Objects.requireNonNull(getArguments().getString("RESPUESTA_CORRECTA"));

        //Paso 4 insertar el texto en nuestras variables declaradas, de esta manera hacemos que este
        //reciba valores dinamicos, debemos inicializar las vistas
        initilizeViews(mView);

        preguntaView.setText(pregunta);
        categoriaView.setText(categoria);
        dificultadView.setText(dificultad);

        grupoRespuestasView.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (respuestaUno.isChecked()) {
                    radioButtonValue = 1;
                } else if (respuestaDos.isChecked()) {
                    radioButtonValue = 0;
                }
            }
        });

        Button boton = mView.findViewById(R.id.btnConsultaRespuesta);
        boton.setOnClickListener(v -> {

            if (respuestaUno.isChecked() && respuestaCorrecta.equals("True")) {
                Toast.makeText(getContext(), getString(R.string.correcto), Toast.LENGTH_SHORT).show();
            } else if (respuestaDos.isChecked() && respuestaCorrecta.equals("False")) {
                Toast.makeText(getContext(), getString(R.string.correcto), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), getString(R.string.incorrecto), Toast.LENGTH_SHORT).show();
            }

        });

return mView;
    }
    //paso 2 para utilizar el metodo oncreateView, creamos un metodo para enlazar las vistas
    private void initilizeViews(View view) {
        preguntaView = view.findViewById(R.id.pregunta);
        categoriaView = view.findViewById(R.id.categoria);
        dificultadView = view.findViewById(R.id.dificultad);
        grupoRespuestasView = view.findViewById(R.id.radioGrupoRespuestas);
        respuestaUno = view.findViewById(R.id.radioRespuestaUno);
        respuestaDos = view.findViewById(R.id.radioRespuestaDos);}
}