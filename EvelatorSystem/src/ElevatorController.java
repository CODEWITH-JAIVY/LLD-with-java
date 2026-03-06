import java.util.ArrayList;
import java.util.List;


// ye  nearest elevator ko pata karaga
public class ElevatorController {
    List<Elevarator>elevaratorList  ;

    public ElevatorController(List<Elevarator>elevaratorList) {
        this.elevaratorList = elevaratorList;
    }

    public Elevarator findNearestElevator (int flore ) {
        Elevarator nearestElevarator   = null   ;
        int midDistance = Integer.MAX_VALUE ;

        for(Elevarator elevarator : elevaratorList ) {
            int distance   = Math.abs(elevarator.getCurrentFlore() - flore ) ;
            if( distance  < midDistance) {
                distance = midDistance  ;
                nearestElevarator = elevarator  ;
            }
        }
        return  nearestElevarator ;
    }

    public void requestHandle ( Request request ) {
        int sourceFlore  = request.getSourceFlore();
        Elevarator elevarator  = findNearestElevator(sourceFlore) ;
        System.out.println("Request at flore "+
                sourceFlore + "" +
                "Coming Elevarator " + elevarator.getElevaratorId() );

        elevarator.addRequest ( sourceFlore ) ;
        elevarator.move();
    }


}