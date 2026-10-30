package com.tecnomant.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Entidad de dominio. El código es inmutable; los demás datos se validan y actualizan en sitio. */
public final class OrdenTrabajo {
    private final String codigo;
    private final String cliente;
    private final LocalDate fechaSolicitud;
    private String descripcion;
    private String tecnicoAsignado;
    private Prioridad prioridad;
    private EstadoOrden estado;
    private BigDecimal costoEstimado;

    public OrdenTrabajo(String codigo, String cliente, String descripcion, LocalDate fechaSolicitud,
                         String tecnicoAsignado, Prioridad prioridad, EstadoOrden estado, BigDecimal costoEstimado) {
        this.codigo = textoObligatorio(codigo, "El código es obligatorio.");
        this.cliente = textoObligatorio(cliente, "El cliente es obligatorio.");
        this.fechaSolicitud = Objects.requireNonNull(fechaSolicitud, "La fecha de solicitud es obligatoria.");
        actualizar(descripcion, tecnicoAsignado, prioridad, estado, costoEstimado);
    }

    public void actualizar(String descripcion, String tecnicoAsignado, Prioridad prioridad,
                           EstadoOrden estado, BigDecimal costoEstimado) {
        this.descripcion = textoObligatorio(descripcion, "La descripción es obligatoria.");
        this.tecnicoAsignado = textoObligatorio(tecnicoAsignado, "El técnico asignado es obligatorio.");
        this.prioridad = Objects.requireNonNull(prioridad, "La prioridad es obligatoria.");
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio.");
        this.costoEstimado = Objects.requireNonNull(costoEstimado, "El costo estimado es obligatorio.");
        if (costoEstimado.signum() < 0) throw new IllegalArgumentException("El costo estimado no puede ser negativo.");
    }

    private static String textoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) throw new IllegalArgumentException(mensaje);
        return valor.trim();
    }
    public String getCodigo() { return codigo; }
    public String getCliente() { return cliente; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public String getTecnicoAsignado() { return tecnicoAsignado; }
    public Prioridad getPrioridad() { return prioridad; }
    public EstadoOrden getEstado() { return estado; }
    public BigDecimal getCostoEstimado() { return costoEstimado; }
}
