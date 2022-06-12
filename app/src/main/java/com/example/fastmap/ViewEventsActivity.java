package com.example.fastmap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewEventsActivity extends AppCompatActivity implements View.OnLongClickListener{

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        listView=(ListView)findViewById(R.id.ListaClientes);
        listView.setOnLongClickListener((View.OnLongClickListener) this);

        Bundle bundle = getIntent().getExtras();
        int nombres;
        nombres=0;
        nombres=bundle.getInt("nombres");

        BDSQLite bd = new BDSQLite((getApplicationContext()),"Agenda", null,1);
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql= "select * from clientes where nombre";
        Cursor c;
        String nombre, telefono, direccion, observacion;
        try {
            c=db.rawQuery(sql, null);
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            if(c.moveToFirst()) {
                do {
                    nombre = c.getString(0);
                    telefono = c.getString(1);
                    direccion = c.getString(2);
                    observacion = c.getString(3);
                } while (c.moveToNext());
                listView.setAdapter(arrayAdapter);
            } else {
                this.finish();
            }
        }catch (Exception ex) {
            Toast.makeText(getApplication(), "Error:"+ex.getMessage(),Toast.LENGTH_SHORT).show();
            this.finish();
    }
        listView.setOnLongClickListener(this);
}
    @Override
    public boolean onLongClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence []items = new  CharSequence[2];
        items[0]="Eliminar Cliente";
        items[1]="Cancelar";
        builder.setTitle("Eliminar evento")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0) {
                            //eliminar el cliente
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }
}