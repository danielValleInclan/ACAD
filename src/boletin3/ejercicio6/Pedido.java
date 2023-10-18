package boletin3.ejercicio6;

import java.io.Serial;
import java.io.Serializable;

public class Pedido implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
    private String desc;
    private int numUnits;
    private double price;

    public Pedido(String desc, int numUnits, double price) {
        this.desc = desc;
        this.numUnits = numUnits;
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
