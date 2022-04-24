
public class Main {

    public static void main(String[] args) {
        Thread[] creadores = new Thread[4];
        Thread[] revisores = new Thread[2];
        Thread[] consumidores = new Thread[2];

        Contenedor inicial = new Contenedor(0);

        for(int i=0;i<4;i++){
            Creador c = new Creador(i, inicial);
            Thread t = new Thread(c,"Hilo Creador "+i);
            creadores[i] = t;
        }

        for(int i=0; i<2; i++){
            Revisor revisor = new Revisor(i,inicial,validados);
            inicial.setReviewer(revisor);
            validados.setReviewer(revisor);
            Thread t = new Thread(revisor,"Hilo Revisor "+i);
            revisores[i] = t;

            Consumidor consumidor = new Consumidor(i);
            Thread cons = new Thread(consumidor,"Hilo Consumidor "+i);
            consumidores[i] = cons;
        }


        // lanzamos todos los hilos
       for(int i=0; i<4; i++){
           creadores[i].start();
           if (i<2){
               revisores[i].start();
              // consumidores[i].start();
           }
       }

    }
}