import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Creador extends Actor implements Runnable{
    private Contenedor inicial;
    private int datos_creados;
    private Lock queueLock;

    public Creador(int ID, Contenedor b){
        super(2,ID);
        this.inicial = b;
        this.datos_creados= 0;
        queueLock = new ReentrantLock();
    }

    @Override
    public void run(){
        int i = this.id;
        // generamos datos indefinidamente
        while(i < 100){
            Dato d = new Dato(i);
            this.agregarDato(d);
            i+=4;
        }
      //  System.out.printf("hilo %s creo %d datos \n",Thread.currentThread().getName(), datos_creados);
      //  System.out.printf("%s hay %d datos en el contenedor\n",Thread.currentThread().getName(), inicial.getSize());
    }

    private void agregarDato(Dato dato){
        try {
            TimeUnit.MILLISECONDS.sleep(timer1);
            this.inicial.putDato(dato);
            this.datos_creados++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void putQueue(int datoID){
    }
}
