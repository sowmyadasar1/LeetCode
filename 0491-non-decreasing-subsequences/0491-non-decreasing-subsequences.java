
class Solution {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(
            int[] nums,
            int start,
            List<Integer> path,
            List<List<Integer>> result) {

        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }

        // Avoid duplicate choices at this recursion level
        boolean[] used = new boolean[201];

        for (int i = start; i < nums.length; i++) {

            // Must be non-decreasing
            if (!path.isEmpty() &&
                nums[i] < path.get(path.size() - 1)) {
                continue;
            }

            // Same value at the same level -> duplicate
            int index = nums[i] + 100;

            if (used[index]) {
                continue;
            }

            used[index] = true;

            path.add(nums[i]);

            backtrack(nums, i + 1, path, result);

            path.remove(path.size() - 1);
        }
    }
}