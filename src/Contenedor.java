import java.util.ArrayList;
import java.util.HashMap;

public class Contenedor {
    private int ID;
    private int capacidad;
    HashMap<Dato, Integer> datos;

    public Contenedor(int ID){
        this.capacidad = 100;
        this.ID = ID;
        reviewers = new ArrayList<>();
        this.datos = new HashMap<>();
        queue_id_consumir = new LinkedList<>();
    }

    public  Dato getDato(int ID){
        return datos.get(ID);
    }

    //public void putDato();
}
