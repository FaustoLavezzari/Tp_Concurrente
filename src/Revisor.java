import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.TimeUnit;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Revisor extends Actor implements Runnable{

    private Contenedor buffer_inicial;
    private Contenedor buffer_validados;
    private Queue<Integer> cola = new LinkedList<>();
    private int datos_procesados;
    private Lock lock;

    public Revisor(int ID, Contenedor buffer_inicial, Contenedor buffer_validados) {
        super(2, ID);
        this.buffer_inicial = buffer_inicial;
        this.buffer_validados= buffer_validados;
        this.datos_procesados = 0;
        lock = new ReentrantLock();
    }

    private void validarDato(Dato dato){
        try{                                                                            //tiempo de espera
            TimeUnit.MILLISECONDS.sleep(timer1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        dato.addReviewer(id);                                                   //agrego este revisor al dato
        datos_procesados++;
        if(dato.getCantReviews()== 2){                                                   //el ultimo revisor lo agrega a validados
            buffer_validados.putDato(dato);
        } else if (dato.getCantReviews()> 2) {
            System.out.println("ERROR, UN DATO FUE REVISADO MAS VECES QUE LA CANTIDAD DE REVISORES");
        }
    }
    @Override
    public void putQueue(int datoID) {

        cola.add(datoID);
    }

    @Override
    public void run() {
        while (true){
            Dato dato = buffer_inicial.getDato(-2,id);
            if(dato.getID() != -1){
                validarDato(dato);
            }
        }
    }
}
