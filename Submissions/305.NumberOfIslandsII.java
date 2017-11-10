class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] toX = {0, 0, -1, 1};
        int[] toY = {-1, 1, 0, 0};
        List<Integer> results = new ArrayList<>();
        if (positions == null || positions.length == 0) {
            return results;
        }
        UnionFind u = new UnionFind(m * n);
        // init board
        int[][] board = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            board[x][y] = 1;
            u.setCount(u.getCount() + 1);
            for (int k = 0; k < 4; k++) {
                int next_x = x + toX[k];
                int next_y = y + toY[k];
                if (next_x < 0 || next_x >= m || next_y < 0 || next_y >= n ||
                   board[next_x][next_y] == 0) {
                    continue;
                }
                u.connect(x * n + y, next_x * n + next_y);  // next_x * n + y
            }
            results.add(u.getCount());
        }
        return results;
        
    }
}
class UnionFind {
    private int[] father;
    private int count = 0;
    public UnionFind(int count) {
        father = new int[count];
        for (int i = 0; i < count; i++) {
            father[i] = i;
        }
    }
    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    public void connect(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);
        if (root_x != root_y) {
            father[root_x] = root_y;
            count--;
        }
    }
    public int getCount() {
        return count;
    }
    public void setCount(int c) {
        count = c;
    }
    
}