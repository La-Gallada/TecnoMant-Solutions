# Reporte de auditoría final

Fecha de verificación: 29 de octubre de 2026  
Proyecto: TecnoMant Solutions  
Alcance: Prueba 1 de Estructuras de Datos

## Resultado reproducible

Se ejecutó desde la raíz del proyecto, con JDK 21 y Maven 3.9.9:

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21.0.12.1'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
.\.tools\apache-maven-3.9.9\bin\mvn.cmd -Dmaven.repo.local=E:\U\TecnoMant-Solutions\.m2-repository clean test
```

Resultado: **BUILD SUCCESS**. JUnit ejecutó **23 pruebas**, con **0 fallos**, **0 errores** y **0 omitidas**. El informe XML y de texto de Surefire se genera bajo `target/surefire-reports/` cuando se reproduce el comando.

## Casos oficiales CP-01 a CP-15

| Caso | Resultado JUnit | Resultado Laboratorio | Evidencia principal |
|---|---|---|---|
| CP-01 | Aprobada | Aprobada | Primer nodo, extremos coincidentes. |
| CP-02 | Aprobada | Aprobada | Nuevo `primero` y enlaces válidos. |
| CP-03 | Aprobada | Aprobada | Nuevo `ultimo` y enlaces válidos. |
| CP-04 | Aprobada | Aprobada | Inserción en posición 2 e integridad válida. |
| CP-05 | Aprobada | Aprobada | Búsqueda de OT-2. |
| CP-06 | Aprobada | Aprobada | Búsqueda inexistente devuelve ausencia. |
| CP-07 | Aprobada | Aprobada | Actualización de `primero`. |
| CP-08 | Aprobada | Aprobada | Actualización de `ultimo`. |
| CP-09 | Aprobada | Aprobada | Vecinos reconectados tras eliminar intermedio. |
| CP-10 | Aprobada | Aprobada | Único nodo eliminado; extremos `null`. |
| CP-11 | Aprobada | Aprobada | Recorrido OT-1 → OT-2 → OT-3. |
| CP-12 | Aprobada | Aprobada | Recorrido OT-3 → OT-2 → OT-1. |
| CP-13 | Aprobada | Aprobada | Duplicado rechazado. |
| CP-14 | Aprobada | Aprobada | Campos autorizados actualizados en el mismo objeto. |
| CP-15 | Aprobada | Aprobada | Excepción controlada y lista sin cambios. |

El Laboratorio crea una nueva `ListaDoble` por caso. Por tanto, sus resultados no alteran la lista visible del usuario.

## Hallazgos y correcciones

| Hallazgo | Riesgo | Corrección |
|---|---|---|
| Faltaban verificaciones automatizadas explícitas para algunos bordes de la entrega. | Medio: evidencia académica incompleta. | Se añadieron pruebas para posición negativa/superior, eliminación vacía, operaciones consecutivas, modificación inexistente, duplicado por capitalización y búsqueda por cliente. |
| El README no explicaba ejecución ni restricciones académicas. | Bajo: entrega menos reproducible. | Se reescribió con requisitos, comandos Windows, posiciones 1-basadas, capas y enlaces a evidencias. |

No se detectó uso de colecciones predefinidas como estructura principal de órdenes. `ObservableList` se limita a presentar recorridos realizados sobre la lista manual.

## Inspección visual real

La aplicación JavaFX se abrió y se verificaron Dashboard, Órdenes, Nueva orden, Editar orden, Estructura y Pruebas. Con OT-001 a OT-005 cargadas se observaron 4 activas, 2 pendientes, 2 en proceso y 1 finalizada. La vista Estructura mostró los cinco nodos, referencias `NULL`, PRIMERO, ÚLTIMO y el resultado de integridad válida. El laboratorio mostró 15 aprobadas y 0 fallidas.

La revisión se realizó visualmente a 1280 × 760 y en ventana maximizada. No se generaron archivos de imagen desde la automatización, para no presentar capturas no verificables como evidencia versionada. Use la guía de capturas manuales para completar evidencia gráfica entregable.
