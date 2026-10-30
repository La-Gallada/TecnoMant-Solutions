# Guía de capturas manuales verificables

No use mockups ni imágenes de diseño como evidencia de ejecución. Abra la aplicación con `mvn javafx:run`, cargue OT-001 a OT-005 y capture estas pantallas reales:

1. **Dashboard con ejemplos:** tabla con OT-001 a OT-005 y métricas 4 activas, 2 pendientes, 2 en proceso, 1 finalizada.
2. **Nueva orden:** formulario vacío, vista previa y las tres opciones de inserción; si se selecciona posición específica, capture el control 1-basado visible.
3. **Editar orden:** busque OT-001 y muestre código, cliente y fecha bloqueados, junto con los campos editables.
4. **Estructura:** los cinco nodos, `NULL` en ambos extremos, PRIMERO, ÚLTIMO, flechas e integridad válida.
5. **Recorrido bidireccional:** capture la selección en el primer nodo tras “Recorrer inicio → final” y en el último tras “Recorrer final → inicio”.
6. **Laboratorio:** tras “Ejecutar todas”, capture 15 aprobadas, 0 fallidas y la consola de resultados.

Antes de cada captura, incluya en el nombre del archivo la fecha y la sección, por ejemplo: `2026-10-29-estructura-cinco-nodos.png`. Conserve los archivos fuera de `target/` y añádalos al entregable solo si corresponden a una ejecución real.
