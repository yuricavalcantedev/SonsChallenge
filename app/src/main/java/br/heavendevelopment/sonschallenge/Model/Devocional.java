package br.heavendevelopment.sonschallenge.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by yuri on 09/03/18.
 */

@Table(name = "Devocional")
public class Devocional extends Model{

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "dia")
    private int dia;

    @Column(name = "data")
    private String data;

    @Column(name = "textoDevocional")
    private String textoDevocional;

    public Devocional(String titulo, String textoDevocional) {
        this.titulo = titulo;
        this.textoDevocional = textoDevocional;
    }

    public Devocional() {
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

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
