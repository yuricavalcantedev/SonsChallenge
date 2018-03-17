package br.heavendevelopment.sonschallenge.Model;

/**
 * Created by yuri on 23/02/18.
 */


public class Desafio {

    private int id;
    private String data_inicio;
    private String data_termino;
    private int image;
    private String nome;
    private String descricao;
    private String habilitado;

    public Desafio() {
    }

    public Desafio(int id, String data_inicio, String data_termino, int image, String nome, String descricao) {
        this.id = id;
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.image = image;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getData_inicio() {
        return data_inicio;
    }
    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_termino() {
        return data_termino;
    }
    public void setData_termino(String data_termino) {
        this.data_termino = data_termino;
    }

    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHabilitado() {
        return habilitado;
    }
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
}
