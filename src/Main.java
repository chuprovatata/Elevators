public class Main {
    public static void main(String[] args) {
        Elevator elevator1 = new Elevator(1);
        Elevator elevator2 = new Elevator(2);
        Elevator elevator3 = new Elevator(3);

        // Запуск потоков для обработки запросов
        new Thread(new RequestProcessor(elevator1)).start();
        new Thread(new RequestProcessor(elevator2)).start();
        new Thread(new RequestProcessor(elevator3)).start();

        // Запуск слушателя запросов
        new Thread(new RequestListener(elevator1, elevator2, elevator3)).start();
    }
}





