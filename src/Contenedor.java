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
    public synchronized Dato getDato(int actor, int id_actor){                           //Funcion que nos devuelve el dato para revisar, o el dato para consumir
           if (!datos.isEmpty()){
               if (actor == -2) {                                                           //Verifico que me esta utilizando el revisor
                   for (Dato dato : datos.values()){                                     //recorro los valores (values) del map datos
                       if (!dato.getReviewers().contains(id_actor)){                        //si el revisor todavia no reviso el dato en donde me encuentro, retorno el dato y termina la funcion
                           return dato;                                                  //en el caso que no entre en el if, sigue recorriendo el map
                       }
                   }
               }
               else {                                                                     //Me esta utilizando un consumidor
                   return datos.get(datos.keySet().toArray()[0]);                       //obtiene la primer clave del map, haciendolo funcionar como cola
               }
           }
           return new Dato(-1);
    }

    public synchronized void putDato(Dato dato){
            if (datos.size() < capacidad) {
                datos.put(dato.getID(), dato);
            }
    }

    // no hay problema de concurrencia
    public void setRevisores(Revisor r){
        revisores.add(r);
    }

    public int getCantDeDatos (){
        return this.datos.size();
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
