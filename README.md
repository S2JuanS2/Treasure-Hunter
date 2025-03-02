Del Rio Juan Sebastián, padrón: 103.337

          TREASURE HUNTER

Descripción:

El juego consiste en recolectar tesoros (minerales, rocas, fósiles) mediante un gancho que se moverá de forma horizontal. Al presionar un botón el gancho descenderá hasta colisionar con algún objeto, este lo agarrará y se le asignará al jugador una recompensa monetaria según la rareza y el tamaño del objeto extraído. En cada partida el jugador comenzará con una cantidad de combustible determinada en donde podrá ademas comprar con lo ganado.

Patrones de diseño: 

-BUILDER, se implementó para la construcción de Tesoros y así evitar una gran cantidad de parámetros en el constructor y obtener varias representaciones del mismo. Al Tesoro se le asignó un tipo, una posición, un nombre, un tamaño y una rareza, en base a estos últimos dos, se le asignará un precio de recompensa por la obtención del mismo.
  
El tipo de tesoro puede ser un metál (oro, diamante, rubi, hierro) , un fósil (huesos) o una roca (granito, mármol), la posición será la coordenada de extracción asignada de forma aleatoria dentro de los límites del juego, el tamaño (pequeño, mediano, grande) se le asignará un valor númerico al igual que a la rareza (1-10) para el cálculo del precio.   

Se definió una clase directora para establecer el orden en el que se deben ejecutar los pasos en la construcción.
                     
-MEMENTO, para evitar el problema de la restricción de acceso, se utilizó para guardar y restaurar el estado del gancho, del personaje y de la lista de tesoros para poder continuar la partida en distintos momentos. El gancho tiene un motor, que se irá mejorando con la compra de actualizaciones.                                  
                            
El juego se desarrolló en Java.

Ilustración del juego:


![Captura de pantalla 2025-03-02 005734](https://github.com/user-attachments/assets/e8bc101b-5e5a-451b-85f7-d39d750fedcb)

