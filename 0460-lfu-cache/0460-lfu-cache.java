
class LFUCache {

    private class Node {
        int key;
        int value;
        int freq;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    private class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }

        Node removeLast() {
            if (size == 0) {
                return null;
            }

            Node node = tail.prev;
            remove(node);

            return node;
        }
    }

    private final int capacity;
    private int size;
    private int minFreq;

    private final Map<Integer, Node> nodes;
    private final Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;

        nodes = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!nodes.containsKey(key)) {
            return -1;
        }

        Node node = nodes.get(key);
        increaseFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (capacity == 0) {
            return;
        }

        // Key already exists
        if (nodes.containsKey(key)) {
            Node node = nodes.get(key);
            node.value = value;

            increaseFrequency(node);
            return;
        }

        // Cache is full
        if (size == capacity) {
            DoublyLinkedList list = freqMap.get(minFreq);

            Node removed = list.removeLast();

            nodes.remove(removed.key);
            size--;
        }

        Node node = new Node(key, value);

        nodes.put(key, node);

        freqMap
            .computeIfAbsent(1, k -> new DoublyLinkedList())
            .addFirst(node);

        minFreq = 1;
        size++;
    }

    private void increaseFrequency(Node node) {

        int oldFreq = node.freq;

        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.remove(node);

        // If this was the last node at minFreq
        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.freq++;

        freqMap
            .computeIfAbsent(node.freq, k -> new DoublyLinkedList())
            .addFirst(node);
    }
}