public class Codec {

    // Serialize
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);

        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append(node.val).append(",");

        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    // Deserialize
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] values = data.split(",");
        int[] index = {0};

        return build(values, index, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private TreeNode build(
            String[] values,
            int[] index,
            long lower,
            long upper) {

        if (index[0] >= values.length) {
            return null;
        }

        long value = Long.parseLong(values[index[0]]);

        // Value doesn't belong to this subtree
        if (value <= lower || value >= upper) {
            return null;
        }

        index[0]++;

        TreeNode node = new TreeNode((int) value);

        node.left = build(values, index, lower, value);
        node.right = build(values, index, value, upper);

        return node;
    }
}