package com.practica.simbolospatrios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class CompletoActivity extends AppCompatActivity {
    private ImageView imagenGancho;
    private Button botonReiniciar,botonSalir;
    private String callerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completo);

        imagenGancho = findViewById(R.id.imagen_gancho);
        botonReiniciar = findViewById(R.id.boton_reinicio);
        botonSalir = findViewById(R.id.boton_salir);
        callerActivity = getIntent().getStringExtra("caller_activity");

        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if ("BanderaActivity".equals(callerActivity)) {
                    intent = new Intent(CompletoActivity.this, BanderaActivity.class);
                } else {
                    intent = new Intent(CompletoActivity.this, EscudoActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Vuelve a la actividad anterior
            }

        });
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletoActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Cierra CompletoActivity
            }
        });
    }
}
