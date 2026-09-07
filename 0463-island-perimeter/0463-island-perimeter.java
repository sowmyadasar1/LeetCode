class Solution {
    public int islandPerimeter(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (grid[r][c] == 0) {
                    continue;
                }

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nr >= rows ||
                        nc < 0 || nc >= cols ||
                        grid[nr][nc] == 0) {

                        perimeter++;
                    }
                }
            }
        }

        return perimeter;
    }
}