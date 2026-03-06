import java.util.ArrayDeque;
import java.util.Queue;

public class Elevarator {
    private int elevaratorId ;
    private Directions   direction  ;
    private int currentFlore  ;

    private Queue<Integer> requests = new ArrayDeque<>();

    public Elevarator(int elevaratorId ) {
        this.elevaratorId = elevaratorId;
        this.direction  =Directions.IDEL  ;
        this.currentFlore = 0  ;
    }

    public int getElevaratorId() {
        return elevaratorId;
    }

    public void setElevaratorId(int elevaratorId) {
        this.elevaratorId = elevaratorId;
    }

    public Directions  getDirection() {
        return direction;
    }

    public void setDirection(Directions  direction) {
        this.direction = direction;
    }


    public int getCurrentFlore() {
        return currentFlore;
    }

    public void setCurrentFlore(int currentFlore) {
        this.currentFlore = currentFlore;
    }


     public  void move() {
        while( ! requests.isEmpty()) {
            int targetFlore = requests.poll() ;
            System.out.println( "Elevetor number "+ elevaratorId +"Elevator moving " +
                    currentFlore + "to ..." + targetFlore );

            currentFlore = targetFlore ;
            System.out.println("Elevetor  " + elevaratorId  + "" +
                    "Reached " + currentFlore );
        }
         direction = Directions.IDEL  ;
     }

     public void  addRequest( int featureRequestFlore ) {
        requests.add(featureRequestFlore);
     }
}