import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Contenedor {
    private int ID;
    private int capacidad;
    LinkedHashMap<Integer, Dato> datos;
    private ArrayList<Revisor> revisores;
    private Queue<Integer> queue_id_consumir;

    public Contenedor(int ID){
        this.capacidad = 100;
        this.ID = ID;
        revisores = new ArrayList<>();
        this.datos = new LinkedHashMap<>();
        queue_id_consumir = new LinkedList<>();

    }
    // parece que aca no hay problema de concurrencia
    public synchronized Dato getDato(int ID){
            if (ID != -1) {
                return datos.get(ID);
            } else {
                if (!datos.isEmpty()) {
                    return datos.get(datos.keySet().toArray()[0]); //obtiene la primer clave del map, haciendolo funcionar como cola
                } else {
                    return new Dato(-1);
                }
            }
    }

    public synchronized void putDato(Dato dato){
            if (datos.size() < capacidad) {
                datos.put(dato.getID(), dato);
                if (dato.getCantReviews() == 0) {
                    for (Revisor revisor : revisores) {
                        revisor.putQueue(dato.getID());
                    }
                }
            }
    }

    // no hay problema de concurrencia
    public void setRevisores(Revisor r){
        revisores.add(r);
    }

    public synchronized boolean removeDatos(int ID){
        if (datos.containsKey(ID)){
            datos.remove(ID);
            return true;
        }
        else{
            return false;
        }


    }
}
