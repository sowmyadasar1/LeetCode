class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(endGene)) return -1;

        char[] genes = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String curr = queue.poll();
                if (curr.equals(endGene)) return steps;

                char[] arr = curr.toCharArray();

                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];

                    for (char c : genes) {
                        if (c == old) continue;
                        arr[i] = c;

                        String next = new String(arr);
                        if (set.contains(next) && visited.add(next)) {
                            queue.offer(next);
                        }
                    }

                    arr[i] = old;
                }
            }

            steps++;
        }

        return -1;
    }
}