class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);

                for (Node child : node.children) {
                    if (child != null) queue.offer(child);
                }
            }

            ans.add(level);
        }

        return ans;
    }
}