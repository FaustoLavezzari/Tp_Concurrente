//public class Consumidor extends Actor{

    public Consumidor(int id, Contenedor buffer_validados, Contenedor buffer_inicial){
        super(1, id);
        this.buffer_validados=buffer_validados;
        this.buffer_inicial=buffer_inicial;
        int datos_procesados = 0;
    }

    @Override
    public void run() {
        while (datos_procesados < 50){
            eliminarDato();
        }
    }
}
