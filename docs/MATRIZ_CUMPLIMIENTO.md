# Matriz de cumplimiento académico

Auditoría actualizada el 29 de octubre de 2026. La clasificación expresa evidencia técnica disponible; no constituye una calificación del docente.

| Criterio | Requisito del examen | Implementación | Pruebas y evidencia | Estado |
|---|---|---|---|---|
| Lista doblemente enlazada (25) | Lista manual, nodos reales, extremos y contador; no sustituir con colecciones. | `structure/Nodo.java`, `ListaDoble.java`: `primero`, `ultimo`, `cantidad`, enlaces `anterior`/`siguiente`. | `ListaDobleTest`: CP-01..15, integridad vacía, operaciones consecutivas. Búsqueda estática sin `LinkedList`, `ArrayList`, `Vector`, arreglos ni mapas en la estructura. | CUMPLE |
| Inserción (15) | Inicio, final e intermedia con enlaces correctos. | `insertarInicio`, `insertarFinal`, `insertarEnPosicion`. Posiciones 1-basadas. | CP-01..CP-04; pruebas de posición negativa, cero, límite superior y operaciones consecutivas. | CUMPLE |
| Eliminación (15) | Primero, último, intermedio, único e inexistente; reconectar vecinos. | `eliminarInicio`, `eliminarFinal`, `eliminarPorCodigo`. | CP-07..CP-10, CP-15 y prueba de eliminar lista vacía. | CUMPLE |
| Búsqueda (10) | Por código y coincidencias por cliente. | `buscarPorCodigo`, `buscarNodoPorCodigo`, `buscarPorCliente`. | CP-05, CP-06 y `buscarPorClienteDevuelveCoincidenciasDeNodosReales`. | CUMPLE |
| Recorrido bidireccional (10) | Primero→último y último→primero con controles de interfaz. | `recorrerAdelante`, `recorrerAtras`, navegación del controlador y vista. | CP-11, CP-12; visualizador y controles de navegación. | CUMPLE |
| Interfaz y experiencia de usuario (10) | Ventana moderna, indicadores, búsqueda, CRUD, tabla y controles. | `VentanaPrincipal.java`, `tecnomant.css`, recursos en `src/main/resources`. | Ejecución JavaFX auditada: Dashboard, Órdenes, Nueva, Editar, Estructura y Pruebas. | CUMPLE |
| Visualización de nodos (5) | Nodos y conexiones bidireccionales, PRIMERO/ÚLTIMO, actualización. | `mostrarEstructura`, `tarjetaNodo`, `datosNodo`. | Ejecución con OT-001..OT-005: NULL, cinco nodos, flechas, extremos e integridad visible. | CUMPLE |
| Validaciones y errores (5) | Obligatorios, duplicados, posición, costo, confirmación. | `OrdenTrabajo`, `ListaDoble`, formularios y diálogos `Componentes`. | CP-13, pruebas de posición/costo/duplicado por mayúsculas y modificación inexistente. | CUMPLE |
| Organización y POO (5) | POO y separación de presentación/lógica. | Paquetes `model`, `structure`, `service`, `controller`, `view`, `app`. | Arquitectura documentada; `ObservableList` solo es proyección visual. | CUMPLE |

## Convención de posiciones

`insertarEnPosicion(orden, posicion)` utiliza posiciones **1-basadas**. La posición `1` equivale a insertar al inicio y la máxima válida es `cantidad + 1`. Las consultas por posición aceptan de `1` a `cantidad`.
