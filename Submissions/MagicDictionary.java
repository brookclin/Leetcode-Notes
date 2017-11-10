class MagicDictionary {

    /** Initialize your data structure here. */
    public Trie trie;
    public MagicDictionary() {
        trie = new Trie(new TrieNode());
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            trie.insert(word);
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return helper(word, 0, trie.root, false);
    }
    private boolean helper(String word, int index, TrieNode root, boolean isReplaced){
        if (index == word.length()) {
            if (root.isString == true && isReplaced == true) {
                return true;
            }
            return false;
        }
        char c = word.charAt(index);
        boolean result = false;
        if (root.map[c - 'a'] != null) {
            if (isReplaced == true) {
                result = result || helper(word, index + 1, root.map[c - 'a'], isReplaced);
            } else {
                for (int i = 0; i < 26; i++) {
                    if (root.map[i] != null) {
                        if (c - 'a' == i) {
                            result = result || helper(word, index + 1, root.map[i], isReplaced);
                        } else {
                            result = result || helper(word, index + 1, root.map[i], true);
                        }
                    }
                } 
            }
        } else {
            if (isReplaced == false) {
                for (int i = 0; i < 26; i++) {
                    if (root.map[i] != null) {
                        result = result || helper(word, index + 1, root.map[i], true);
                    }
                }
            } else {
                return false;
            }
        }
        return result;
    }
}
class TrieNode {
    // public HashMap<Character, TrieNode> map = new HashMap<>();
    public TrieNode[] map;
    public boolean isString;
    public TrieNode() {
        map = new TrieNode[26];
        isString = false;
    }
}
class Trie {
    public TrieNode root;
    public Trie(TrieNode node) {
        root = node;
    } 
    public void insert(String s) {
        char[] charArray = s.toCharArray();
        TrieNode now = root;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (now.map[c - 'a'] == null) { // WA: 错在这里 没有判断是否为空 每次都直接new一个新node 
                // 那么插入前缀一样的单词的时候会把原来的树统统破坏
                now.map[c - 'a'] = new TrieNode();
            }
            now = now.map[c - 'a'];
        }
        now.isString = true;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */