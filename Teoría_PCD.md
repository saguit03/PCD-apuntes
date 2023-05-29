# Programación Concurrente y Distribuida (PCD)

## Programación concurrente

## Tema 1. Conceptos fundamentales

En los años 60 surgieron los primeros sistemas operativos. El **Mainframe** implementaba concurrencia a nivel ensamblador (en el SO), a lo que luego se sumaron IBM, General Electric, HoneyWell, Siemens y Telefunken.  

En 1972, apareció Concurrent Pascal.  
Esto fue el auge de la programación concurrente:

- Concepto de **thread** o hilo.
- Lenguajes como Java, dando soporte directo con primitivas específicas.
- Aparición de Internet y de procesadores multinúcleo.

### Proceso VS Programa

Un programa es algo estático, que está en el disco duro.  
Un proceso es dinámico y consume recursos. Es una actividad asíncrona susceptible de ser asignada por un procesador. Un proceso es concurrente si alguna instrucción se mezcla.  

- Un proceso puede ser parte de un programa en ejecución.
- Un programa puede dar lugar a uno o varios procesos.

### Relación entre procesos

- Nula
- De colaboración
- De competencia

### Concurrencia VS Paralelismo

Si dos procesos se ejecutan simultáneamente es programación paralela. La concurrencia es paralelismo potencial, pero son conceptos diferentes.

### Beneficios de la programación concurrente

- Aumento de la velocidad de ejecución
- Aprovechamiento de la CPU
- Solución de sistemas inherentemente concurrentes. Sistemas de control, tecnologías web, interfaces de usuario, simulación, SGDB.

### Sistema

- Monoprocesador
- Multiprocesador (memoria compartida o distribuida)

### Programa

- Concurrente
- Paralelo
- Distribuido [orden de ejecución, indeterminismo]
  
### Creación de threads en Java
  
- extends Thread
- implements Runnable

### Problemas inherentes a la programación concurrente

- Exclusión mutua
- Sección crítica
- Condición de sincronización

### Corrección de programas concurentes

#### Problemas de seguridad

- Exclusión mutua.
- Condición de sincronización.
- Interbloqueo (deadlock).

#### Problemas de vivacidaz

- Interbloqueo activo (livelock)
- Inanición (starvation)

### Condiciones de Bernstein

Lectura y escritura no se pueden ejecutar concurrentemente. Escritura y escritura, tampoco.

## Tema 2. Solución a la condición de sincronización

Soluciones a la exclusión mutua

### Soluciones Software

1. En ausencia de pugna, un proceso que quiera entrar debe hacerlo.
2. No debe producirse deadlock.
3. No puede haber postergación indefinida (un proceso entrará en un tiempo finito).

- Algoritmo de Peterson
- Algoritmo de Dekker

### Soluciones Hardware

- exchange
- getAndSet
- getAndAdd
- test&Set
- testAndSet(a, b). a = b, b = true

### Otras soluciones

Deshabilitación de instrucciones

### Conclusiones tema 1

Ninguna construcción software que conocemos solucionan la exclusión mutua y la condición de sincronización, y encima contienen espera ocupada o  espera activa: **spinklock** (tratamiento a muy bajo nivel).  
Las instrucciones atómicas a nivel hardware tampoco ayudan demasiado.  
Son necesarias nuevas primitivas: las **primitivas de sincronización**.

## Tema 3. Mecanismos para la sincronización de Threads

### Variables atómicas

//TODO

### Cerrojos

//TODO

### Primitivas propias de Java

//TODO

//TODO

### Estructuras de datos concurrentes

//TODO

### Mecanismos avanzados de sincronización

//TODO

## Programación distribuida

## Tema 4. Programación reactiva

### Lambdas

//TODO

### Streams paralelos

//TODO

### Observables y Observadores

//TODO

### Concurrencia en Programación Reactiva

//TODO

## Tema 5. Mecanismos de paso de mensaje

### Características de los mecanismos de paso de mensaje

#### ¿Identificamos emisor y receptor en el proceso de comunicación?

- **Comunicación directa**.
  - **Simétrica** (ambos están identificados). La ventaja es la seguridad (los procesos están perfectamente identificados), pero modificar el nombre de un proceso implica modificar el programa.  
    `SEND (A, message)`. Send a message to process A.  
    `RECEIVE (B, message)`. Receive a message from process B.
  - **Asimétrica** (el emisor identifica al receptor).  
    `SEND (A, message)`. Send a message to process A.  
    `RECEIVE (Id, message)`. Receive a message (in Id the system would indicate the identification of the sender).
- **Comunicación indirecta**.  
  `SEND (mailbox, message)`. Send a message to the mailbox  
  `RECEIVE (mailbox, message)`. Receive a message from the mailbox
  - **Buzón** (mailbox)
    - 1 a 1
    - 1 a n
    - n a 1 (puerto)
    - n a n
  - **Canales**
    - fuertemente tipados
    - comunicación síncrona
    - 1 a 1

#### Tipo de comunicación

- Síncrona (rendezvous y extended rendezvous)
- Asíncrona
- Paso de mensajes futuro (Future message passing)

#### Canal (o medio) de comunicación

- **Flujo**
  - unidireccional (Sockets UDP, comunicación asíncrona)
  - bidireccional (Sockets TCP, comunicación RPC)
- **Capacidad** del canal
  - cero (comunicación síncrona)
  - finito (comunicación asíncrona)*
  - infinito (comunicación asíncrona)*  
  *buffer asociado al canal
- **Longitud** de los mensajes
  - fija (fragmentación provoca desorden)
  - variable (message = head + body)
- Canales con o sin **tipo**.
- **Parámetros** pasados
  - por referencia (siempre en sistemas distribuidos)
  - por copia (implica compartición de memoria, por lo que nunca se usa en distribuida; menos seguro en concurrencia, pero más eficiente)
- Transmisión de **errores**.

### Paso de mensaje síncrono

Se produce una cita, rendezvous (aka synchronous message passing): la comunicación no empieza hasta que ambos, emisor y receptor, estén preparados.

### Espera selectiva

#### Select sentence: Dijkstra, 1975

```
select
RECEIVE (process1, message);
sentences;
or
RECEIVE (process2, message);
sentences
or
...
or
RECEIVE (process3, message);
sentences;
end select;
```

#### Selective waiting with guards

```
...
select
when condition1 =>
RECEIVE(process1, message);
sentences;
or
when condition2 =>
RECEIVE(process2, message);
sentences;
or
...
or
when conditionN =>
RECEIVE(processN, message);
sentences;
end select;
...

```

Las condiciones solo se evalúan cuando llegan a SELECT.

### Paso de mensaje asíncrono

El emisor envía los datos cuando él quiera y el receptor los recibe cuando le venga en gana.

### Invocación remota aka extended-rendezvous

La comunicación Extended-Rendezvous es una extensión de la comunicación Rendezvous que permite que más de dos procesos se comuniquen entre sí. En este tipo de comunicación, el emisor espera la recepción del mensaje por parte del receptor y una respuesta determinada.  
#### Remote invocation

El término remoto se refiere a otro **proceso**. El cliente y el servidor pueden estar en el mismo contexto y máquina.

#### RPC (Remote Procedure Call)

El término remoto se refiere a otro **procesador**. El cliente y el servidor están en distintas máquinas (distribuidas). Por ejemplo: Java RMI y gRPC.  

Ambos son modelos de comunicación síncronos, su flujo es bidireccional y son buenos para aplicaciones del estilo cliente/servidor.

### Sentencia SELECT

Communication alternatives are modelled using `Selectable` objects. `MailBox`, `Channel` and `EntryPoint` classes inherits from `Selectable`.  
The mode of use is as follows. After creating an object of type `Selector`, you attach to it objects of type `Selectable` and finally invokes any of the various selection methods available.  
To add the different communication alternatives to a select, we will use the `addSelectable (Selectable s, boolean sender)` method that will allow us, thanks to the sender parameter, to indicate if the added alternative will be used to send (true) or to receive messages (false). Once this is done, before invoking any of the selection methods, we can assign a boolean value to the guard that each communication alternative has associated. If we do not do that, it is assumed that the value assigned is true.

## Tema 6. Programación con Sockets

### Concepto de sockets

Un socket es un objeto de software que permite **enviar y recibir datos** entre hosts remotos o entre procesos locales. Es un punto de conexión de comunicaciones que posee un nombre y dirección en una red. Los sockets se crean y se utilizan con un sistema de peticiones o de llamadas de función a veces llamados interfaz de programación de aplicación de sockets (API).  
Un socket lo crea el sistema operativo. Java implementa sus clases por encima de las funciones que ofrecen el sistema operativo.

- Un **cliente** inicia la comunicación y solicita un servicio al servidor.
- El **servidor** espera peticiones y ofrece el servicio pedido.
- Un **puerto** es una dirección lógica (un número) por el que un servicio se ofrece/accede. Lo proporciona el Sistema Operativo.  

Un socket es una abstracción del SO. Las aplicaciones los crean, los usan y los cierran cuando ya no hacen falta. Su comportamiento lo controla el SO.  
**Los procesos envían/reciben mensajes a través de sockets**. Internet se comunica usando sockets. El proceso que se va a comunicar se identifica por su socket. Cada socket tiene un identificador, que está formado por la IP del ordenador y un número de puerto.

#### Protocolo TCP/IP

Un socket API (Application Program Interface) permite a la aplicación usar los protocolos TCP/IP y define las operaciones permitidas (abrir, leer, escribir, cerrar, etc.) y sus parámetros.  

Se transportan **bytes**. La aplicación protocolo proporciona la semántica.  

##### Data marshalling (serialización)

El marshalling o serialización es el proceso de **transformar la representación de memoria** de un objeto en un formato de datos adecuado para el almacenamiento o la transmisión. Se utiliza típicamente cuando los datos deben moverse entre diferentes partes de un programa de computadora o de un programa a otro.  

En resumen, el marshalling es el proceso de convertir objetos en flujos de bytes capaces de ser almacenados en dispositivos, bases de datos o de ser enviados a través de la red y, posteriormente, ser capaces de reconstruirlos en los equipos donde sea necesario.

### Tipos de sockets

Desde el punto de vista de su comportamiento:

- **Activos**. Pueden enviar y recibir datos a través de una conexión.
- **Pasivos**. Esperan a intentos de conexión. Cuando llega una petición, se asigna un socket activo.

Desde el punto de vista del protocolo:

- Sockets TCP. Stream.
- Sockets UDP. Datagrama.

### Sockets TCP (Stream)

Se comportan como una llamada telefónica.  

1. El equipo local solicita el establecimiento de comunicación al canal del equipo remoto.
2. Una vez se ha creado el canal, la comunicación puede empezar.

- Se garantiza la llegada.
- Stream de **bytes**: llegada en orden.
- Orientado a **conexión**: un socket por conexión (cliente)
- La conexión se establece y luego los datos se intercambian.

#### Clases en Java TCP

- `Socket`. Establecimiento del cliente y conexión con servidor.
- `ServerSocket`. Establecimiento de un socket pasivo en un servidor. Se usa para escuchar peticiones.

### Sockets UDP (Datagram)

Se comportan de forma similar a una carta postal. Lo más importante es la **velocidad**. Los mensajes se pueden perder o llegar fuera de orden.  
Son menos fiables que los TCP, por lo que habría que implementar, si fuera necesario, sus propios mecanismos de verificación.

- Un **socket** para recibir mensajes (como un buzón). No se garantiza la entrega ni el orden.
- Un **datagrama** es un paquete independiente.
- Muchas **direcciones** cada paquete.

Se envían datagramas sin conexión, de forma rápida y sin asentimiento. La comunicación es rápida y simple.

#### Clases en Java UDP

- `DatagramPacket`. Implementa el `Paquete` de datos que se envían por la red.
  - En la recepción se debe especificar:
    - Un **buffer** donde almacenar los datos recibidos (array de bytes).
    - Un entero indicando el tamaño máximo de los datos a recibir.
    ```DatagramPacket dp=new DatagramPacket(buffer, tam);```
  - En el envío se debe especificar:
    - El buffer de datos a enviar.
    - El tamaño de los datos a enviar.
    - Dirección (address).
    - Puerto.  
    ```DatagramPacket dp=new DatagramPacket(buffer,tam,direcc,puerto);```
- `DatagramSocket`. Maneja sockets UDP, permitiendo enviar y recibir datos (datagrams) por la red.
  - En la recepción se ha de especificar el puerto en el que las peticiones serán escuchadas.  
  ```DatagramSocket ds_recep=new DatagramSocket(1234);```
  - En el envío no es necesario especificar parámetros, porque ya están en el mismo datagrama.  
  ```DatagramSocket ds_envi=new DatagramSocket();```

### ¿TCP o UDP?

Depende de la aplicación.

- **TCP** cuando se necesite integridad de la información u orden en los mensajes. Por ejemplo en control remoto o transferencia de archivos.
- **UDP** cuando se necesiten comunicaciones rápidas o no importe la pérdida de paquetes. Por ejemplo en aplicaciones de tiempo real (videoconferencias) o aplicaciones distribuidas en una LAN.

## Tema 7. Problemas de programación distribuida

*Los sistemas distribuidos son programas que se ejecutan en máquinas diferentes*.

### Programación distribuida: requisitos y posibilidades

- **Mecanismos de comunicació**n, que pueden ser directos o indirectos, y simétricos o asimétricos.
- **Sincronización** asíncrona o síncrona (simple/extended rendezvous).
- Características del **canal**: tipado del canal, parámetros, capacidad y dirección del flujo.  

### Problemas de la programación distribuida

- Sincronización del reloj.
- Exclusión mutua.
- Detección de terminación.
- Detección de interbloqueo pasivo (deadlock).
- Estado global del sistema.
- Elección de líder.
- Consenso (blockchain).

### Algoritmos distribuidos

#### Algoritmos de sincronización de reloj

- Relojes físicos: Algoritmo de Christian.
- Relojes lógicos: Algoritmo de Lamport.
- Relojes vectoriales: Algoritmo de vector.

#### Algoritmos de exclusión mutua

- Solución centralizada
- Solución en anillo
- Solución descentralizada

#### Algoritmos de consenso

- Algoritmo de una ronda (One-round)
- Algoritmo de dos rondas (Two-round)
- Blockchain

#### Algoritmos de estado global del sistema

- Algoritmo de Chandy-Lamport

#### Algoritmos de elección de líder

- Algoritmo de selección en topología en anillo
- Algoritmo acosador (bully algorithm)

## Sincronización de reloj

### Algoritmo de Christian - Reloj físico

Un sistema distribuido con `i` nodos, cada uno tiene un reloj local `Ci`. Queremos que todos los relojes tengan la misma hora `t` y que sea la hora real.  
Sin embargo, los chips de los relojes no son exactos. Tienen un pequeño error, por lo que tienden a ser cada vez más diferentes a `t`.  
La solución es **sincronizar los relojes periódicamente de una fuente fiable**.
  
El algoritmo de Christian sirve para sincronizar un reloj local con el de un servidor fiable. Algunas consideraciones a tener en cuenta:

- El servidor proporciona una hora muy fiable, probablemente sincronizada con más servidores fiables.
- Los relojes **no** pueden volver atrás.
- La sincronización requiere paso de mensajes, pero consume tiempo.

#### Funcionamiento del algoritmo de Christian

1. El cliente pregunta la hora al servidor en t0 (cliente).
2. El servidor responde con el valor de su reloj en ese momento (tc).
3. El cliente recibe la hora en t1 (según su reloj).
4. C = tc + (t1-t0)/2

Si C es mayor que la hora actual del Cliente, entonces el Cliente tomará C como hora actual.  
Si es menor, entonces el reloj del cliente se pausará durante (Cc-C) unidades de tiempo.

#### Análisis del algoritmo de Christian

El algoritmo de Christian asume que el **tiempo de transmisión es el mismo** para la petición y la respuesta, por lo que tc se obtiene justo en la mitad del proceso de comunicación.  
Si uno de los mensajes dura más tiempo que el otro, la configuración no será correcta.  
La **sincronización perfecta es imposible**, por lo que los programas tendrán que tolerar este error inherente al problema.

### Algoritmo de Lamport - Reloj lógico

Los relojes lógicos son herramientas que sirven para establecer orden en eventos que ocurren, de tal forma que sean independientes de los relojes físicos. Son bastante útiles para saber **si un evento ha ocurrido antes que otro**. Tienen **sincronización perfecta, pero con ciertos límites**.

#### a -> b

a ocurre antes que b, y todos los nodos están de acuerdo en este orden.

#### Si a -> b & b -> c entonces a -> c

Aquí tenemos orden parcial, ya que puede haber concurrencia (x || y).

#### Funcionamiento del algoritmo de Lamport

1. Cada nodo tiene un contador que se incializa a 0.
2. Con cada envío de mensaje, el contador se incrementa.
3. Cada mensaje m se etiqueta (Cm) con el contador del emisor.
4. Cuando un nodo p recibe un mensaje, actualiza su reloj (Cp). **Cp = max (Cp, Cm) + 1**.
5. Al finalizar, tenemos un orden parcial. Este se puede convertir en total añadiendo el **id** del nodo como sufijo.

Sin embargo, en el caso de **a -> b** y **C(a) < C(b)**, no podemos asegurar **a -> b** o que **a || b**.

### Relojes vectoriales

Para saber si **a->b** o **a || b**, dados **N** nodos, cada nodo **p** mantiene un Vp con N marcas de tiempo (timestamps). **V0[0] = k** es el número de eventos para 0.  

1. Se asignan vectores iniciales para cada nodo [0,0,0].
2. Cuando se envía un mensaje, entonces el propio valor se actualiza y el vector se envía con el mensaje.
3. Cuando un nodo recibe un mensaje, incrementa su propio valor. Comprueba si los otros valores actuales son superiores que el valor actual y actualiza **Vp[i] = max(Vp[i], Vm[i])**.

- Un reloj es menor que otro si **V(a) < V(b)**
- Todos los componentes en V(a) son menores o iguales que los componentes en V(b), PERO uno de ellos es estrictamente inferior: **a->b**.
- Si **a -> b => V(a) < V(b)** y **V(a) < V(b) => a -> b**:  

Si **V(a) < V(b) == false & V(b) < V(a) == false ==> a || b**

## Exclusión mutua

### Solución centralizada

Hay un líder que controla el acceso a la sección crítica. Concede permiso si está libre, y bloquea y anota los procesos que solicitan acceso cuando está ocupado.

### Solución en anillo

No hay nodo que haga de líder, sino que hay un token que se van pasando entre ellos. Cuando un proceso quiera entrar a la sección crítica, debe esperar hasta tener el token para poder hacerlo. El nodo que tenga el token lo retendrá hasta que acabe la sección crítica.

### Solución distribuida (descentralizada)

La exclusión mutua se resuelve con un mensaje broadcast. No hay nodo líder.  
Cuando un nodo quiere entrar en la SC, avisa a los demás de que quiere acceder a ella. Si ningún otro nodo está en ella, le responden ACK y entra en la SC.  
Si por el contrario, ya hay un proceso en la SC, este no responderá a la pregunta broadcast, por lo que el nodo que quiera entrar ahora esperará a que el otro proceso responda.  
Cuando varios nodos quieran entrar a la vez en la sección crítica, hay que usar el algoritmo de Lamport para etiquetar cada intento y así controlar el orden.

## Estado global del sistema: Chandy-Lamport

El estado global está formado por:

- El estado específico de cada nodo, con sus variables y valores.
- Mensajes enviados y pendientes.  

Algunas de sus aplicaciones es recolectar basura (garbage collector) al ver objetos remotos que no se estén utilizando.  

El escenario ideal sería capturar un momento en el tiempo (instantánea o snapshot) y preguntar a todos los nodos su información, pero no podemos lograr una sincronización perfecta. Por ello, usaremos una instantánea consistente (consistent snapshot).  
Para esto necesitamos varios nodos, topología completa y canales fiables (FIFO o unidireccionales).

//Ver vídeos o diapositivas para ilustrarlo mejor

## Elección de líder

Los líderes son necesarios en un sistema distribuido, porque simplifican los algoritmos y disminuyen el número de mensajes para acuerdos.  
Tenemos que elegir un nuevo líder al inicio de la ejecución o cuando el líder actual no responde.

### Bully's algorithm - Algoritmo abusón

0. Un nodo se da cuenta de que el líder no responde y notifica de que hay que elecciones con un **mensaje de elección**. Solo se comunica con nodos que tengan un identificador superior.
1. Los nodos activos responderán con ACK. Cuando un nodo recibe al menos un ACK, el nodo se retira del proceso de selección.
2. Se repite el paso anterior (enviar mensaje, esperar ACK) hasta que algún nodo no recibe un ACK y se convierte en el nuevo líder.
3. El nuevo líder se comunica con los demás con un **mensaje de coordinación**.

### Topología en anillo

0. Un nodo se da cuenta de que el líder no responde y empieza el algoritmo. Este nodo será el inicializador y enviará un mensaje: `(ID inicializador, ID líder temporal)`.
1. Cuando un nodo recibe el mensaje, comprueba el ID del líder temporal y, si es menor que él, lo actualiza. Luego continúa enviando el mensaje al siguiente.
2. Se repite el paso anterior hasta que el mensaje llega al nodo inicializador, quien comunica al resto de nodos quién es el nuevo líder con un **mensaje de coordinación**.

## Consenso: Blockchain

El problema del **acuerdo bizantino** requiere de un proceso líder, con un valor inicial, que debe llegar a un acuerdo con otros procesos sobre su valor inicial. En ausencia de fallo no tiene interés. Dos tipos de fallo:

- **Crash failure**. Un nodo deja de funcionar, lo que puede causar que el resto de nodos no lleguen a un consenso.
- **Byzantine failure**. Un nodo tiene un comportamiento anómalo, incluso malintencionado.

Sujeto a las siguientes condiciones:

- **Acuerdo**. Todos los procesos que no fallan deben estar de acuerdo en el mismo valor.
- **Validez**. Si el proceso líder no falla, entonces el valor acordado por el resto de procesos debe ser el inicial del líder.
- **Terminación**. Todo proceso que no falle debe tomar una decisión.

### El problema del consenso

Difiere del acuerdo bizantino en que cada proceso tiene un valor inicial y todos los procesos que no fallan deben estar de acuerdo en un valor de esos. Debe satisfacer:

- **Acuerdo**. La decisión final de todo proceso que no falla debe ser la misma.
- **Validez**. Si todo proceso que no falla empieza con el mismo valor v, entonces la decisión final debe ser v.
- **Terminación**. Todo proceso que no falle debe tomar una decisión.

### El problema de la consistencia interactiva

Difiere del acuerdo bizantino en que cada proceso tiene un valor inicial y todos los procesos correctos deben acordar un conjunto de valores, con un valor para cada proceso. Se debe cumplir:

- **Acuerdo**. Todos los procesos que no fallan deben llegar a un acuerdo en los mismos valores para el array A[v1...vn].
- **Validez**. Si el proceso i no falla y su valor inicial es Vi, entonces todos los procesos que no fallan deben estar de acuerdo en Vi como el i-ésimo elemento del array A. Si el proceso j falla, entonces los procesos que no fallan pueden acordar cualquier valor para A[j].
- **Terminación**. Cada proceso que no falla debe eventualmente tomar una decisión sobre el array A.

## Tema 8. Plataformas de componentes distribuidos

- Los sistemas distribuidos de amplia escala están compuestas actualmente por microservicios. Permiten bajo acoplamiento e incluso desarrollo en múltiples lenguajes de programación. Permiten escalabilidad (cores, dispositivos, nodos, clusters, data-centers).
- Comunicación fundamentalmente estructurada como RPC.
  - Múltiples nodos de comunicación RPC.
  - Terminología habitual: cleintes usan los stub para llamar a los métodos en los servicios/servidores.
  - Facilidad para usar interfaces (síncronos y/o asíncronos) a través del código generado de soporte.
- Bastante documentación relacionada con la descripción de sistemas RPC.

- **Local Procedure Call** ejecuta localmente.
- **Remote Procedure Call** ejecuta remotamente.

### gRPC

Google ya ha tenido cuatro generaciones de sistemas RPC internos: los Stubby.  

- Todas las aplicaciones de producción y sistemas se producen usando RPC.
- Alrededor de 10¹⁰ invocaciones RPC por segundo.
- API escritas en distintos lenguajes tales como C++, Java, Python o Go.  

A través de gRPC introduce las prácticas aprendidas internamente relacionadas con escalabilidad, rendimiento o desarrollo de API.

- Definición sencilla de servicios: Protocol buffers y generación automática del código de soporte.
- Gran escalabilidad: Protocol buffers (again) y HTTP/2.
- Soporte de múltiples lenguajes de programación.
- Utilizado por grandes empresas.

### Protocol Buffers

- Protocol Buffers es el lenguaje neutral para *serializar* datos: RPC y almacenamiento.
- Codifica los datos en **formato binario**. Es mucho más rápido y más ligero en comparación a JSON.
- Ventajas obtenidas por HTTP/2.
- Generadores de código para múltiples lenguajes.
- Fuertemente tipado.

### Conceptos gRPC

#### Tipos de comunicación

- **Unary RPC**. El cliente realiza una petición RPC y obtiene una respuesta.
- **Server streaming RPC**. El cliente envía una petición y el servidor responde con un flujo (stream) de mensajes. El cliente leerá hasta que no queden más mensajes en el flujo.
- **Client streaming RPC**. El cliente envía un flujo (stream) de mensajes al servidor. El servidor leerá todos los datos de la secuencia de mensajes y responderá con un tipo de dato de retorno. El cliente leerá hasta que no queden más mensajes en el flujo.
- **Bidirectional streaming RPC**. Ambos lados de la comunicación intercambian secuencias de mensajes. Los flujos operan de forma independiente, de forma que los clientes y servidores pueden leer y escribir en cualquier orden. **Se garantiza el orden** de los mensajes en cada flujo.

#### Ciclo de vida RPC

- **Deadline/Timeouts**. gRPC permite a los clientes especificar cuánto tiempo están dispuestos a esperar para que una RPC se complete antes de que la RPC se termine con un error DEADLINE_EXCEEDED.
- **RPC Termination**. Los clientes y servidores especifican de forma independiente en qué momento ha terminado la invocación.
- **Canceling RPC**. Tanto clientes como servidores pueden cancelar las llamadas RPC en cualquier momento.

#### Información de comunicación

- **Metadata**. Los metadatos son información sobre una llamada RPC determinada en forma de una lista de pares clave-valor.
- **Channels**. Un canal gRPC proporciona una conexión gRPC en un host y puerto especificados. Se utiliza al crear un código auxiliar de cliente. Los clientes pueden especificar el canal argumento para modificar el comportamiento predeterminado de gRPC, como cambiar mensaje Comprensión activada o desactivada.
