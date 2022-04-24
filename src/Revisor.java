import java.util.concurrent.TimeUnit;
import java.util.LinkedList;
import java.util.Queue;

public class Revisor extends Actor implements Runnable{

    private Contenedor buffer_inicial;
    private Contenedor buffer_validados;
    private Queue<Integer> cola = new LinkedList<>();
    private int datos_procesados;

    public Revisor(int ID, Contenedor buffer_inicial, Contenedor buffer_validados) {
        super(1, ID);
        this.buffer_inicial = buffer_inicial;
        this.buffer_validados= buffer_validados;
        datos_procesados = 0;
    }

    private void validarDato(Dato dato){
        try{                                                                            //tiempo de espera
            TimeUnit.MILLISECONDS.sleep(timer1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        dato.addReviewer(this);                                                   //agrego este revisor al dato
        datos_procesados++;
        if(dato.getCantReviews()== 2){                                                   //el ultimo revisor lo agrega a validados
            buffer_validados.putDato(dato);
        } else if (dato.getCantReviews()> 2) {
            System.out.println("ERROOOOOOOOOOOOOR, UN DATO FUE REVISADO MAS VECES, QUE LA CANTIDAD DE REVISORES");
        }
    }
    public void putQueue(int datoID){
        cola.add(datoID);
    }

    @Override
    public void run() {
        while (datos_procesados < 100){
            if (!(cola.size() == 0)){
                validarDato(buffer_inicial.getDato(cola.peek()));
                cola.remove();
            }
        }
    }
}
