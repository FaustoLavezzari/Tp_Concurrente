import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dato {

    private int ID;
    private  List<Integer> reviewers;


    private ReadWriteLock reentrantLock = new ReentrantReadWriteLock(true);

    public Dato(int ID){
        this.ID = ID;
        reviewers = new LinkedList<>();
    }

    public synchronized int getCantReviews(){
            return reviewers.size();
    }

    public synchronized void addReviewer(Integer revisor){
            reviewers.add(revisor);
    }

    public synchronized List<Integer> getReviewers() {
        return reviewers;
    }

    public int getID(){
        return ID;
    }
}

