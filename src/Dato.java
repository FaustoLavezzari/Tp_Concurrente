import java.util.LinkedList;
import java.util.List;

public class Dato {
    private int cantReviews;
    private int ID;
    private List<Revisor> reviewers;

    public Dato(int ID){
        this.ID = ID;
        reviewers = new LinkedList<Revisor>();
    }

    public int getCantReviews(){
        return ca
    }

    public void addReviewer(Revisor revisor){
        reviews.add(revisor);
    }

    public boolean checkReviewer(Revisor revisor){
        return reviews.contains(revisor);
    }

    public int getID(){
        return ID;
    }
}
