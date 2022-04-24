import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Contenedor {
    private int ID;
    private int capacidad;
    HashMap<Integer, Dato> datos;
    private ArrayList<Revisor> reviewers;
    private Queue<Integer> queue_id_consumir;

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

    public  void putDato(Dato dato){
        if (!(datos.size() >= capacidad)) {
            datos.put(dato.getID(), dato);
            if (dato.getCantReviews() == 0){
                for (Revisor reviewer : reviewers) {
                    reviewer.putQueue(dato.getID());
                }
            }
            else if (dato.getCantReviews() == reviewers.size()){
                queue_id_consumir.add(dato.getID());
            }
        }
    }

    public void setReviewer(Revisor r){
        reviewers.add(r);
    }

    public  int  getCantReviewers(){
        return reviewers.size();
    }

    public  int getPorConsumir(){
        if (queue_id_consumir.size() > 0){
            return queue_id_consumir.remove();
        }
        else{
            return -1;
        }
    }

    public  void removeDatos(int ID){
        datos.remove(ID);
    }
}
