import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Thread[] creadores = new Thread[4];                  //Creamos un arreglo de tamaño 4 para los hilos creadores
        Thread[] revisores = new Thread[2];                  //Creamos un arreglo de tamaño 2 para los hilos revisores
        Thread[] consumidores = new Thread[2];               //Creamos un arreglo de tamaño 4 para los hilos consumidores
        ArrayList<Consumidor> consures = new ArrayList<Consumidor>(); //Creamos un ArrayList para el log

        Contenedor inicial = new Contenedor(0);           //Creamos el buffer inicial instanciando un objeto de contenedor
        Contenedor validados = new Contenedor(1);         //Creamos el buffer de validados instanciando un objeto de contenedor

        for(int i=0;i<4;i++){                                 //utilizamos un ciclo for para poder instanciar los 4 hilos creadores
            Creador c = new Creador(i, inicial);
            Thread t = new Thread(c,"Hilo Creador "+i);  //como Creadores implementa la interfaz runnable, la utilizamos para instanciar los hilos
            creadores[i] = t;
        }

        for(int i=0; i<2; i++){                                      //utilizamos un ciclo for para poder instanciar los 2 hilos revisores
            Revisor revisor = new Revisor(i, inicial, validados);
            Thread t = new Thread(revisor,"Hilo Revisor "+ i);  //como Revisores implementa la interfaz runnable, la utilizamos para instanciar los hilos
            revisores[i] = t;

            Consumidor consumidor = new Consumidor(i, validados, inicial);    //utilizamos un ciclo for para poder instanciar los 2 hilos consumidores
            Thread cons = new Thread(consumidor,"Hilo Consumidor "+ i);  //como Consumidores implementa la interfaz runnable, la utilizamos para instanciar los hilos
            consumidores[i] = cons;
            consures.add(consumidor);
        }
        Thread tlog = new Thread(new Log(consures, inicial, validados), "Hilo LOG ");  //instanciamos el hilo Log

        // lanzamos todos los hilos
       tlog.start();               //iniciamos el hilo Log desde un principio para que no se pierda ningun dato durante la impresion
       for(int i=0; i<4; i++){
           creadores[i].start();   //iniciamos los hilos creadores
           if (i<2){
               revisores[i].start();  //iniciamos los hilos revisores
               consumidores[i].start(); //iniciamos los hilos consumidores
           }
       }

        // esperamos a que los hilos finalicen
        for(int i=0; i<4; i++){               //en este punto del programa frenamos el main hasta que los demas hilos terminen de ejecutarse
            try{
                creadores[i].join();
                if (i<2){
                    revisores[i].join();
                    consumidores[i].join();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try{
            tlog.join();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("\n\nTODOS los hilos en este punto. Fin del programa");
        }
    }
}