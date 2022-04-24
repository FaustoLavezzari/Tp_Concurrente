import java.util.concurrent.TimeUnit;


public class Consumidor extends Actor implements Runnable{
    private Contenedor buffer_validados;
    private Contenedor buffer_inicial;

    private int datos_procesados;

    public Consumidor(int id, Contenedor buffer_validados, Contenedor buffer_inicial){
        super(1, id);
        this.buffer_validados=buffer_validados;
        this.buffer_inicial=buffer_inicial;
        int datos_procesados = 0;
    }
    public void eliminarDato(){
        int datoID = buffer_validados.getPorConsumir();
        if(datoID != -1) {
            try {                                                                            //tiempo de espera
                TimeUnit.MILLISECONDS.sleep(timer1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer_inicial.removeDatos(datoID);
            buffer_validados.removeDatos(datoID);
            datos_procesados++;
        }
    }
    @Override
    public void run() {
        while (datos_procesados < 50){
            eliminarDato();
        }
    }
}
