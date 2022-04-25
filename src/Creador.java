import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Creador extends Actor implements Runnable{

    private Contenedor buffer_inicial;   //cada Creador tendra su propio buffer inicial

    public Creador(int ID,int timer, Contenedor b){
        super(timer,ID);             //llamamos al metodo de la super
        this.buffer_inicial = b;           //el contenedor  inicial se setea con el contenedor que le es pasado como parametro (en este caso el buffer_inicial)
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
            Random var = new Random();                                                                       //Instancio un objeto Random
            TimeUnit.MILLISECONDS.sleep((long) (timer1*var.nextFloat(1,1 + variacion)));        //Aqui implementamos un sleep de tiempo pseudoaleatorio
            this.buffer_inicial.putDato(dato);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
