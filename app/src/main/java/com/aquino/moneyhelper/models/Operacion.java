package com.aquino.moneyhelper.models;


public class Operacion {

    private double monto;
    private String tipo;
    private String tipo_cuenta;

    public Operacion(){}

    public Operacion(double monto, String tipo, String tipo_cuenta) {
        this.monto = monto;
        this.tipo = tipo;
        this.tipo_cuenta = tipo_cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }
}
