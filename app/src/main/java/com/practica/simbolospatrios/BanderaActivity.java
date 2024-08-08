package com.practica.simbolospatrios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class BanderaActivity extends AppCompatActivity {
    private TextView textoPregunta;
    private RadioGroup grupoOpciones;
    private Button botonEnviar, botonReiniciar;
    private ImageView imagenGancho;

    private ArrayList<Pregunta> preguntas;
    private Pregunta preguntaActual;
    private int indicePregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bandera);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Bandera), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textoPregunta = findViewById(R.id.questionTextView);
        grupoOpciones = findViewById(R.id.optionsRadioGroup);
        botonEnviar = findViewById(R.id.submitButton);

        // Inicialización de preguntas
        inicializarPreguntas();

        // Mostrar primera pregunta
        mostrarSiguientePregunta();

        // Manejo del botón de respuesta
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta();
            }
        });
    }

    private void inicializarPreguntas() {
        preguntas = new ArrayList<>();

        // Agregar preguntas sobre la bandera de Panamá
        preguntas.add(new Pregunta("¿Qué representa el color blanco en la bandera de Panamá?",
                new String[]{"La paz", "La valentía", "La pureza", "La lealtad"},
                "La paz"));
        preguntas.add(new Pregunta("¿Cuántas estrellas tiene la bandera de Panamá?",
                new String[]{"Una", "Dos", "Tres", "Cuatro"},
                "Dos"));
        preguntas.add(new Pregunta("¿Qué representa el cuadrado azul en la bandera de Panamá?",
                new String[]{"La justicia", "El cielo", "La pureza", "La lealtad"},
                "La lealtad"));

        // Mezclar el orden de las preguntas
        Collections.shuffle(preguntas);
        indicePregunta = 0;
    }

    private void mostrarSiguientePregunta() {
        preguntaActual = preguntas.get(indicePregunta);

        // Mostrar la pregunta y opciones en la interfaz
        textoPregunta.setText(preguntaActual.getTextoPregunta());

        String[] opciones = preguntaActual.getOpciones();
        for (int i = 0; i < opciones.length; i++) {
            RadioButton radioButton = (RadioButton) grupoOpciones.getChildAt(i);
            radioButton.setText(opciones[i]);
        }

        // Limpiar selección de radio buttons
        grupoOpciones.clearCheck();
    }

    private void verificarRespuesta() {
        int idRadioButtonSeleccionado = grupoOpciones.getCheckedRadioButtonId();
        if (idRadioButtonSeleccionado != -1) {
            RadioButton radioButtonSeleccionado = findViewById(idRadioButtonSeleccionado);
            String respuestaSeleccionada = radioButtonSeleccionado.getText().toString();

            if (preguntaActual.verificarRespuesta(respuestaSeleccionada)) {
                Toast.makeText(BanderaActivity.this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();
                // Mostrar siguiente pregunta si es correcto
                indicePregunta++;
                if (indicePregunta < preguntas.size()) {
                    mostrarSiguientePregunta();
                } else {
                    // Iniciar la actividad CompletoActivity
                    Intent intent = new Intent(BanderaActivity.this, CompletoActivity.class);
                    intent.putExtra("caller_activity", "BanderaActivity");
                    startActivity(intent);
                }
            } else {
                Toast.makeText(BanderaActivity.this, "Respuesta incorrecta. ¡Inténtalo de nuevo!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(BanderaActivity.this, "Por favor selecciona una respuesta", Toast.LENGTH_SHORT).show();
        }
    }

    private class Pregunta {
        private String textoPregunta;
        private String[] opciones;
        private String respuestaCorrecta;

        public Pregunta(String textoPregunta, String[] opciones, String respuestaCorrecta) {
            this.textoPregunta = textoPregunta;
            this.opciones = opciones;
            this.respuestaCorrecta = respuestaCorrecta;
        }

        public String getTextoPregunta() {
            return textoPregunta;
        }

        public String[] getOpciones() {
            return opciones;
        }

        public boolean verificarRespuesta(String respuesta) {
            return respuesta.equals(respuestaCorrecta);
        }
    }
}
