class Solution {
    private int[] toX = {0, 0, -1, 1};
    private int[] toY = {-1, 1, 0, 0};
    private int n;
    private int m;
    private boolean[][] visited; // backtrack中的hash
    public boolean exist(char[][] board, String word) {
        n = board.length;
        m = board[0].length;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (word.charAt(0) == board[i][j] && helper(board, i, j, 0, word)) { 
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, int x, int y, int start, String word) {
        if (start == word.length()) {
            return true;
        }
        if (x < 0 || x >= n || y < 0 || y >= m || 
            visited[x][y] || board[x][y] != word.charAt(start)) {
            return false;
        }
        visited[x][y] = true;
        boolean result = false;
        for (int k = 0; k < 4; k++) {
            int nextX = x + toX[k];
            int nextY = y + toY[k];
            result = result || helper(board, nextX, nextY, start + 1, word);
        }
        visited[x][y] = false;
        return result;
        
    }
}