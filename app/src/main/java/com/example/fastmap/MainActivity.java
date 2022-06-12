package com.example.fastmap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.CalendarView);
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
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0) {
                            //actividad agregar CLiente
                            Intent intent = new Intent(getApplication(), AddActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("nombre",nombre);


                            intent.putExtras(bundle);
                            startActivity(intent);

                        }else if(i==1){
                            //Ver clientes
                            Intent intent = new Intent(getApplication(), ViewEventsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("nombre",nombre);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }else{
                            return;
                        }

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}