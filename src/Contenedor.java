import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Contenedor {
    private int ID;
    private int capacidad;
    LinkedHashMap<Integer, Dato> datos;
    private ArrayList<Revisor> revisores;
    private Queue<Integer> queue_id_consumir;
    private ReadWriteLock reentrantLock;
    private Lock readLock;
    private Lock writeLock;


    public Contenedor(int ID){
        this.capacidad = 100;
        this.ID = ID;
        this.revisores = new ArrayList<>();
        this.datos = new LinkedHashMap<>();
        this.queue_id_consumir = new LinkedList<>();
        this.reentrantLock = new ReentrantReadWriteLock(true);
        this.readLock = reentrantLock.readLock();
        this.writeLock = reentrantLock.writeLock();

    }
    //public synchronized Dato getDato(int id_actor){
    public Dato getDato(int id_actor){
        //if(readLock.tryLock()){
            readLock.lock();
            if (!datos.isEmpty()){
                //readLock.unlock();
                if (id_actor < 2) {                                                      //Verifico que me esta utilizando el revisor
                    for (Dato dato : datos.values()){                                       //recorro los valores (values) del map datos
                        if (!dato.getReviewers().contains(id_actor)){                     //si el revisor todavia no reviso el dato en donde me encuentro, retorno el dato y termina la funcion
                            readLock.unlock();
                            return dato;                                                  //en el caso que no entre en el if, sigue recorriendo el map
                        }
                    }
                }else {                                                                     //Me esta utilizando un consumidor
                    Dato dato = datos.get(datos.keySet().toArray()[0]);
                    readLock.unlock();
                    return dato;                       //obtiene la primer clave del map, haciendolo funcionar como cola
                }
            }
            readLock.unlock();
            return new Dato(-1);
        //}
       // else{
        //    return new Dato(-1);
        //}

    }


    //public synchronized void putDato(Dato dato){
    public void putDato(Dato dato){
        if (datos.size() < capacidad) {
            reentrantLock.writeLock().lock();
            datos.put(dato.getID(), dato);
            reentrantLock.writeLock().unlock();
        }
    }

    // no hay problema de concurrencia
    public void setRevisores(Revisor r){
        revisores.add(r);
    }

    public int getCantDeDatos (){
        return this.datos.size();
    }

    //public synchronized boolean removeDatos(int ID){
    public boolean removeDatos(int ID){
        if (datos.containsKey(ID)){
            reentrantLock.writeLock().lock();
            datos.remove(ID);
            reentrantLock.writeLock().unlock();
            return true;
        }
        else{
            return false;
        }
    }
}
