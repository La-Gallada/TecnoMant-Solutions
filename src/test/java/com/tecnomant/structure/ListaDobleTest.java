package com.tecnomant.structure;

import com.tecnomant.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Evidencia automatizada de CP-01 a CP-15 y de los enlaces físicos de la lista. */
class ListaDobleTest {
    private OrdenTrabajo orden(String c){return new OrdenTrabajo(c,"Cliente "+c,"Descripción",LocalDate.of(2026,1,1),"Técnico",Prioridad.MEDIA,EstadoOrden.PENDIENTE,BigDecimal.ONE);}
    private ListaDoble base(){ListaDoble l=new ListaDoble();l.insertarFinal(orden("OT-1"));l.insertarFinal(orden("OT-2"));l.insertarFinal(orden("OT-3"));return l;}
    @Test void cp01_insertarEnListaVacia(){ListaDoble l=new ListaDoble();l.insertarInicio(orden("OT-1"));assertEquals(1,l.getCantidad());assertSame(l.getPrimero(),l.getUltimo());}
    @Test void cp02_insertarAlInicio(){ListaDoble l=base();l.insertarInicio(orden("OT-0"));assertEquals("OT-0",l.getPrimero().getOrden().getCodigo());assertTrue(l.verificarIntegridad().valida());}
    @Test void cp03_insertarAlFinal(){ListaDoble l=base();l.insertarFinal(orden("OT-4"));assertEquals("OT-4",l.getUltimo().getOrden().getCodigo());assertTrue(l.verificarIntegridad().valida());}
    @Test void cp04_insertarIntermedio(){ListaDoble l=base();l.insertarEnPosicion(orden("OT-X"),2);assertEquals("OT-X",l.nodoEnPosicion(2).getOrden().getCodigo());assertTrue(l.verificarIntegridad().valida());}
    @Test void cp05_buscarExistente(){assertEquals("OT-2",base().buscarPorCodigo("ot-2").getCodigo());}
    @Test void cp06_buscarInexistente(){assertNull(base().buscarPorCodigo("OT-Z"));}
    @Test void cp07_eliminarPrimero(){ListaDoble l=base();l.eliminarInicio();assertEquals("OT-2",l.getPrimero().getOrden().getCodigo());assertNull(l.getPrimero().getAnterior());}
    @Test void cp08_eliminarUltimo(){ListaDoble l=base();l.eliminarFinal();assertEquals("OT-2",l.getUltimo().getOrden().getCodigo());assertNull(l.getUltimo().getSiguiente());}
    @Test void cp09_eliminarIntermedio(){ListaDoble l=base();l.eliminarPorCodigo("OT-2");assertSame(l.getUltimo(),l.getPrimero().getSiguiente());assertTrue(l.verificarIntegridad().valida());}
    @Test void cp10_eliminarUnico(){ListaDoble l=new ListaDoble();l.insertarFinal(orden("OT-1"));l.eliminarPorCodigo("OT-1");assertTrue(l.estaVacia());assertNull(l.getPrimero());assertNull(l.getUltimo());}
    @Test void cp11_recorrerAdelante(){StringBuilder r=new StringBuilder();for(OrdenTrabajo o:base().recorrerAdelante())r.append(o.getCodigo());assertEquals("OT-1OT-2OT-3",r.toString());}
    @Test void cp12_recorrerAtras(){StringBuilder r=new StringBuilder();for(OrdenTrabajo o:base().recorrerAtras())r.append(o.getCodigo());assertEquals("OT-3OT-2OT-1",r.toString());}
    @Test void cp13_codigoDuplicado(){ListaDoble l=base();assertThrows(IllegalArgumentException.class,()->l.insertarFinal(orden("OT-2")));}
    @Test void cp14_modificarExistente(){ListaDoble l=base();l.modificar("OT-2","Nueva","Técnico 2",Prioridad.CRITICA,EstadoOrden.FINALIZADA,new BigDecimal("44"));assertEquals("Nueva",l.buscarPorCodigo("OT-2").getDescripcion());assertEquals(EstadoOrden.FINALIZADA,l.buscarPorCodigo("OT-2").getEstado());}
    @Test void cp15_eliminarInexistente(){ListaDoble l=base();assertThrows(IllegalArgumentException.class,()->l.eliminarPorCodigo("OT-Z"));assertEquals(3,l.getCantidad());assertTrue(l.verificarIntegridad().valida());}
    @Test void rechazaPosicionInvalidaYCostoNegativo(){assertThrows(IllegalArgumentException.class,()->base().insertarEnPosicion(orden("OT-X"),0));assertThrows(IllegalArgumentException.class,()->new OrdenTrabajo("OT-X","C","D",LocalDate.now(),"T",Prioridad.BAJA,EstadoOrden.PENDIENTE,new BigDecimal("-1")));}
    @Test void integridadDeListaVacia(){assertTrue(new ListaDoble().verificarIntegridad().valida());}
    @Test void rechazaPosicionesNegativasYMayoresAlLimite(){ListaDoble l=base();assertThrows(IllegalArgumentException.class,()->l.insertarEnPosicion(orden("OT-X"),-1));assertThrows(IllegalArgumentException.class,()->l.insertarEnPosicion(orden("OT-X"),l.getCantidad()+2));assertEquals(3,l.getCantidad());assertTrue(l.verificarIntegridad().valida());}
    @Test void eliminarDeListaVaciaNoCambiaExtremos(){ListaDoble l=new ListaDoble();assertThrows(IllegalStateException.class,l::eliminarInicio);assertThrows(IllegalStateException.class,l::eliminarFinal);assertTrue(l.estaVacia());assertNull(l.getPrimero());assertNull(l.getUltimo());}
    @Test void operacionesConsecutivasConservanEnlacesYContador(){ListaDoble l=new ListaDoble();l.insertarFinal(orden("OT-2"));l.insertarInicio(orden("OT-1"));l.insertarEnPosicion(orden("OT-3"),3);l.insertarEnPosicion(orden("OT-X"),2);l.eliminarPorCodigo("OT-X");l.eliminarFinal();assertEquals(2,l.getCantidad());assertEquals("OT-1",l.getPrimero().getOrden().getCodigo());assertEquals("OT-2",l.getUltimo().getOrden().getCodigo());assertTrue(l.verificarIntegridad().valida());}
    @Test void modificarInexistenteNoAlteraLaLista(){ListaDoble l=base();assertThrows(IllegalArgumentException.class,()->l.modificar("OT-Z","Nueva","T",Prioridad.ALTA,EstadoOrden.FINALIZADA,BigDecimal.TEN));assertEquals(3,l.getCantidad());assertEquals("Descripción",l.buscarPorCodigo("OT-1").getDescripcion());assertTrue(l.verificarIntegridad().valida());}
    @Test void duplicadoNoDistingueMayusculasNiMinusculas(){ListaDoble l=base();assertThrows(IllegalArgumentException.class,()->l.insertarInicio(orden("ot-2")));assertEquals(3,l.getCantidad());assertTrue(l.verificarIntegridad().valida());}
    @Test void buscarPorClienteDevuelveCoincidenciasDeNodosReales(){ListaDoble l=base();l.insertarFinal(new OrdenTrabajo("OT-4","Cliente Especial","Descripción",LocalDate.of(2026,1,2),"Técnico",Prioridad.ALTA,EstadoOrden.PENDIENTE,BigDecimal.ONE));int coincidencias=0;for(Nodo nodo:l.buscarPorCliente("cliente")){coincidencias++;assertNotNull(nodo.getOrden());}assertEquals(4,coincidencias);}
}
