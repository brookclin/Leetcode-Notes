class Solution {
    public int[] toX = {0, 0, -1, 1};
    public int[] toY = {-1, 1, 0, 0};
    public int m;
    public int n;
    public List<String> findWords(char[][] board, String[] words) {
        List<String> results = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0) {
            return results;
        }
        m = board.length;
        n = board[0].length;
        Trie trie = new Trie(new TrieNode());
        for (String word : words) {
            trie.insert(word);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (trie.root.map.containsKey(board[i][j])) {
                    search(board, i, j, trie.root, results);
                }
            }
        }
        return results;
    }
    public void search(char[][] board, int x, int y, TrieNode root, List<String> results) {
        if (root.isString && !results.contains(root.s)) { // 报错 null pointer
            results.add(root.s);
        }
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] == '0' || root == null) { // x > m y > n
            return;
        }
        if (!root.map.containsKey(board[x][y])) {
            return;
        }
        char c = board[x][y];
        for (int k = 0; k < 4; k++) {
            board[x][y] = '0';
            search(board, x + toX[k], y + toY[k], root.map.get(c), results); // root.map.get(board[x][y])
            // search(board, x, y, root.map.get(c), results);
            board[x][y] = c;                     // board[x][y] = 'c';
        }
    } 
}
class TrieNode {
    public HashMap<Character, TrieNode> map = new HashMap<>();
    public boolean isString = false;
    public String s;
    public TrieNode() {
        s = "";
    }
}
class Trie {
    public TrieNode root;
    public Trie(TrieNode node) {
        root = node;
    }
    public void insert(String s) {
        TrieNode now = root;
        char[] charArray = s.toCharArray();
        int i = 0;
        for (i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!now.map.containsKey(c)) {
                now.map.put(c, new TrieNode());
            }
            now = now.map.get(c);
        }
        if (i == charArray.length) {
            now.isString = true;
            now.s = s;
        }
    }
}