package br.heavendevelopment.sonschallenge.Model;

/**
 * Created by yuri on 09/03/18.
 */

public class Leitura {


    //desafio: 0 - não existe, 1 - acordar de hora em hora, 2 - frase, texto, 3 - ligação, 7 - realizado
    private int id;
    private int mes;
    private int dia;
    private String data;
    private int leitura;
    private int oracao;
    private int desafio;
    private String referencia;
    private String mensagem;

    public Leitura() {
    }

    public Leitura(int id, int mes, int dia, String data, int leitura, int oracao, int desafio, String referencia, String mensagem) {

        this.id = id;
        this.data = data;
        this.mes = mes;
        this.dia = dia;
        this.leitura = leitura;
        this.oracao = oracao;
        this.desafio = desafio;
        this.referencia = referencia;
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getLeitura() {
        return leitura;
    }

    public void setLeitura(int leitura) {
        this.leitura = leitura;
    }

    public int getOracao() {
        return oracao;
    }

    public void setOracao(int oracao) {
        this.oracao = oracao;
    }

    public int getDesafio() {
        return desafio;
    }

    public void setDesafio(int desafio) {
        this.desafio = desafio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
