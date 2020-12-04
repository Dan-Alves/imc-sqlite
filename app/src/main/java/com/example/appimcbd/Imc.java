package com.example.appimcbd;

public class Imc {
    // definição dos atributos
    // o atributo id não existia na versão anterior
    private long id;
    private String nome;
    private double altura, peso;

    // Métodos de acesso (Setters & Getters)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        if (altura >= 0) {
            this.altura = altura;
        }
    }

    public double getPeso() {

        return peso;
    }

    public void setPeso(double peso) {
        if (peso >= 0) {
            this.peso = peso;
        }
    }

    // método construtor com definição dos valores padrão
    public Imc() {
        nome = "Nome";
        altura = 0.0;
        peso = 0.0;
    }

    // método de formatação dos dados para exibição na lista
    public String textoLista() {
        String item;
        item = getNome();
        item += "\nAltura: " + String.format("%3.1f", getAltura());
        item += "\tPeso: " + String.format("%3.1f", getPeso());
        return item;
    }
}

