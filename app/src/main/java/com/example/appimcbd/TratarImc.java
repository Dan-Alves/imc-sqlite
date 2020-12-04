package com.example.appimcbd;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TratarImc extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    Button bt1, bt2;
    private int acao;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratar_imc);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        acao = getIntent().getExtras().getInt("acao");
        id = getIntent().getExtras().getLong("id");
        if (acao == -1) {
            // inclusão
            setTitle("Inserir Pessoa");
            bt1.setText("Incluir");
            bt2.setEnabled(false);
            ed1.setText("Nome");
            ed2.setText(String.format("%.1f", 0.0));
            ed3.setText(String.format("%.1f", 0.0));
            ed4.setText(String.format("%.1f", 0.0));
        } else {
            // alteração ou exclusão
            setTitle("Alterar ou Excluir");
            Imc aux = new Imc();
            Imc_DAO dao = new Imc_DAO(this);
            dao.open();
            aux = dao.buscar(id);
            ed1.setText(aux.getNome());
            ed2.setText(String.format("%.1f", aux.getAltura()));
            ed3.setText(String.format("%.1f", aux.getPeso()));
            dao.close();
        }
    }

    public void alterarInserir(View v) {
        String nome;
        double altura, peso;
        nome = ed1.getText().toString();
        altura = Double.parseDouble(ed2.getText().toString());
        peso = Double.parseDouble(ed3.getText().toString());
        Imc_DAO dao = new Imc_DAO(this);
        dao.open();
        if (acao == -1) { // inserção
            dao.inserir(nome, altura, peso);
        }
        else{ // alteração
            dao.alterar(id, nome, altura, peso);
        }
        dao.close();
        finish();
    }

    public void excluir(View v) {
        // exclusão
        if (acao == 0) {
            Imc_DAO dao = new Imc_DAO(this);
            dao.open();
            dao.apagar(id);
            dao.close();
        }
        finish();
    }
    public void voltar(View v) {
        finish();
    }

}
