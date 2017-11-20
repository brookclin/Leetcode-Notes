class Solution {
    private int maxLen = 0;
    private int[] toX = {0, 0, -1, 1};
    private int[] toY = {-1, 1, 0, 0};
    private int m;
    private int n;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        m = matrix.length;
        n = matrix[0].length;
        int[][] lens = new int[m][n];
        boolean[][] hash = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                helper(i, j, matrix, lens, hash);
            }
        }
        return maxLen;
    }
    private int helper(int x, int y,
                        int[][] matrix, int[][] lens,
                        boolean[][] hash) {
        if (lens[x][y] != 0) {
            return lens[x][y];
        }
        
        hash[x][y] = true;
        int max = 0;
        for (int k = 0; k < 4; k++) {
            int nextX = x + toX[k];
            int nextY = y + toY[k];
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !hash[nextX][nextY] 
                && matrix[nextX][nextY] > matrix[x][y]) {
                int candidate = helper(nextX, nextY, matrix, lens, hash);
                max = Math.max(max, candidate);
            }
        }
        lens[x][y] = 1 + max;
        maxLen = Math.max(maxLen, lens[x][y]);
        hash[x][y] = false;
        return lens[x][y];
    }
}