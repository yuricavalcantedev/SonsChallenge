package br.heavendevelopment.sonschallenge.Model;

/**
 * Created by yuri on 09/03/18.
 */

public class Devocional {

    private int id;
    private int diaDesafio;
    private String data;
    private String titulo;
    private String textoDevocional;

    public Devocional(int id, int diaDesafio, String data, String titulo, String textoDevocional) {
        this.id = id;
        this.titulo = titulo;
        this.diaDesafio = diaDesafio;
        this.data = data;
        this.textoDevocional = textoDevocional;
    }

    public Devocional(int diaDesafio, String data, String titulo, String textoDevocional) {
        this.titulo = titulo;
        this.diaDesafio = diaDesafio;
        this.data = data;
        this.textoDevocional = textoDevocional;
    }

    public Devocional(String titulo, String textoDevocional) {
        this.titulo = titulo;
        this.textoDevocional = textoDevocional;
    }

    public Devocional() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTextoDevocional() {
        return textoDevocional;
    }

    public void setTextoDevocional(String textoDevocional) {
        this.textoDevocional = textoDevocional;
    }

    public int getDiaDesafio() {
        return diaDesafio;
    }

    public void setDiaDesafio(int diaDesafio) {
        this.diaDesafio = diaDesafio;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
