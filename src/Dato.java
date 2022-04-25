import java.util.LinkedList;
import java.util.List;

public class Dato {

    private int ID;                             //ID de cada dato
    private  List<Integer> reviewers;           //cada dato tendra una lista con los revisores que ya validaron ese dato

    public Dato(int ID){
        this.ID = ID;
        reviewers = new LinkedList<>();
    }

    public synchronized void addReviewer(Integer revisor){       //si un revisor valida un dato, lo añade a la lista de revisores
            reviewers.add(revisor);
    }

    public synchronized List<Integer> getReviewers() {       //devuelve la lista de revisores
        return reviewers;
    }

    public int getID(){             //obtiene el ID de un dato
        return ID;
    }
}

