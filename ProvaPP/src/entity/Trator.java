package entity;
import java.math.BigDecimal;

public class Trator {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private float valor;

    public Trator(int id, String marca, String modelo, int ano, float valor) {
        super();
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;

    }

    public Trator() {
        super();
    }

    public Trator(String marca, String modelo, int ano, float valor) {
        super();
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
}
    // METODOS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Trator [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + ", valor=" + valor
                + "]";
    }

}
