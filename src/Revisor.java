import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Revisor extends Actor implements Runnable{

    private Contenedor buffer_inicial;            //cada Creador tendra su propio buffer inicial
    private Contenedor buffer_validados;          //cada Creador tendra su propio buffer validados
    private  float variacion;
    private int datos_procesados;                 //variable de revision

    public Revisor(int ID,int timer, Contenedor buffer_inicial, Contenedor buffer_validados) {
        super(timer, ID);                         //llamamos al metodo de la super
        this.buffer_inicial = buffer_inicial;      //el contenedor  inicial se setea con el contenedor que le es pasado como parametro (en este caso el buffer_inicial)
        this.buffer_validados= buffer_validados;   //el contenedor  validados se setea con el contenedor que le es pasado como parametro (en este caso el buffer_validados)
        this.datos_procesados = 0;
        variacion = (float) 0.3;
    }

    private void validarDato(Dato dato){
        try{                                                                                    //tiempo de espera
            Random var = new Random();
            TimeUnit.MILLISECONDS.sleep((long) (timer1*var.nextFloat(1,1 + variacion)));
            dato.addReviewer(id);                                                   //agrego este revisor al dato
            datos_procesados++;
            if(dato.getReviewers().size() == 2){                                                   //el ultimo revisor lo agrega a validados
                buffer_validados.putDato(dato);
            } else if (dato.getReviewers().size() > 2) {
                System.out.println("ERROR, UN DATO FUE REVISADO MAS VECES QUE LA CANTIDAD DE REVISORES");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            Dato dato = buffer_inicial.getDato(id);
            if(dato.getID() != -1){                   //si el hashmap de datos NO esta vacio, pasa a validar datos
                validarDato(dato);
            }
        }
    }
}
