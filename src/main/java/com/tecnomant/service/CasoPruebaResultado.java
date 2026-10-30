package com.tecnomant.service;

/** Fila observable del laboratorio; cada instancia corresponde a una ejecución real. */
public final class CasoPruebaResultado {
    private final String id, descripcion, esperado;
    private EstadoCasoPrueba estado = EstadoCasoPrueba.PENDIENTE;
    private String obtenido = "Aún no ejecutado";
    private String detalle = "Pendiente de ejecución.";
    public CasoPruebaResultado(String id, String descripcion, String esperado) { this.id=id; this.descripcion=descripcion; this.esperado=esperado; }
    public String getId(){return id;} public String getDescripcion(){return descripcion;} public String getEsperado(){return esperado;}
    public EstadoCasoPrueba getEstado(){return estado;} public String getObtenido(){return obtenido;} public String getDetalle(){return detalle;}
    void aprobar(String obtenido, String detalle){this.estado=EstadoCasoPrueba.APROBADA;this.obtenido=obtenido;this.detalle=detalle;}
    void fallar(String detalle){this.estado=EstadoCasoPrueba.FALLIDA;this.obtenido="Error durante la ejecución";this.detalle=detalle;}
    void reiniciar(){this.estado=EstadoCasoPrueba.PENDIENTE;this.obtenido="Aún no ejecutado";this.detalle="Pendiente de ejecución.";}
}
