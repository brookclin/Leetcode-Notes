// 
// Generated by fetch-leetcode-submission project on GitHub.
// https://github.com/gitzhou/fetch-leetcode-submission
// Contact Me: aaron67[AT]aaron67.cc
// 
// Add and Search Word - Data structure design
// https://leetcode.com/problems/add-and-search-word-data-structure-design/
// 


class WordDictionary {
    class TrieNode {
        public boolean isEnd;
        private char c;
        public HashMap<Character, TrieNode> children = new HashMap<>();
        public TrieNode() {

        }
        public TrieNode(char c) {
            this.c = c;
        }
}
    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!p.children.containsKey(c)) {
                p.children.put(c, new TrieNode(c));
            }
            p = p.children.get(c);
        }
        p.isEnd = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return find(word, 0, root);
    }
    private boolean find(String word, int index, TrieNode now) {
        if (index == word.length()) {
            return now.isEnd;
        }
        char c = word.charAt(index);
        if (now.children.containsKey(c)) {
            return find(word, index + 1, now.children.get(c));
        } else if (c == '.') {
            for (Map.Entry<Character, TrieNode> child : now.children.entrySet()) {
                if (find(word, index + 1, child.getValue())) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
