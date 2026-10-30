# Guía breve para la defensa

## Ideas clave

Una lista doblemente enlazada es una cadena de nodos. Cada nodo conserva una orden y dos referencias: `anterior` y `siguiente`. `primero` solo tiene anterior `null`; `ultimo` solo tiene siguiente `null`.

Al insertar entre A y B se crean los cuatro enlaces: `A.siguiente = nuevo`, `nuevo.anterior = A`, `nuevo.siguiente = B` y `B.anterior = nuevo`. Al eliminar se conectan A y B entre sí y se desconecta el nodo eliminado.

La búsqueda compara el código desde `primero`; el recorrido inverso parte de `ultimo`. Por eso no se usó `LinkedList`: la evaluación exige demostrar y controlar explícitamente esos enlaces y sus casos límite.

La UI está separada porque una tabla sirve para presentar; su `ObservableList` se reconstruye desde `recorrerAdelante()`. La única fuente de verdad continúa siendo `ListaDoble`.

## Preguntas frecuentes

**¿Cómo se evita un código repetido?** Antes de insertar, la lista recorre nodos con `buscarNodoPorCodigo`; si existe, lanza un mensaje y no cambia enlaces ni contador.

**¿Qué valida la integridad?** Extremos, reciprocidad de enlaces, conteos y ciclos potenciales. No se marca válida sin ejecutar esa lógica.

**¿Cómo demuestra que editar no duplica?** Se localiza el nodo y se invoca `orden.actualizar(...)` sobre el mismo objeto almacenado. El código permanece `final`.

**¿Qué representa el laboratorio?** Cada CP crea su propia `ListaDoble`, verifica una condición y conserva el detalle real. La lista del usuario permanece intacta.
