package com.practica.simbolospatrios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView Bandera, Himno, Escudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bandera = findViewById(R.id.ImgBandera);
        Himno = findViewById(R.id.ImgHimno);
        Escudo = findViewById(R.id.ImgEscudo);

        Bandera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBandera = new Intent(MainActivity.this, IntroActivity1.class);
                startActivity(iBandera);
            }
        });
        Himno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHimno = new Intent(MainActivity.this, IntroActivity3.class);
                startActivity(iHimno);
            }
        });
        Escudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEscudo = new Intent(MainActivity.this, IntroActivity2.class);
                startActivity(iEscudo);
            }
        });
    }
}
