import java.util.LinkedList;
import java.util.List;

public class Dato {
    private int ID;
    private  List<Revisor> reviewers;

    public Dato(int ID){
        this.ID = ID;
        reviewers = new LinkedList<Revisor>();
    }

    public int getCantReviews(){
            return reviewers.size();

    }

    public synchronized void addReviewer(Revisor revisor){
            reviewers.add(revisor);
    }

    public int getID(){
        return ID;
    }
}

