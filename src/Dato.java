import java.util.LinkedList;
import java.util.List;

public class Dato {
    private int ID;
    private List<Revisor> reviewers;

    public Dato(int ID){
        this.ID = ID;
        reviewers = new LinkedList<Revisor>();
    }

    public int getCantReviews(){
        return cantReviews;
    }

    public void addReviewer(Revisor revisor){
        reviewers.add(revisor);
    }

    public boolean checkReviewer(Revisor revisor){
        return reviewers.contains(revisor);
    }

    public int getID(){
        return ID;
    }
}

