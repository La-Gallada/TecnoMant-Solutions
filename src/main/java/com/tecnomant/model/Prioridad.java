package com.tecnomant.model;

/** Prioridad permitida por el enunciado académico. */
public enum Prioridad {
    BAJA("Baja"), MEDIA("Media"), ALTA("Alta"), CRITICA("Crítica");

    private final String etiqueta;
    Prioridad(String etiqueta) { this.etiqueta = etiqueta; }
    @Override public String toString() { return etiqueta; }
}
