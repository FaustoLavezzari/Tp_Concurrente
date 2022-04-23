import java.util.ArrayList;

public class Creador extends Actor implements Runnable{
    private Contenedor inicial;
    private int datos_creados;

    public Creador(int ID, Contenedor b){
        super(1,ID);
        this.inicial = b;
        datos_creados= 0;
    }

    @Override
    public void run(){
        int i = this.id;
        // generamos datos indefinidamente
        int f = 0;
        while(f <100){
            Dato d = new Dato(i);
            this.agregarDato(d);
        }
      //  System.out.printf("hilo %s creo %d datos \n",Thread.currentThread().getName(), datos_creados);
      //  System.out.printf("%s hay %d datos en el contenedor\n",Thread.currentThread().getName(), inicial.getSize());
    }

    private void agregarDato(Dato dato){
        try{
            TimeUnit.MILLISECONDS.sleep(timer1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.inicial.putDato(dato);
        datos_creados++;
    }
}
