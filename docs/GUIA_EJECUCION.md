# Guía de ejecución

1. Instale un JDK 21 y Apache Maven 3.9 o superior. Compruebe `java -version` y `mvn -version`.
2. Abra una terminal en la raíz de `TecnoMant-Solutions`.
3. Ejecute las pruebas:

```powershell
mvn clean test
```

4. Ejecute la aplicación:

```powershell
mvn javafx:run
```

En Windows, si hay más de un JDK instalado, configure temporalmente la terminal:

```powershell
$env:JAVA_HOME = 'C:\ruta\a\jdk-21'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
```

Al iniciar, la lista está vacía por diseño. Use **Cargar ejemplos** para cargar OT-001 a OT-005, o registre datos propios en **Nueva orden**. Revise la operación en **Estructura** y ejecute las pruebas desde **Pruebas**.
