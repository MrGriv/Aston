package home.task.animal;

public interface Water {
    default void liveInWater() {
        System.out.println("Live in water");
    }
}
