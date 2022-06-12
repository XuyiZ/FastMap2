package com.example.fastmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nombreCliente, telefono, direccion, observacion;
    private EditText descripcion;

    private Button guardar, cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nombreCliente=(EditText) findViewById(R.id.Nombre);
        telefono=(EditText) findViewById(R.id.Telefono);
        direccion=(EditText) findViewById(R.id.Direccion);
        observacion=(EditText) findViewById(R.id.Observacion);



        guardar=(Button) findViewById(R.id.Guardar);
        cancelar=(Button) findViewById(R.id.Cancelar);
        guardar.setOnClickListener((View.OnClickListener) this);
        cancelar.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==guardar.getId()) {
            BDSQLite bd = new BDSQLite(getApplication(), "Agenda", null, 1);
            SQLiteDatabase db = bd.getWritableDatabase();

            String sql = "insert into clientes" +
                    "(nombre, telefono, direccion, observacion) " +
                    "values ('" +
                    nombreCliente.getText()+
                    "'.'"+telefono.getText()+
                    "'.'"+direccion.getText()+
                    "'.'"+observacion.getText() + "')";
            try {
                db.execSQL(sql);

                nombreCliente.setText("");
                telefono.setText("");
                direccion.setText("");
                observacion.setText("");
            }catch (Exception e) {
                Toast.makeText(getApplication(), "Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        } else {
            this.finish();
            return;
        }

    }
}