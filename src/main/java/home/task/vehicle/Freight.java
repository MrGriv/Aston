package home.task.vehicle;

public interface Freight {
    default void transportsFreight() {
        System.out.println("Transporting freight");
    }
}
