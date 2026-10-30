package com.tecnomant.structure;

/** Resultado legible de una inspección real de enlaces y contador. */
public record ResultadoIntegridad(boolean valida, String detalle, int nodosAdelante, int nodosAtras) { }
