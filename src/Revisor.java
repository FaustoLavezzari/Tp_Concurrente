public class Revisor extends Actor{

    public Revisor(int ID, Contenedor buffer_incial, Contenedor buffer_validados) {
        super(1, ID);
        this.buffer_incial = buffer_incial;
        this.buffer_validados= buffer_validados;
        datos_procesados = 0;
    }

    //private boolean validarDato(Dato dato){

    //}

    @Override
    public void run() {
        while (datos_procesados < 100){
            if (!(cola.size() == 0)){
                //int id_dato= ;
                validarDato(buffer_incial.getDato(cola.peek()));
                cola.remove();
            }
        }
    }
}
