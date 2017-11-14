class Solution {
    private int m;
    private int n;
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[] toX = new int[]{0, 0, -1, 1};
        int[] toY = new int[]{-1, 1, 0, 0};
        m = maze.length;
        n = maze[0].length;
        PriorityQueue<Point> queue = new PriorityQueue<>(1, new Comparator<Point>(){
            public int compare(Point a, Point b) {
                return a.len - b.len;
            }
        });
        queue.offer(new Point(start[0], start[1], 0));
        int[][] length = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                length[i][j] = Integer.MAX_VALUE;
            }
        }
        // length[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;
            if (length[x][y] <= p.len) {
                continue;
            }
            length[x][y] = p.len;
            for (int k = 0; k < 4; k++) {
                int next_x = x;
                int next_y = y;
                int len = p.len;
                while (next_x >= 0 && next_x < m && next_y >= 0 && next_y < n
                      && maze[next_x][next_y] == 0) {
                    next_x += toX[k];
                    next_y += toY[k];
                    len++;
                }
                next_x -= toX[k];
                next_y -= toY[k];
                len--;
                // length[next_x][next_y] = len;
                queue.offer(new Point(next_x, next_y, len));
            }
        }
        return length[destination[0]][destination[1]] == Integer.MAX_VALUE ? 
            -1 : length[destination[0]][destination[1]];
    }
}
class Point {
    public int x;
    public int y;
    public int len;
    public Point(int x, int y, int len) {
        this.x = x;
        this.y = y;
        this.len = len;
    }
}