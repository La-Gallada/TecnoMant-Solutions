# Evidencias de pruebas

La aplicación ejecuta CP-01 a CP-15 desde el Laboratorio sobre instancias aisladas, mostrando resultado esperado, obtenido, estado y detalle. El botón **Exportar evidencias** produce un archivo de texto con la ejecución real.

Para evidencia automatizada, ejecute `mvn clean test`: `ListaDobleTest` contiene un método por CP y casos límite adicionales de posición negativa, cero y máxima; costo negativo; lista vacía; operaciones consecutivas; modificación inexistente; duplicado sin distinción entre mayúsculas/minúsculas; búsqueda por cliente e integridad. Los resultados reproducibles se registran en `target/surefire-reports/` (directorio no versionado).

## Ejecución verificada

El 29 de octubre de 2026 se ejecutó `mvn clean test` con JDK 21: **23 pruebas ejecutadas, 0 fallos, 0 errores y 0 omitidas**. Las primeras 15 corresponden exactamente a CP-01 a CP-15; las 8 restantes cubren los casos límite anteriores.

No se incluyen capturas fabricadas. Consulte `docs/evidencias/CAPTURAS_MANUALES.md` para una lista reproducible de capturas: cargue los ejemplos, abra Inicio, Nueva orden, Editar orden, Estructura y Pruebas, y capture cada pantalla de la aplicación ejecutada.
