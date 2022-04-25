import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Creador extends Actor implements Runnable{

    private Contenedor inicial;   //cada Creador tendra su propio buffer inicial
    private int datos_creados;    //variable de revision
    private float variacion;

    public Creador(int ID,int timer, Contenedor b){
        super(timer,ID);             //llamamos al metodo de la super
        this.inicial = b;           //el contenedor  inicial se setea con el contenedor que le es pasado como parametro (en este caso el buffer_inicial)
        this.datos_creados= 0;      //el numero de datos procesador cuando se crea el hilo es 0
        variacion = (float) 0.3;
    }

    @Override
    public void run(){            //este es el metodo que van a ejecutar todos los hilos una vez que se inicialicen. En el caso de creador, su metodo Run se encarga de generar datos de manera indefinida
        int i = this.id;          //this.id hace referencia al atributo ID del hilo que esta corriendo el hilo en ese momento
        while(true){              //bucle infinito
            Dato d = new Dato(i); //se crea un dato con el ID igual a i
            this.agregarDato(d);  //llama al metodo agregarDato para continuar con la adicioh del dato al buffer inicial
            i+=4;                 //incrementa el valor de i en en 4. Esto va a generar una secuancia de ID unicos para cada hilo que no van a coincidir nunca con las secuancias de los demas hilos creadores
        }
    }

    private void agregarDato(Dato dato){
        try {
            Random var = new Random();
            TimeUnit.MILLISECONDS.sleep((long) (timer1*var.nextFloat(1,1 + variacion)));
            this.inicial.putDato(dato);
            this.datos_creados++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
