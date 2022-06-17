package com.example.fastmap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Usuario,Contraseña;
    private Button Entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario=(EditText)findViewById(R.id.Usuario);
        Contraseña=(EditText)findViewById(R.id.Contraseña);
        Entrar=(Button)findViewById(R.id.Entrar);

        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Usuario.getText().toString().equals("Admin")&Contraseña.getText().toString().equals("123")) {
                    Toast.makeText(MainActivity.this, "Usuario y Contraseña correcta", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Usuario y Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    Usuario.setText("");
                    Contraseña.setText("");
                    Usuario.requestFocus();
                }
            }
        });
        CalendarView calendarView = (CalendarView) findViewById(R.id.CalendarView);
        calendarView.setOnDateChangeListener((CalendarView.OnDateChangeListener) this);
    }

    public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence []items = new CharSequence[3];
        items[0]="Agregar Cliente";
        items[1]="Ver Clientes";
        items[2]="Cancelar";

        final int nombre;
        nombre = i;


        builder.setTitle("Seleciona un cliente")
                .setItems(items, (dialogInterface, i3) -> {
                    if (i3 ==0) {
                        //actividad agregar CLiente
                        Intent intent = new Intent(getApplication(), AddActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("nombre",nombre);


                        intent.putExtras(bundle);
                        startActivity(intent);

                    }else if(i3 ==1){
                        //Ver clientes
                        Intent intent = new Intent(getApplication(), ViewEventsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("nombre",nombre);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}