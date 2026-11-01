import java.util.*;

class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    grid[i][j] = 1;

                    boolean closed = true;

                    while (!q.isEmpty()) {
                        int[] cell = q.poll();
                        int r = cell[0];
                        int c = cell[1];

                        if (r == 0 || c == 0 || r == m - 1 || c == n - 1) {
                            closed = false;
                        }

                        for (int[] d : dirs) {
                            int nr = r + d[0];
                            int nc = c + d[1];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                                grid[nr][nc] = 1;
                                q.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    if (closed) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}