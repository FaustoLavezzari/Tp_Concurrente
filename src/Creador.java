import java.util.concurrent.TimeUnit;

public class Creador extends Actor implements Runnable{

    private Contenedor inicial;   //cada Creador tendra su propio buffer inicial
    private int datos_creados;    //variable de revision

    public Creador(int ID, Contenedor b){
        super(20,ID);             //todos los creadores se setean con un tiempo de procesamiento de 20 y un ID determinado
        this.inicial = b;         //el contenedor  inicial se setea con el contenedor que le es pasado como parametro (en este caso el buffer_inicial)
        this.datos_creados= 0;    //el numero de datos procesador cuando se crea el hilo es 0
    }

    @Override
    public void run(){            //este es el metodo que van a ejecutar todos los hilos una vez que se inicialicen
        int i = this.id;
        while(true){
            Dato d = new Dato(i);
            this.agregarDato(d);
            i+=4;
        }
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
}
