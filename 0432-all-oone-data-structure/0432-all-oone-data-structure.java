class AllOne {

    private class Node {
        int count;
        Set<String> keys = new HashSet<>();
        Node prev, next;

        Node(int count) {
            this.count = count;
        }
    }

    private Map<String, Node> map;
    private Node head, tail;

    public AllOne() {
        map = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if (!map.containsKey(key)) {
            Node node;
            if (head.next != tail && head.next.count == 1) {
                node = head.next;
            } else {
                node = new Node(1);
                insertAfter(head, node);
            }
            node.keys.add(key);
            map.put(key, node);
        } else {
            Node cur = map.get(key);
            Node next = cur.next;

            if (next == tail || next.count != cur.count + 1) {
                next = new Node(cur.count + 1);
                insertAfter(cur, next);
            }

            next.keys.add(key);
            map.put(key, next);

            cur.keys.remove(key);
            if (cur.keys.isEmpty()) remove(cur);
        }
    }

    public void dec(String key) {
        Node cur = map.get(key);

        if (cur.count == 1) {
            map.remove(key);
        } else {
            Node prev = cur.prev;

            if (prev == head || prev.count != cur.count - 1) {
                prev = new Node(cur.count - 1);
                insertAfter(cur.prev, prev);
            }

            prev.keys.add(key);
            map.put(key, prev);
        }

        cur.keys.remove(key);
        if (cur.keys.isEmpty()) remove(cur);
    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    private void insertAfter(Node prev, Node node) {
        node.next = prev.next;
        node.prev = prev;
        prev.next.prev = node;
        prev.next = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}