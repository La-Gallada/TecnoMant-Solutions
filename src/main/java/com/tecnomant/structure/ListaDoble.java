package com.tecnomant.structure;

import com.tecnomant.model.OrdenTrabajo;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Lista doblemente enlazada manual. Las posiciones públicas son 1-basadas:
 * insertar en 1 agrega al inicio y la última posición permitida es tamaño + 1.
 */
public final class ListaDoble {
    private Nodo primero;
    private Nodo ultimo;
    private int cantidad;

    public void insertarInicio(OrdenTrabajo orden) {
        asegurarCodigoDisponible(orden);
        Nodo nuevo = new Nodo(orden);
        if (estaVacia()) primero = ultimo = nuevo;
        else { nuevo.setSiguiente(primero); primero.setAnterior(nuevo); primero = nuevo; }
        cantidad++;
    }
    public void insertarFinal(OrdenTrabajo orden) {
        asegurarCodigoDisponible(orden);
        Nodo nuevo = new Nodo(orden);
        if (estaVacia()) primero = ultimo = nuevo;
        else { nuevo.setAnterior(ultimo); ultimo.setSiguiente(nuevo); ultimo = nuevo; }
        cantidad++;
    }
    public void insertarEnPosicion(OrdenTrabajo orden, int posicion) {
        if (posicion < 1 || posicion > cantidad + 1)
            throw new IllegalArgumentException("Posición inválida. Use un valor entre 1 y " + (cantidad + 1) + ".");
        if (posicion == 1) { insertarInicio(orden); return; }
        if (posicion == cantidad + 1) { insertarFinal(orden); return; }
        asegurarCodigoDisponible(orden);
        Nodo actual = nodoEnPosicion(posicion);
        Nodo nuevo = new Nodo(orden);
        Nodo previo = actual.getAnterior();
        nuevo.setAnterior(previo); nuevo.setSiguiente(actual);
        previo.setSiguiente(nuevo); actual.setAnterior(nuevo);
        cantidad++;
    }
    public OrdenTrabajo buscarPorCodigo(String codigo) {
        Nodo nodo = buscarNodoPorCodigo(codigo);
        return nodo == null ? null : nodo.getOrden();
    }
    public Nodo buscarNodoPorCodigo(String codigo) {
        if (codigo == null) return null;
        for (Nodo actual = primero; actual != null; actual = actual.getSiguiente())
            if (actual.getOrden().getCodigo().equalsIgnoreCase(codigo.trim())) return actual;
        return null;
    }
    public Iterable<Nodo> buscarPorCliente(String fragmento) {
        String consulta = fragmento == null ? "" : fragmento.trim().toLowerCase();
        return () -> new Iterator<>() {
            private Nodo proximo = encontrar(primero);
            private Nodo encontrar(Nodo desde) {
                for (Nodo cursor = desde; cursor != null; cursor = cursor.getSiguiente())
                    if (cursor.getOrden().getCliente().toLowerCase().contains(consulta)) return cursor;
                return null;
            }
            public boolean hasNext() { return proximo != null; }
            public Nodo next() { if (proximo == null) throw new NoSuchElementException(); Nodo salida = proximo; proximo = encontrar(proximo.getSiguiente()); return salida; }
        };
    }
    public OrdenTrabajo eliminarInicio() {
        if (estaVacia()) throw new IllegalStateException("No hay órdenes para eliminar.");
        Nodo eliminado = primero;
        if (cantidad == 1) primero = ultimo = null;
        else { primero = eliminado.getSiguiente(); primero.setAnterior(null); eliminado.setSiguiente(null); }
        cantidad--; return eliminado.getOrden();
    }
    public OrdenTrabajo eliminarFinal() {
        if (estaVacia()) throw new IllegalStateException("No hay órdenes para eliminar.");
        Nodo eliminado = ultimo;
        if (cantidad == 1) primero = ultimo = null;
        else { ultimo = eliminado.getAnterior(); ultimo.setSiguiente(null); eliminado.setAnterior(null); }
        cantidad--; return eliminado.getOrden();
    }
    public OrdenTrabajo eliminarPorCodigo(String codigo) {
        Nodo eliminado = buscarNodoPorCodigo(codigo);
        if (eliminado == null) throw new IllegalArgumentException("No existe una orden con el código indicado.");
        if (eliminado == primero) return eliminarInicio();
        if (eliminado == ultimo) return eliminarFinal();
        Nodo previo = eliminado.getAnterior(), siguiente = eliminado.getSiguiente();
        previo.setSiguiente(siguiente); siguiente.setAnterior(previo);
        eliminado.setAnterior(null); eliminado.setSiguiente(null); cantidad--;
        return eliminado.getOrden();
    }
    public Iterable<OrdenTrabajo> recorrerAdelante() { return () -> new IteradorOrden(primero, true); }
    public Iterable<OrdenTrabajo> recorrerAtras() { return () -> new IteradorOrden(ultimo, false); }
    public Iterable<Nodo> nodosAdelante() { return () -> new IteradorNodo(primero, true); }
    public Iterable<Nodo> nodosAtras() { return () -> new IteradorNodo(ultimo, false); }
    public Nodo nodoEnPosicion(int posicion) {
        if (posicion < 1 || posicion > cantidad) throw new IllegalArgumentException("Posición inexistente.");
        if (posicion <= cantidad / 2 + 1) { Nodo cursor = primero; for (int i=1;i<posicion;i++) cursor=cursor.getSiguiente(); return cursor; }
        Nodo cursor = ultimo; for (int i=cantidad;i>posicion;i--) cursor=cursor.getAnterior(); return cursor;
    }
    public int posicionDe(Nodo nodo) { int p=1; for(Nodo c=primero;c!=null;c=c.getSiguiente(),p++) if(c==nodo) return p; return -1; }
    public void modificar(String codigo, String descripcion, String tecnico, com.tecnomant.model.Prioridad prioridad, com.tecnomant.model.EstadoOrden estado, java.math.BigDecimal costo) {
        OrdenTrabajo orden = buscarPorCodigo(codigo);
        if (orden == null) throw new IllegalArgumentException("No existe una orden con el código indicado.");
        orden.actualizar(descripcion, tecnico, prioridad, estado, costo);
    }
    public ResultadoIntegridad verificarIntegridad() {
        if (cantidad == 0) return primero == null && ultimo == null
                ? new ResultadoIntegridad(true, "Lista vacía consistente: primero y último son null.", 0, 0)
                : new ResultadoIntegridad(false, "Lista vacía inconsistente: existen referencias de extremo.", 0, 0);
        if (primero == null || ultimo == null) return new ResultadoIntegridad(false, "La lista tiene elementos pero falta primero o último.", 0, 0);
        if (primero.getAnterior() != null) return new ResultadoIntegridad(false, "primero.anterior debe ser null.", 0, 0);
        if (ultimo.getSiguiente() != null) return new ResultadoIntegridad(false, "ultimo.siguiente debe ser null.", 0, 0);
        int adelante=0, atras=0; Nodo previo=null;
        for(Nodo c=primero;c!=null;c=c.getSiguiente()) { if(++adelante>cantidad) return new ResultadoIntegridad(false,"Ciclo detectado hacia adelante.",adelante,atras); if(c.getAnterior()!=previo) return new ResultadoIntegridad(false,"Enlace anterior inconsistente en "+c.getOrden().getCodigo()+".",adelante,atras); previo=c; }
        if(previo!=ultimo) return new ResultadoIntegridad(false,"El último recorrido no coincide con ultimo.",adelante,atras);
        Nodo siguiente=null;
        for(Nodo c=ultimo;c!=null;c=c.getAnterior()) { if(++atras>cantidad) return new ResultadoIntegridad(false,"Ciclo detectado hacia atrás.",adelante,atras); if(c.getSiguiente()!=siguiente) return new ResultadoIntegridad(false,"Enlace siguiente inconsistente en "+c.getOrden().getCodigo()+".",adelante,atras); siguiente=c; }
        if(siguiente!=primero || adelante!=cantidad || atras!=cantidad) return new ResultadoIntegridad(false,"El contador no coincide con los recorridos.",adelante,atras);
        return new ResultadoIntegridad(true,"Estructura válida: extremos, enlaces y contador son consistentes.",adelante,atras);
    }
    public boolean estaVacia() { return cantidad == 0; }
    public int getCantidad() { return cantidad; }
    public Nodo getPrimero() { return primero; }
    public Nodo getUltimo() { return ultimo; }
    private void asegurarCodigoDisponible(OrdenTrabajo orden) { Objects.requireNonNull(orden,"La orden es obligatoria."); if(buscarNodoPorCodigo(orden.getCodigo())!=null) throw new IllegalArgumentException("El código "+orden.getCodigo()+" ya existe."); }
    private static final class IteradorOrden implements Iterator<OrdenTrabajo> { private Nodo actual; private final boolean adelante; IteradorOrden(Nodo inicial,boolean adelante){actual=inicial;this.adelante=adelante;} public boolean hasNext(){return actual!=null;} public OrdenTrabajo next(){if(actual==null)throw new NoSuchElementException(); OrdenTrabajo o=actual.getOrden();actual=adelante?actual.getSiguiente():actual.getAnterior();return o;} }
    private static final class IteradorNodo implements Iterator<Nodo> { private Nodo actual; private final boolean adelante; IteradorNodo(Nodo inicial,boolean adelante){actual=inicial;this.adelante=adelante;} public boolean hasNext(){return actual!=null;} public Nodo next(){if(actual==null)throw new NoSuchElementException(); Nodo n=actual;actual=adelante?actual.getSiguiente():actual.getAnterior();return n;} }
}
