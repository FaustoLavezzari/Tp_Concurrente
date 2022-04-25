import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Consumidor extends Actor implements Runnable{
    private Contenedor buffer_validados;
    private Contenedor buffer_inicial;
    private int datos_procesados;
    private  float variacion;

    public Consumidor(int id,int timer ,Contenedor buffer_validados, Contenedor buffer_inicial){
        super(timer, id);
        this.buffer_validados=buffer_validados;
        this.buffer_inicial=buffer_inicial;
        variacion = (float) 0.3;
        datos_procesados = 0;
    }

    public void eliminarDato(){
        Dato eliminar = buffer_validados.getDato(3); //pide el primer elemento del mapa
        if(buffer_inicial.removeDatos(eliminar.getID()) && buffer_validados.removeDatos(eliminar.getID())) {          //si los valores se pudieron eliminar de forma exitosa de ambos buffers entonces pasa a dormir el hilo por un tiempo pseudoaleatorio e incrementa los datos procesados
            try {                                                                            //tiempo de espera
                Random var = new Random();
                TimeUnit.MILLISECONDS.sleep((long) (timer1*var.nextFloat(1,1 + variacion)));
                datos_procesados++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
            while (true) {
                this.eliminarDato();
            }
    }

    public int getDatos_procesados(){                                                   //nos sirve para el log
        return datos_procesados;
    }

}

