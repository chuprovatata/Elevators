import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class RequestListener implements Runnable {
    private final Elevator[] elevators;

    public RequestListener(Elevator... elevators) {
        this.elevators = elevators;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Welcome to the Elevator System!");
            System.out.println("Please enter a floor number between 1 and 20 (or type 'exit' to quit):");

            String floorNumberStr;
            while ((floorNumberStr = bufferedReader.readLine()) != null) {
                if (handleExit(floorNumberStr)) break;
                if (isValidFloorNumber(floorNumberStr)) {
                    int floor = Integer.parseInt(floorNumberStr);
                    System.out.printf("User  pressed: %d%n", floor);
                    Elevator bestElevator = findBestElevator(floor);
                    if (bestElevator != null) {
                        bestElevator.addFloor(floor);
                    }
                } else {
                    System.out.printf("Invalid floor request: %s. Please enter a floor number between 1 and 20.%n", floorNumberStr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean handleExit(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the Elevator System. Goodbye!");
            return true;
        }
        return false;
    }

    private boolean isValidFloorNumber(String s) {
        return s != null && s.matches("[1-9]|1[0-9]|20");
    }

    private Elevator findBestElevator(int requestedFloor) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);
            if (distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }
}