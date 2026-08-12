class Solution {
    public Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }

    private Node build(int[][] grid, int r, int c, int len) {
        if (isLeaf(grid, r, c, len)) {
            return new Node(grid[r][c] == 1, true);
        }

        int half = len / 2;
        Node root = new Node(true, false);
        root.topLeft = build(grid, r, c, half);
        root.topRight = build(grid, r, c + half, half);
        root.bottomLeft = build(grid, r + half, c, half);
        root.bottomRight = build(grid, r + half, c + half, half);

        return root;
    }

    private boolean isLeaf(int[][] grid, int r, int c, int len) {
        int val = grid[r][c];
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (grid[i][j] != val) return false;
            }
        }
        return true;
    }
}