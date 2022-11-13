Del Rio Juan Sebastián, padrón: 103.337

          TREASURE HUNTER

Descripción:

El juego consiste en recolectar tesoros (minerales, rocas, fósiles) mediante un gancho que se moverá de forma horizontal. Al presionar una tecla el gancho descenderá hasta colisionar con algún objeto, este lo agarrará y se le asignará al jugador una recompensa según la rareza del objeto extraído. En cada partida se establecerá un límite de tiempo o de acciones (a definir).



Patrones de diseño: 

-BUILDER, se implementará para la construcción de Tesoros y así evitar una gran cantidad de parámetros en el constructor y obtener varias representaciones del mismo. Al Tesoro se le asignará un tipo, una posición, un nombre, un tamaño y una rareza, en base a estos últimos dos, se le asignará un precio de recompensa por la obtención del mismo.
  
El tipo de tesoro puede ser un metál (oro, diamante, rubi, hierro, cobre) , un fósil (huesos, ámbar) o una roca (granito, cuarcita, mármol, obsidiana), la posición será la coordenada de extracción asignada de forma aleatoria dentro de los límites del juego, el tamaño (pequeño, mediano, grande) se le asignará un valor númerico al igual que a la rareza (1-100) para el cálculo del precio.   

Se definirá una clase directora para establecer el orden en el que se deben ejecutar los pasos en la construcción.
                     
-MEMENTO, para evitar el problema de la restricción de acceso, se lo utilizará para guardar y restaurar el estado del gancho y del personaje para poder continuar la partida en distintos momentos. El gancho tendrá ciertas propiedades al igual que el personaje, que se irán obteniendo con la compra de mejoras (atributos de fuerza, resistencia, etc).                                   
                            
El juego se desarrollará en Java.

Ilustraciones a modo de ejemplo: 

![Treasure Hunter](https://user-images.githubusercontent.com/104468421/197403579-32875435-db1c-421e-9c8e-53aaaa7c7f32.png)


URL del juego: https://www.juegosdiarios.com/juegos/treasure-hunter-jack.html


