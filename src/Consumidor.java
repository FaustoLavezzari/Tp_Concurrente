import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;



public class Consumidor extends Actor implements Runnable{
    private Contenedor buffer_validados;
    private Contenedor buffer_inicial;
    private int datos_procesados;

    public Consumidor(int id, Contenedor buffer_validados, Contenedor buffer_inicial){
        super(4, id);
        this.buffer_validados=buffer_validados;
        this.buffer_inicial=buffer_inicial;
        int datos_procesados = 0;

    }
    public void eliminarDato(){
        Dato eliminar = buffer_validados.getDato(-1); //pide el primer elemento del mapa
        if(eliminar.getID() != -1) {
            try {                                                                            //tiempo de espera
                TimeUnit.MILLISECONDS.sleep(timer1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(buffer_inicial.removeDatos(eliminar.getID()) && buffer_validados.removeDatos(eliminar.getID())) {
                datos_procesados++;
            }
        }
    }
    public int getDatos_procesados(){                                                   //nos sirve para el log
        return datos_procesados;
    }
    @Override
    public void putQueue(int datoID){};
    @Override
    public void run() {
            while (true) {
                this.eliminarDato();
            }
        }
    }

