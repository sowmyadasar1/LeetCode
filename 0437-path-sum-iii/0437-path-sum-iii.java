class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);

        return dfs(root, 0L, targetSum, prefix);
    }

    private int dfs(TreeNode node, long sum, int targetSum,
                    Map<Long, Integer> prefix) {

        if (node == null) {
            return 0;
        }

        sum += node.val;

        // Number of paths ending here with targetSum
        int count = prefix.getOrDefault(sum - targetSum, 0);

        // Add current prefix sum
        prefix.put(sum, prefix.getOrDefault(sum, 0) + 1);

        count += dfs(node.left, sum, targetSum, prefix);
        count += dfs(node.right, sum, targetSum, prefix);

        // Backtrack: remove current prefix sum
        prefix.put(sum, prefix.get(sum) - 1);

        return count;
    }
}