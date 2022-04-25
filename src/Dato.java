import java.util.LinkedList;
import java.util.List;

public class Dato {

    private int ID;
    private  List<Integer> reviewers;

    public Dato(int ID){
        this.ID = ID;
        reviewers = new LinkedList<Integer>();
    }

    public synchronized int getCantReviews(){
            return reviewers.size();
    }

    public synchronized void addReviewer(Integer revisor){
            reviewers.add(revisor);
    }

    public List<Integer> getReviewers() {
        return reviewers;
    }

    public int getID(){
        return ID;
    }
}

