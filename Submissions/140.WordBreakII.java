class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<String, List<String>> map = new HashMap<>();
        return helper(s, wordDict, map);
    }
    private List<String> helper(String s, List<String> dict, 
                                HashMap<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> results = new ArrayList<>();
        if (s.length() == 0) {
            results.add("");
            return results;
        }
        for (String str : dict) {
            if (s.startsWith(str)) {
                List<String> subres = helper(s.substring(str.length()), dict, map);
                for (String sub : subres) {
                    results.add(str + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        if (!map.containsKey(s)) {
            map.put(s, results);
        }
        
        return results;
    }
}