package home.task.task1.vehicle;

public interface Freight {
    default void transportsFreight() {
        System.out.println("Transporting freight");
    }
}
