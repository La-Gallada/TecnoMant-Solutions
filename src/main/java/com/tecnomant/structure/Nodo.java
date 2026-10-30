package com.tecnomant.structure;

import com.tecnomant.model.OrdenTrabajo;
import java.util.Objects;

/** Nodo real de la lista: sus enlaces nunca se almacenan en una colección auxiliar. */
public final class Nodo {
    private final OrdenTrabajo orden;
    private Nodo anterior;
    private Nodo siguiente;
    Nodo(OrdenTrabajo orden) { this.orden = Objects.requireNonNull(orden, "La orden es obligatoria."); }
    public OrdenTrabajo getOrden() { return orden; }
    public Nodo getAnterior() { return anterior; }
    public Nodo getSiguiente() { return siguiente; }
    void setAnterior(Nodo anterior) { this.anterior = anterior; }
    void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}
