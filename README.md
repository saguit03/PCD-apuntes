# EjemplosExamenPCD

## Enunciado Distribuida
Se pretende construir un Servidor TCP en el puerto 10000 que recibe uno a uno los libros que le manda un cliente. Para cada libro  obtendrá su título y se lo mandará a un servicio alojado en un servidor gRPC que está en el  puerto 9090 y cuya definición tienes en el archivo EjemploExamen.proto.  
Ambos servidores están en localhost.
El método esPromocion del servicio devolverá “yes” si el libro está en promoción y “no” si el libro no está en promoción. Se considera que está en promoción si la longitud de su título es mayor que 20. El servidor TCP le devolverá a su vez al cliente “yes\n” ó “no\n” y el cliente imprimirá en su ventana ese valor.  
Tanto el cliente, como el servidor TCP y el servicio Promocion tendrán su propia ventana para imprimir los mensajes. A continuación tenes la salida que debería producirse en cada ventana. Para mayor comodidad también se facilita el esqueleto del Cliente con varios libros creados (ClientBookAlumnos.java).  
Forma de evaluar:
- Todo bien: 100%
- Solo cliente y servidor TCP: 50% (en este caso el servidor TCP es el que calcula si el libro está en promoción o no)
- Solo cliente, servicio y servidor gRPC: 50% (en este caso el cliente se comunica directamente con el servicio gRPC)