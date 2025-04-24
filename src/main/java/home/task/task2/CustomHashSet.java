package home.task.task2;

import java.util.HashMap;

public class CustomHashSet<E> {
    private HashMap<E, Object> map;
    private static final Object OBJECT = new Object();

    public CustomHashSet() {
        map = new HashMap<>();
    }

    public int size() {
        return map.size();
    }

    public boolean add(E element) {
        return map.put(element, OBJECT) == null;
    }

    public boolean remove(E element) {
        return map.remove(element).equals(OBJECT);
    }
}
