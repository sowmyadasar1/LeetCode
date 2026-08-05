class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int stone : stones) {
            map.put(stone, new HashSet<>());
        }

        map.get(0).add(1);

        int last = stones[stones.length - 1];

        for (int stone : stones) {
            for (int jump : map.get(stone)) {
                int reach = stone + jump;

                if (reach == last) return true;

                if (map.containsKey(reach)) {
                    if (jump - 1 > 0) map.get(reach).add(jump - 1);
                    map.get(reach).add(jump);
                    map.get(reach).add(jump + 1);
                }
            }
        }

        return false;
    }
}