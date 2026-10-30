# TecnoMant Solutions

Aplicación empresarial de escritorio para gestionar órdenes de mantenimiento mediante una **lista doblemente enlazada implementada manualmente**. Fue desarrollada para la Prueba 1 de Estructuras de Datos de la Universidad Latina de Costa Rica.

## Requisitos

- JDK 21.
- Maven 3.9 o superior.
- Windows 10/11 para la ejecución visual prevista.

No requiere base de datos ni servicios externos. Las órdenes viven únicamente en memoria y `ListaDoble` es su fuente de verdad.

## Ejecutar en Windows

Desde la raíz del proyecto:

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-21.0.12.1'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
java -version
mvn -version
mvn clean test
mvn javafx:run
```

Si Maven no está disponible globalmente, utilice una instalación local de Maven o configure `MAVEN_HOME` antes de ejecutar los mismos comandos.

## Uso académico

1. Inicie en **Dashboard** y pulse **Cargar ejemplos** para cargar OT-001 a OT-005.
2. Registre órdenes desde **Nueva orden**. Las posiciones son **1-basadas**: `1` inserta al inicio y el máximo permitido es `cantidad + 1`.
3. Consulte y filtre en **Órdenes**. Los filtros afectan solo la tabla, nunca los enlaces de `ListaDoble`.
4. Abra **Estructura** para inspeccionar `NULL`, PRIMERO, ÚLTIMO, enlaces y la verificación de integridad.
5. Abra **Pruebas** y ejecute CP-01 a CP-15. Cada caso usa una lista aislada; no altera las órdenes del usuario.

## Arquitectura

| Capa | Ubicación | Responsabilidad |
|---|---|---|
| Dominio | `model/` | `OrdenTrabajo`, prioridad y estado. |
| Estructura | `structure/` | `Nodo`, `ListaDoble` e integridad. |
| Aplicación | `service/`, `controller/` | Casos de uso y adaptación a JavaFX. |
| Presentación | `view/`, `resources/` | Vistas JavaFX, CSS e imágenes. |
| Pruebas | `src/test/java/` | CP-01 a CP-15 y casos límite. |

`ObservableList` se utiliza exclusivamente para presentar una proyección obtenida mediante recorridos de `ListaDoble`; no almacena ni sustituye la estructura principal.

## Evidencias y documentación

- [Arquitectura](docs/ARQUITECTURA.md)
- [Matriz de cumplimiento](docs/MATRIZ_CUMPLIMIENTO.md)
- [Guía de ejecución](docs/GUIA_EJECUCION.md)
- [Guía de defensa](docs/GUIA_DEFENSA.md)
- [Evidencias de pruebas](docs/EVIDENCIAS_PRUEBAS.md)
- [Auditoría final y evidencias](docs/evidencias/REPORTE_AUDITORIA_FINAL.md)

No se garantiza una calificación: corresponde a la evaluación externa del docente.
