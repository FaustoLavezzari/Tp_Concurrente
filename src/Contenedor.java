import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class Contenedor {
    private int ID;
    private int capacidad;
    HashMap<Dato, Integer> datos;

    public Contenedor(int ID){
        this.capacidad = 100;
        this.ID = ID;
        this.datos = new HashMap<Integer, Dato>();
        queue_id_consumir = new LinkedList<Integer>();
    }

    public Dato getDato(int ID){
        return datos.get(ID);
    }

    //public void putDato();
}
