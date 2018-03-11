package br.heavendevelopment.sonschallenge.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by yuri on 09/03/18.
 */

@Table(name = "Leitura")
public class Leitura extends Model{

    @Column(name = "data")
    private String data;

    @Column(name = "dia")
    private int dia;

    @Column(name = "semana")
    private int semana;

    @Column(name = "leitura")
    private boolean leitura;

    @Column(name = "oracao")
    private boolean oracao;

    @Column(name = "desafio")
    private boolean desafio;

    @Column(name = "referencia")
    private String referencia;

    public Leitura() {
    }

    public Leitura(String data, int dia, boolean leitura, boolean oracao, boolean desafio, String referencia) {

        this.data = data;
        this.dia = dia;
        this.leitura = leitura;
        this.oracao = oracao;
        this.desafio = desafio;
        this.referencia = referencia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public boolean isLeitura() {
        return leitura;
    }

    public void setLeitura(boolean leitura) {
        this.leitura = leitura;
    }

    public boolean isOracao() {
        return oracao;
    }

    public void setOracao(boolean oracao) {
        this.oracao = oracao;
    }

    public boolean isDesafio() {
        return desafio;
    }

    public void setDesafio(boolean desafio) {
        this.desafio = desafio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
