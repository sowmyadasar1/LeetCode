import java.util.*;

class Solution {

    private PriorityQueue<Integer> small =
        new PriorityQueue<>(Collections.reverseOrder());

    private PriorityQueue<Integer> large =
        new PriorityQueue<>();

    private Map<Integer, Integer> delayed =
        new HashMap<>();

    private int smallSize = 0;
    private int largeSize = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        double[] result = new double[n - k + 1];

        for (int i = 0; i < k; i++) {
            add(nums[i]);
        }

        result[0] = getMedian(k);

        for (int i = k; i < n; i++) {

            add(nums[i]);

            remove(nums[i - k]);

            result[i - k + 1] = getMedian(k);
        }

        return result;
    }

    private void add(int num) {

        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            smallSize++;
        } else {
            large.offer(num);
            largeSize++;
        }

        rebalance();
    }

    private void remove(int num) {

        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        if (!small.isEmpty() && num <= small.peek()) {
            smallSize--;
        } else {
            largeSize--;
        }

        prune(small);
        prune(large);

        rebalance();
    }

    private void rebalance() {

        // small can have at most one more element
        if (smallSize > largeSize + 1) {

            large.offer(small.poll());

            smallSize--;
            largeSize++;

            prune(small);

        } else if (smallSize < largeSize) {

            small.offer(large.poll());

            largeSize--;
            smallSize++;

            prune(large);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {

        while (!heap.isEmpty()) {

            int num = heap.peek();

            if (!delayed.containsKey(num)) {
                break;
            }

            int count = delayed.get(num);

            if (count == 1) {
                delayed.remove(num);
            } else {
                delayed.put(num, count - 1);
            }

            heap.poll();
        }
    }

    private double getMedian(int k) {

        if ((k & 1) == 1) {
            return (double) small.peek();
        }

        return ((double) small.peek() +
                (double) large.peek()) / 2.0;
    }
}