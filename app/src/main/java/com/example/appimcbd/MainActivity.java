package com.example.appimcbd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    Intent intent;
    public static final int ACTIVITY_REQUEST_IMC = 1;
    private Imc_DAO dao;

    private String[] imcs;
    private long[] idImcs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        setTitle("Banco de Dados com SQLite!");
        dao = new Imc_DAO(this);
        dao.open();

        lista.setOnItemClickListener(this); // Clique no item
    }

    @Override
    protected void onResume() {
        dao.open();
        super.onResume();
        List<Imc> listaImcs = dao.getAll();
        imcs = new String[listaImcs.size()];
        idImcs = new long[listaImcs.size()];
        int i = 0;
        Iterator<Imc> iterator = listaImcs.iterator();
        while (iterator.hasNext()) {
            Imc aux = new Imc();
            aux = (Imc) iterator.next();
            imcs[i] = aux.textoLista();
            idImcs[i] = aux.getId();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, imcs);
        lista.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long ident) {
        long id = idImcs[position];
        intent = new Intent(getApplicationContext(), TratarImc.class);
        intent.putExtra("acao", 0);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void incluirImc(View v) {
        intent = new Intent(getApplicationContext(), TratarImc.class);
        intent.putExtra("acao", -1);
        intent.putExtra("id", 0L);
        startActivity(intent);
    }

    public void sair(View v) {
        finish();

    }
}