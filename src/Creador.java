import java.util.ArrayList;

public class Creador extends Actor implements Runnable{
    private Contenedor inicial;

    public Creador(int ID, Contenedor b){
        super(10,ID);
        this.inicial = b;
    }

    @Override
    public void run(){
        int i = this.id;
        // generamos datos indefinidamente
        while(true){
            Dato d = new Dato(i);
            this.agregarDato(d);
        }
    }

    private void agregarDato(Dato dato){
        try{
            TimeUnit.MILLISECONDS.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.inicial.putDato(dato);

    }
}
