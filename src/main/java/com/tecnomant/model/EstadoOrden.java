package com.tecnomant.model;

/** Estado permitido por el enunciado académico. */
public enum EstadoOrden {
    PENDIENTE("Pendiente"), EN_PROCESO("En proceso"), FINALIZADA("Finalizada");

    private final String etiqueta;
    EstadoOrden(String etiqueta) { this.etiqueta = etiqueta; }
    @Override public String toString() { return etiqueta; }
}
