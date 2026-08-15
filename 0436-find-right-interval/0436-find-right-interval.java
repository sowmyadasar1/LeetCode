class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] ans = new int[n];

        // start -> original index
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            map.put(intervals[i][0], i);
        }

        for (int i = 0; i < n; i++) {
            Integer nextStart = map.ceilingKey(intervals[i][1]);

            if (nextStart == null) {
                ans[i] = -1;
            } else {
                ans[i] = map.get(nextStart);
            }
        }

        return ans;
    }
}