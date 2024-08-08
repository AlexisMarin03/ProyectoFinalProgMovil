package com.practica.simbolospatrios;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HimnoActivity extends AppCompatActivity {

    private TextView opcion1, opcion2, opcion3, opcion4;
    private ImageView himno1, himno2, himno3, himno4;
    private ImageView imagenGancho;
    private Button botonReinicio;

    private int respuestasCorrectas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_himno);

        opcion1 = findViewById(R.id.opcion1);
        opcion2 = findViewById(R.id.opcion2);
        opcion3 = findViewById(R.id.opcion3);
        opcion4 = findViewById(R.id.opcion4);

        himno1 = findViewById(R.id.himno1);
        himno2 = findViewById(R.id.himno2);
        himno3 = findViewById(R.id.himno3);
        himno4 = findViewById(R.id.himno4);

        imagenGancho = findViewById(R.id.imagen_gancho);
        botonReinicio = findViewById(R.id.boton_reinicio);

        setDragAndDropListeners(opcion1);
        setDragAndDropListeners(opcion2);
        setDragAndDropListeners(opcion3);
        setDragAndDropListeners(opcion4);

        setDragAndDropTargets(himno1);
        setDragAndDropTargets(himno2);
        setDragAndDropTargets(himno3);
        setDragAndDropTargets(himno4);

        botonReinicio.setOnClickListener(v -> reiniciarJuego());
    }

    private void setDragAndDropListeners(TextView textView) {
        textView.setOnLongClickListener(v -> {
            CharSequence text = textView.getText();
            ClipData.Item item = new ClipData.Item(text);
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

            ClipData dragData = new ClipData(textView.getTag().toString(), mimeTypes, item);
            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(textView);
            textView.startDragAndDrop(dragData, myShadow, textView, 0);
            return true;
        });
    }

    private void setDragAndDropTargets(ImageView imageView) {
        imageView.setOnDragListener((v, event) -> {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
                case DragEvent.ACTION_DROP:
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    String dragData = item.getText().toString();

                    if ((v.getId() == R.id.himno1 && dragData.equals("William Buchanan")) ||
                            (v.getId() == R.id.himno2 && dragData.equals("J. Ledezma")) ||
                            (v.getId() == R.id.himno3 && dragData.equals("Santos Jorge")) ||
                            (v.getId() == R.id.himno4 && dragData.equals("Jeronimo D. Ossa"))) {
                        Toast.makeText(HimnoActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                        TextView draggedTextView = (TextView) event.getLocalState();
                        draggedTextView.setVisibility(View.INVISIBLE);
                        respuestasCorrectas++;
                        if (respuestasCorrectas == 4) {
                            Intent intent = new Intent(HimnoActivity.this, CompletoActivity2.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(HimnoActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    return true;
                default:
                    break;
            }
            return false;
        });
    }

    public void reiniciarJuego() {
        respuestasCorrectas = 0;
        opcion1.setVisibility(View.VISIBLE);
        opcion2.setVisibility(View.VISIBLE);
        opcion3.setVisibility(View.VISIBLE);
        opcion4.setVisibility(View.VISIBLE);
        imagenGancho.setVisibility(View.GONE);
    }
}
