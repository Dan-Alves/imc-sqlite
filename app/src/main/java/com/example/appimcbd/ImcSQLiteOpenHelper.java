package com.example.appimcbd;

import android.content.Context ;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImcSQLiteOpenHelper extends SQLiteOpenHelper {

    // definição dos atributos
    public static final String TABELA = " Imc ";
    public static final String COLUNA_ID = " id ";
    public static final String COLUNA_NOME = " nome ";
    public static final String COLUNA_Altura = " altura ";
    public static final String COLUNA_Peso = " peso ";

    // nomeia o banco de dados
    private static final String DATABASE_NAME = "imcs.db";

    // determina a versão do banco
    private static final int DATABASE_VERSION = 1;

    // prepara a criação da tabela se não existir
    private static final String CRIAR_BANCO = " create table "
            + TABELA + "("
            + COLUNA_ID + " integer primary key autoincrement , "
            + COLUNA_NOME + " text not null , "
            + COLUNA_Altura + " double not null , "
            + COLUNA_Peso + " double not null ) ;";

    // construtor
    public ImcSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // criação do banco
    @Override
    public void onCreate ( SQLiteDatabase database ) {
        database . execSQL ( CRIAR_BANCO );
    }

    // atualização do banco
    @Override
    public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion ) {
        db. execSQL (" DROP TABLE IF EXISTS " + TABELA );
        onCreate (db);
    }
}