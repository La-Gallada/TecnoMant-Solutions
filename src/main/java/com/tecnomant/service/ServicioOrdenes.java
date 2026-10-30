package com.tecnomant.service;

import com.tecnomant.model.*;
import com.tecnomant.structure.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Capa de aplicación: coordina las operaciones sin exponer enlaces mutables a las vistas. */
public final class ServicioOrdenes {
    private final ListaDoble lista = new ListaDoble();
    public ListaDoble getLista() { return lista; }
    public void registrar(OrdenTrabajo orden, TipoInsercion tipo, int posicion) {
        switch (tipo) { case INICIO -> lista.insertarInicio(orden); case FINAL -> lista.insertarFinal(orden); case POSICION -> lista.insertarEnPosicion(orden, posicion); }
    }
    public void modificar(String codigo, String descripcion, String tecnico, Prioridad prioridad, EstadoOrden estado, BigDecimal costo) { lista.modificar(codigo, descripcion, tecnico, prioridad, estado, costo); }
    public EstadisticasOrdenes estadisticas() {
        int pendientes=0, proceso=0, finalizadas=0;
        for (OrdenTrabajo orden : lista.recorrerAdelante()) switch (orden.getEstado()) { case PENDIENTE -> pendientes++; case EN_PROCESO -> proceso++; case FINALIZADA -> finalizadas++; }
        return new EstadisticasOrdenes(pendientes + proceso, pendientes, proceso, finalizadas);
    }
    /** Carga opcional exactamente con los cinco ejemplos oficiales; no altera una lista no vacía. */
    public void cargarDemostracion() {
        if (!lista.estaVacia()) throw new IllegalStateException("Para cargar ejemplos, la lista debe estar vacía.");
        registrar(new OrdenTrabajo("OT-001", "Hospital Central", "Aire acondicionado", LocalDate.of(2026, 10, 20), "Laura Jiménez", Prioridad.ALTA, EstadoOrden.PENDIENTE, new BigDecimal("450000")), TipoInsercion.FINAL, 0);
        registrar(new OrdenTrabajo("OT-002", "Centro Comercial Norte", "Revisión eléctrica", LocalDate.of(2026, 10, 21), "Carlos López", Prioridad.MEDIA, EstadoOrden.EN_PROCESO, new BigDecimal("320000")), TipoInsercion.FINAL, 0);
        registrar(new OrdenTrabajo("OT-003", "Industrias del Valle", "Maquinaria", LocalDate.of(2026, 10, 22), "Ana Torres", Prioridad.ALTA, EstadoOrden.PENDIENTE, new BigDecimal("780000")), TipoInsercion.FINAL, 0);
        registrar(new OrdenTrabajo("OT-004", "Edificio Corporativo", "Inspección", LocalDate.of(2026, 10, 23), "Marta Gómez", Prioridad.BAJA, EstadoOrden.FINALIZADA, new BigDecimal("185000")), TipoInsercion.FINAL, 0);
        registrar(new OrdenTrabajo("OT-005", "Supermercados del Este", "Cámara frigorífica", LocalDate.of(2026, 10, 24), "Diego Pérez", Prioridad.CRITICA, EstadoOrden.EN_PROCESO, new BigDecimal("950000")), TipoInsercion.FINAL, 0);
    }
}
