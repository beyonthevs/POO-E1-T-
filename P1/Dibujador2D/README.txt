DIBUJADOR 2D - Java Swing
==========================

Requisitos:
- Java 17 o superior.

Archivos fuente:
- Dibujador2D.java       -> ventana principal y barra de herramientas
- PanelDibujo.java       -> lienzo, mouse, preview y acciones
- Figura.java            -> clase base de figuras
- FiguraRectangulo.java  -> rectángulos
- FiguraOvalo.java       -> óvalos
- FiguraLinea.java       -> líneas
- TipoFigura.java        -> tipos disponibles

Compilar desde la carpeta del proyecto:

    javac -encoding UTF-8 -d classes src/*.java

Ejecutar:

    java -cp classes Dibujador2D

También se incluye un JAR ejecutable:

    java -jar Dibujador2D.jar

Funcionalidad:
- Rectángulo, Óvalo y Línea.
- Dibujar haciendo clic y arrastrando.
- Color configurable mediante el botón de color.
- Figuras rellenas o huecas.
- Vista previa semitransparente mientras se arrastra.
- Deshacer elimina la última figura.
- Borrar TODO elimina todas las figuras.
- El arrastre funciona en cualquier dirección.
