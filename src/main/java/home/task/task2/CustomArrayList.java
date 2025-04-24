package home.task.task2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class CustomArrayList<E> {
    private int size;
    private Object[] elementData;
    private int length = 10;

    public CustomArrayList() {
        this.elementData = new Object[length];
    }

    public int size() {
        return size;
    }

    public boolean add(E element) {
        if (size == elementData.length)
            elementData = extension();
        elementData[size] = element;
        size++;
        return true;
    }

    private Object[] extension() {
        length = length + length/2;
        return elementData = Arrays.copyOf(elementData, length);
    }

    public boolean addAll(Collection<? extends E> collection) {
        Object[] array = collection.toArray();

        if (array.length == 0)
            return false;

        int arraySize = 0;

        for (Object object : array) {
            arraySize++;
            if ((size + arraySize) == elementData.length)
                elementData = extension();

            elementData[size - 1 + arraySize] = object;
        }

        size += arraySize;
        return true;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elementData[index];
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        Object[] newElementData = new Object[length];
        E removedElement = (E) elementData[index];

        int shift = 0;
        for (int i = 0; i < size; i++) {
            if (i == index)
                shift = 1;
            newElementData[i] = elementData[i + shift];
        }

        size--;
        elementData = newElementData;
        return removedElement;
    }
}
