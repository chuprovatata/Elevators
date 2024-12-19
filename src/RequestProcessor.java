import java.util.Optional;

class RequestProcessor implements Runnable {
    private final Elevator elevator;

    public RequestProcessor(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        while (true) {
            Optional<Integer> nextFloorOpt = elevator.nextFloorOptional();
            nextFloorOpt.ifPresentOrElse(this::processFloorRequest, this::waitForNextRequest);
        }
    }

    private void processFloorRequest(int floor) {
        int startingFloor = elevator.getCurrentFloor();
        elevator.setCurrentFloor(floor);
        System.out.printf("Elevator %d: %d -> %d%n", elevator.getId(), startingFloor, floor);
    }

    private void waitForNextRequest() {
        synchronized (elevator) {
            try {
                elevator.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
