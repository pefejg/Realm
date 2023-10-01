package com.example.realm.DAO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.UUID;

public class ModeloPersona extends RealmObject{

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    String nombre;
    int tipoPago;
    boolean seleccionado;

    public ModeloPersona(){}

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public ModeloPersona(String nombre, int tipoPago, boolean selecionado) {
        this.nombre = nombre;
        this.tipoPago = tipoPago;
        this.seleccionado = selecionado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }
}