# Evidencias visuales de ejecución

Esta carpeta contiene capturas reales proporcionadas desde una ejecución de TecnoMant Solutions. No son mockups ni imágenes reconstruidas. Cada archivo debe interpretarse según el estado que muestra, sin atribuirle resultados que no aparecen en pantalla.

| Archivo | Evidencia visible |
|---|---|
| `E01_Dashboard.png` | Dashboard con los cinco ejemplos cargados, métricas 4/2/2/1 y tabla de órdenes. |
| `E02_Gestion_Ordenes.png` | Centro de gestión con búsqueda, filtros y tabla de órdenes reales. |
| `E03_Nueva_Orden.png` | Formulario de alta, opciones de inserción 1-basadas y vista previa. |
| `E04_Editar_Orden.png` | Estado seguro inicial de edición antes de buscar una orden. |
| `E05_Lista_Doble.png` | Cinco nodos de la lista, referencias `NULL`, PRIMERO, ÚLTIMO e integridad válida. |
| `E09_Laboratorio_Inicial.png` | Estado inicial del laboratorio: 15 pruebas pendientes y lista principal íntegra. No acredita todavía las 15 pruebas aprobadas. |

## Capturas aún pendientes

Siguiendo `../CAPTURAS_MANUALES.md`, aún pueden añadirse evidencia específica del recorrido de inicio a final (`E06`), de final a inicio (`E07`), de una verificación de integridad independiente (`E08`), del Laboratorio tras ejecutar las 15 pruebas (`E09_Laboratorio_15_Pruebas.png`) y de la salida JUnit (`E10_Resultados_JUnit.png`).

Para completarlas, ejecute `mvn javafx:run`, capture únicamente la ventana de TecnoMant y compruebe que no aparezcan datos sensibles ni otras aplicaciones. La evidencia ejecutable de CP-01 a CP-15 y JUnit se documenta en `../REPORTE_AUDITORIA_FINAL.md` y `../../EVIDENCIAS_PRUEBAS.md`.
