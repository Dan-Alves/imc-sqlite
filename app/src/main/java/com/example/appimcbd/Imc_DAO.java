package com.example.appimcbd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class Imc_DAO {
    // banco
    private SQLiteDatabase database;

    // colunas da tabela
    private String[] columns = {ImcSQLiteOpenHelper.COLUNA_ID,
            ImcSQLiteOpenHelper.COLUNA_NOME,
            ImcSQLiteOpenHelper.COLUNA_Altura,
            ImcSQLiteOpenHelper.COLUNA_Peso};
    private ImcSQLiteOpenHelper sqliteOpenHelper;

    // construtor
    public Imc_DAO(Context context) {
        sqliteOpenHelper = new ImcSQLiteOpenHelper(context);
    }

    // abrir conexão
    public void open() throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }

    // fechar conexão
    public void close() {
        sqliteOpenHelper.close();
    }

    // inclusão
    public void inserir(String nome, double altura, double peso) {

        ContentValues values = new ContentValues();
        values.put(ImcSQLiteOpenHelper.COLUNA_NOME, nome);
        values.put(ImcSQLiteOpenHelper.COLUNA_Altura, String.valueOf(altura));
        values.put(ImcSQLiteOpenHelper.COLUNA_Peso, String.valueOf(peso));

        long insertId = database.insert(ImcSQLiteOpenHelper.TABELA, null,
                values);
    }

    // alteração
    public void alterar(long id, String nome, double altura, double peso) {
        // prepara os dados para a atualização
        ContentValues values = new ContentValues();
        values.put(ImcSQLiteOpenHelper.COLUNA_NOME, nome);
        values.put(ImcSQLiteOpenHelper.COLUNA_Altura, String.valueOf(altura));
        values.put(ImcSQLiteOpenHelper.COLUNA_Peso, String.valueOf(peso));

        database.update(ImcSQLiteOpenHelper.TABELA, values, ImcSQLiteOpenHelper.COLUNA_ID + "=" + id, null);
    }

    // exclusão
    public void apagar(long id) {

        database.delete(ImcSQLiteOpenHelper.TABELA, ImcSQLiteOpenHelper.COLUNA_ID
                + " = " + id, null);
    }

    // método de busca
    public Imc buscar(long id) {
        Cursor cursor = database.query(ImcSQLiteOpenHelper.TABELA,
                columns, ImcSQLiteOpenHelper.COLUNA_ID + " = " + id, null,
                null, null, null);
        cursor.moveToFirst();

        Imc imc = new Imc();
        imc.setId(cursor.getLong(0));
        imc.setNome(cursor.getString(1));
        imc.setAltura(cursor.getDouble(2));
        imc.setPeso(cursor.getDouble(3));
        cursor.close();

        return imc;
    }

    // criação da lista
    public List<Imc> getAll() {

        List<Imc> imcs = new ArrayList<Imc>();
        Cursor cursor = database.query(ImcSQLiteOpenHelper.TABELA,
                columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Imc imc = new Imc();
            imc.setId(cursor.getLong(0));
            imc.setNome(cursor.getString(1));
            imc.setAltura(cursor.getDouble(2));
            imc.setPeso(cursor.getDouble(3));
            imcs.add(imc);
            cursor.moveToNext();
        }
        cursor.close();
        return imcs;
    }

}
