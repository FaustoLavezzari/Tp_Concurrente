import java.util.ArrayList;

public class Creador extends Actor implements Runnable{
    ArrayList<Dato> datos;

    public Creador(int ID, Contenedor b){
        super(10,ID);
        this.datos = new ArrayList<>();
    }
    @Override
    protected void procesar(){  // al re pedo
        System.out.println("Creando datos..");
    }
    @Override
    protected int getId(){  // no tiene sentido pero boa
        return this.id;
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
        this.datos.add(dato);
        b
    }
}
