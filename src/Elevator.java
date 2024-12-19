import java.util.Optional;
import java.util.TreeSet;

class Elevator {
    private final TreeSet<Integer> requestSet = new TreeSet<>();
    private int currentFloor = 1;
    private final int id;

    public Elevator(int id) {
        this.id = id;
    }

    public synchronized void addFloor(int f) {
        requestSet.add(f);
        notify();
    }

    public synchronized Optional<Integer> nextFloorOptional() {
        return requestSet.isEmpty() ? Optional.empty() : Optional.of(requestSet.pollFirst());
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        try {
            // Имитация времени перемещения лифтов
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
