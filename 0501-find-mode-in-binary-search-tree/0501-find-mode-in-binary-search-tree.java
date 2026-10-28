
class Solution {

    private Integer prev = null;
    private int currentCount = 0;
    private int maxCount = 0;

    private List<Integer> result = new ArrayList<>();

    public int[] findMode(TreeNode root) {

        inorder(root);

        int[] ans = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    private void inorder(TreeNode node) {

        if (node == null) {
            return;
        }

        inorder(node.left);

        // Process current value
        if (prev != null && prev == node.val) {
            currentCount++;
        } else {
            currentCount = 1;
        }

        if (currentCount > maxCount) {
            maxCount = currentCount;
            result.clear();
            result.add(node.val);

        } else if (currentCount == maxCount) {
            result.add(node.val);
        }

        prev = node.val;

        inorder(node.right);
    }
}