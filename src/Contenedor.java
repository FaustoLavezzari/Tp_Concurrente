import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Contenedor {
    private int ID;                 //ID de cada contenedor
    private int capacidad;          //cantidad de cada contenedor
    LinkedHashMap<Integer, Dato> datos;    //El HashMap va a cumplir la funcion de contenener los datos y asociarlos a un ID
    private ReadWriteLock reentrantLock;


    public Contenedor(int ID){
        this.capacidad = 100;    //cada contenedor tendra una capacidad de 100 datos
        this.ID = ID;
        this.datos = new LinkedHashMap<>();
        this.reentrantLock = new ReentrantReadWriteLock(true);    //el lock sera justo para los hilos que estuvieron esperando mas tiempo
    }
    public Dato getDato(int id_actor){
            reentrantLock.readLock().lock();
            if (!datos.isEmpty()){
                if (id_actor < 2) {                                              //Verifico que me esta utilizando el revisor
                    for (Dato dato : datos.values()){                            //recorro los valores (values) del map datos
                        if (!dato.getReviewers().contains(id_actor)){            //si el revisor todavia no reviso el dato en donde me encuentro, retorno el dato y termina la funcion
                            reentrantLock.readLock().unlock();
                            return dato;                                         //en el caso que no entre en el if, sigue recorriendo el map
                        }
                    }
                }else {                                                          //Me esta utilizando un consumidor
                    Dato dato = datos.get(datos.keySet().toArray()[0]);
                    reentrantLock.readLock().unlock();
                    return dato;                                                 //obtiene la primer clave del map, haciendolo funcionar como cola
                }
            }
            reentrantLock.readLock().unlock();
            return new Dato(-1);                                               //devuelve un -1 si el hashmap esta vacio 
    }

    public void putDato(Dato dato){
        reentrantLock.writeLock().lock();
        if (datos.size() < capacidad) {
            datos.put(dato.getID(), dato);
        }
        reentrantLock.writeLock().unlock();
    }

    public int getCantDeDatos (){
        return this.datos.size();
    }

    public boolean removeDatos(int ID){
        reentrantLock.writeLock().lock();
        if (datos.containsKey(ID)){
            datos.remove(ID);
            reentrantLock.writeLock().unlock();
            return true;
        }
        else{
            reentrantLock.writeLock().unlock();
            return false;
        }
    }
}
