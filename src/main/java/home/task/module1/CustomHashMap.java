package home.task.module1;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K,V>[] table;
    private int threshold;
    private int size;
    private final float loadFactor;

    public CustomHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        this.loadFactor = loadFactor;
        this.table = new Node[initialCapacity];
        this.threshold = (int) (initialCapacity * loadFactor);
    }

    public CustomHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public CustomHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public V put(K key, V value) {
        int hash = hash(key);
        int index = defineIndex(hash, table.length);

        if (table[index] == null) {
            table[index] = add(hash, key, value, null);
        } else {
            for (Node<K, V> node = table[index]; node != null; node = node.next) {
                if (node.hash == hash && (node.key == key || key.equals(node.key))) {
                    V oldValue = node.value;
                    node.value = value;
                    return oldValue;
                }

                if (node.next == null) {
                    node.next = add(hash, key, value, null);
                    break;
                }
            }
        }

        return null;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = defineIndex(hash, table.length);

        for (Node<K, V> node = table[index]; node != null; node = node.next) {
            if (node.hash == hash && (node.key == key || key.equals(node.key))) {
                return node.value;
            }
        }

        return null;
    }

    public V remove(K key) {
        int hash = hash(key);
        int index = defineIndex(hash, table.length);
        Node<K, V> firstNode = table[index];

        if (firstNode == null) {
            return null;
        }

        if (firstNode.hash == hash && (firstNode.key == key || key.equals(firstNode.key))) {
            table[index] = firstNode.next;
            --size;

            return firstNode.value;
        }

        Node<K, V> prev = table[index];

        for (Node<K, V> node = table[index].next; node != null; node = node.next) {
            if (node.hash == hash && (node.key == key || key.equals(node.key))) {
                prev.next = node.next;
                --size;

                return node.value;
            }

            prev = node;
        }

        return null;
    }

    private Node<K, V> add(int hash, K key, V value, Node<K, V> next) {
        if (++size > threshold) {
            resize();
        }

        return new Node<>(hash, key, value, next);
    }

    private void resize() {
        Node<K,V>[] oldTable = table;
        int oldCap = oldTable.length;

        if (oldCap >= Integer.MAX_VALUE) {
            threshold = Integer.MAX_VALUE;

            return;
        }

        int newCapacity = table.length * 2;
        Node<K, V>[] newTable = new Node[newCapacity];

        for (Node<K, V> node : table) {
            while (node != null) {
                Node<K, V> next = node.next;
                int index = defineIndex(node.hash, newTable.length);
                node.next = newTable[index];
                newTable[index] = node;
                node = next;
            }
        }

        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    private int defineIndex(int hash, int tableLength) {
        return hash & (tableLength - 1);
    }

    private int hash(K key) {
        if (key == null) return 0;
        int h = key.hashCode();

        return h ^ (h >>> 16);
    }

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
