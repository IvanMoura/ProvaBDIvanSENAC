package br.senac.pi.smartphones;

/**
 * Created by Aluno on 20/11/2015.
 */
public class Celular {
    private int id;
    private String modelo;
    private String fabricante;
    private Double preco;

    public Celular() {
    }

    public Celular(int id, String modelo, String fabricante, Double preco) {
        this.id = id;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return modelo+" - "+fabricante+" - "+preco;
    }
}
