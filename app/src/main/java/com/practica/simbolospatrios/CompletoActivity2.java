package com.practica.simbolospatrios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CompletoActivity2 extends AppCompatActivity {

    private Button botonReinicio, botonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completo2);

        botonReinicio = findViewById(R.id.boton_reinicio);
        botonSalir = findViewById(R.id.boton_salir);

        botonReinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarQuiz();
            }
        });
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletoActivity2.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Cierra CompletoActivity
            }
        });
    }

    private void reiniciarQuiz() {
        Intent intent = new Intent(CompletoActivity2.this, HimnoActivity.class);
        startActivity(intent);
        finish();
    }
}
