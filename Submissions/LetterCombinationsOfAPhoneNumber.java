class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits == null || digits.length() == 0) { // digits.length
            return results;
        }
        String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        int[] int_digits = new int[digits.length()];
        for (int i = 0; i < int_digits.length; i++) {
            int_digits[i] = digits.charAt(i) - '0';
        }
        helper(0, int_digits, results, new StringBuilder(), dict);
        return results;
    }
    private void helper(int index, int[] digits, 
                        List<String> results, StringBuilder sb, String[] dict) {
        if (sb.length() == digits.length) {
            results.add(sb.toString());
            return;
        }
        int digit = digits[index];
        String candidates = dict[digit];
        for (int i = 0; i < candidates.length(); i++) {
            sb.append(candidates.charAt(i));
            helper(index + 1, digits, results, sb, dict);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}