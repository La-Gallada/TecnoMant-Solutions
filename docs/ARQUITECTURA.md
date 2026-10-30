# Arquitectura de TecnoMant Solutions

## Objetivo y tecnologías

Aplicación de escritorio Java 21 para administrar órdenes de mantenimiento con JavaFX, Maven, CSS y JUnit 5. La persistencia exigida es exclusivamente en RAM. JavaFX solo adapta datos para mostrarlos; no es una estructura de almacenamiento de órdenes.

## Capas y paquetes

| Paquete | Responsabilidad |
|---|---|
| `model` | Entidad `OrdenTrabajo` y enums `Prioridad` y `EstadoOrden`. |
| `structure` | `Nodo`, `ListaDoble` y verificación de integridad. |
| `service` | Casos de uso, estadísticas, demostración y laboratorio aislado. |
| `controller` | Adaptador entre el servicio y las proyecciones JavaFX. |
| `view` | Cinco pantallas JavaFX y componentes reutilizables. |
| `app` | Arranque de JavaFX. |

## Modelo y estructura principal

`OrdenTrabajo` conserva el código como `final`. La actualización autorizada cambia descripción, técnico, prioridad, estado y costo, validando todos los valores. Las fechas son `LocalDate` y el costo es `BigDecimal` no negativo.

`Nodo` contiene una orden y las referencias reales `anterior` y `siguiente`. `ListaDoble` conserva `primero`, `ultimo` y `cantidad`. No usa `LinkedList`, `ArrayList`, arreglos, `HashMap`, base de datos ni colecciones como fuente de verdad.

Las posiciones son **1-basadas**: posición 1 inserta al inicio y la última válida para insertar es `cantidad + 1`. La búsqueda, inserción, eliminación, navegación y recorridos siguen los enlaces de nodos.

## Algoritmos e integridad

- Inserción en inicio/final ajusta ambos extremos en el caso de lista vacía.
- Inserción intermedia enlaza `previo ⇄ nuevo ⇄ actual`.
- Eliminación intermedia enlaza vecinos y desconecta el nodo eliminado.
- Los recorridos producen iteradores desde `primero` o `ultimo`; la UI los consume para crear su proyección visual.
- `verificarIntegridad()` comprueba extremos, simetría de enlaces, contador, lista vacía y ciclos con límite por contador.

## UI y visualización

La vista utiliza tarjetas JavaFX, no imágenes de pantallas. Integra logo, iconos y paleta del kit. La pantalla Estructura crea una tarjeta por nodo real y muestra `NULL`, `PRIMERO`, `ÚLTIMO`, flechas, posición y referencias. El desplazamiento horizontal permite inspeccionar listas largas.

Las validaciones están en el dominio y se muestran en español. El laboratorio crea listas nuevas para cada CP y nunca toca la lista del usuario. Las pruebas JUnit cubren CP-01 a CP-15, integridad de lista vacía y valores límite.
