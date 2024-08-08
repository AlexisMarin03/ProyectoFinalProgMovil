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

public class EscudoActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_escudo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Escudo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textoPregunta = findViewById(R.id.questionTextView);
        grupoOpciones = findViewById(R.id.optionsRadioGroup);
        botonEnviar = findViewById(R.id.submitButton);
        botonReiniciar = findViewById(R.id.restartButton);
        imagenGancho = findViewById(R.id.ganchoImageView);

        inicializarPreguntas();
        mostrarSiguientePregunta();

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta();
            }
        });

        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarQuiz();
            }
        });
    }

    private void inicializarPreguntas() {
        preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("¿Qué representa el águila en el escudo de Panamá?", new String[]{"La soberanía", "La valentía", "La paz", "La lealtad"}, "La soberanía"));
        preguntas.add(new Pregunta("¿Cuántos espacios tiene el escudo de Panamá?", new String[]{"Cuatro", "Seis", "Tres", "Cinco"}, "Cinco"));
        preguntas.add(new Pregunta("¿Qué representa la cornucopia en el escudo de Panamá?", new String[]{"La riqueza", "La justicia", "La paz", "La agricultura"}, "La riqueza"));
        Collections.shuffle(preguntas);
        indicePregunta = 0;
    }

    private void mostrarSiguientePregunta() {
        preguntaActual = preguntas.get(indicePregunta);
        textoPregunta.setText(preguntaActual.getTextoPregunta());
        String[] opciones = preguntaActual.getOpciones();
        for (int i = 0; i < opciones.length; i++) {
            RadioButton radioButton = (RadioButton) grupoOpciones.getChildAt(i);
            radioButton.setText(opciones[i]);
        }
        grupoOpciones.clearCheck();
    }

    private void verificarRespuesta() {
        int idRadioButtonSeleccionado = grupoOpciones.getCheckedRadioButtonId();
        if (idRadioButtonSeleccionado != -1) {
            RadioButton radioButtonSeleccionado = findViewById(idRadioButtonSeleccionado);
            String respuestaSeleccionada = radioButtonSeleccionado.getText().toString();

            if (preguntaActual.verificarRespuesta(respuestaSeleccionada)) {
                Toast.makeText(EscudoActivity.this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();
                indicePregunta++;
                if (indicePregunta < preguntas.size()) {
                    mostrarSiguientePregunta();
                } else {
                    Intent intent = new Intent(EscudoActivity.this, CompletoActivity.class);
                    intent.putExtra("caller_activity", "EscudoActivity");
                    startActivity(intent);
                }
            } else {
                Toast.makeText(EscudoActivity.this, "Respuesta incorrecta. ¡Inténtalo de nuevo!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(EscudoActivity.this, "Por favor selecciona una respuesta", Toast.LENGTH_SHORT).show();
        }
    }

    private void reiniciarQuiz() {
        indicePregunta = 0;
        Collections.shuffle(preguntas);
        imagenGancho.setVisibility(View.GONE);
        botonReiniciar.setVisibility(View.GONE);
        botonEnviar.setVisibility(View.VISIBLE);
        mostrarSiguientePregunta();
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
