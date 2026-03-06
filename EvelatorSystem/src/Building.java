import java.util.ArrayList;
import java.util.List;

public class Building {
    List<Elevarator> elevaratorList  ;
    ElevatorController controller;



    public Building(List<Elevarator> elevaratorList ) {
        this.elevaratorList = elevaratorList;
        this.controller = new ElevatorController( elevaratorList );
    }

    public  ElevatorController getController () {
        return  controller  ;
    }
}