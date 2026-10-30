# Evidencias de pruebas

La aplicación ejecuta CP-01 a CP-15 desde el Laboratorio sobre instancias aisladas, mostrando resultado esperado, obtenido, estado y detalle. El botón **Exportar evidencias** produce un archivo de texto con la ejecución real.

Para evidencia automatizada, ejecute `mvn clean test`: `ListaDobleTest` contiene un método por CP y casos límite adicionales de posición negativa, cero y máxima; costo negativo; lista vacía; operaciones consecutivas; modificación inexistente; duplicado sin distinción entre mayúsculas/minúsculas; búsqueda por cliente e integridad. Los resultados reproducibles se registran en `target/surefire-reports/` (directorio no versionado).

## Ejecución verificada

El 29 de octubre de 2026 se ejecutó `mvn clean test` con JDK 21: **23 pruebas ejecutadas, 0 fallos, 0 errores y 0 omitidas**. Las primeras 15 corresponden exactamente a CP-01 a CP-15; las 8 restantes cubren los casos límite anteriores.

Las capturas reales disponibles se incorporan en `docs/evidencias/capturas/`: Dashboard, gestión de órdenes, nueva orden, edición, estructura con cinco nodos e inicio del laboratorio. La captura del laboratorio muestra correctamente **15 pendientes**, por lo que no se utiliza como prueba visual de una ejecución aprobada. Consulte `docs/evidencias/CAPTURAS_MANUALES.md` para completar los recorridos, la ejecución aprobada del laboratorio y la salida JUnit sin fabricar evidencias.
