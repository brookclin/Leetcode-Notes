class Solution {
    private int m;
    private int n;
    private boolean[][] hash;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[] toX = new int[]{0, 0, -1, 1};
        int[] toY = new int[]{-1, 1, 0, 0};
        m = maze.length;
        n = maze[0].length;
        hash = new boolean[m][n];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start[0], start[1]));
        hash[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;
            for (int k = 0; k < 4; k++) {
                int next_x = x;
                int next_y = y;
                while (next_x >= 0 && next_x < m && next_y >= 0 && next_y < n 
                       && maze[next_x][next_y] == 0) {
                    next_x = next_x + toX[k];
                    next_y = next_y + toY[k];
                }
                next_x = next_x - toX[k];         // stop point
                next_y = next_y - toY[k];
                if (hash[next_x][next_y]) {
                    continue;
                }
                hash[next_x][next_y] = true;
                if (next_x == destination[0] && next_y == destination[1]) {
                    return true;
                }
                queue.offer(new Point(next_x, next_y));
            }
        }
        return false;
    }
}
class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}