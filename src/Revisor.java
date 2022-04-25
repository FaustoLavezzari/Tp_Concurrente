import java.util.concurrent.TimeUnit;

public class Revisor extends Actor implements Runnable{

    private Contenedor buffer_inicial;
    private Contenedor buffer_validados;
    private int datos_procesados;

    public Revisor(int ID, Contenedor buffer_inicial, Contenedor buffer_validados) {
        super(5, ID);
        this.buffer_inicial = buffer_inicial;
        this.buffer_validados= buffer_validados;
        this.datos_procesados = 0;
    }

    private void validarDato(Dato dato){
        try{                                                                            //tiempo de espera
            TimeUnit.MILLISECONDS.sleep(timer1);
            dato.addReviewer(id);                                                   //agrego este revisor al dato
            datos_procesados++;
            if(dato.getCantReviews()== 2){                                                   //el ultimo revisor lo agrega a validados
                buffer_validados.putDato(dato);
            } else if (dato.getCantReviews()> 2) {
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
            if(dato.getID() != -1){
                validarDato(dato);
            }
        }
    }
}
