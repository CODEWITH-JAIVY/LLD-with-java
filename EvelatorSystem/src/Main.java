import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Elevarator> elevators = new ArrayList<>();

        elevators.add(new Elevarator(1));
        elevators.add(new Elevarator(2));
        elevators.add(new Elevarator(3));

        Building building = new Building(elevators);

        ElevatorController controller = building.getController();

        Request request1 = new Request(5, Directions.UP);
        Request request2 = new Request(2, Directions.DOWN);
        Request request3 = new Request(8, Directions.UP);

        controller.requestHandle(request1);
        controller.requestHandle(request2);
        controller.requestHandle(request3);
    }
}