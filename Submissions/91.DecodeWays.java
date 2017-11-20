class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;           // WA: 1
        }
        int n = s.length();
        int[] opt = new int[n + 1];
        opt[0] = 1;
        opt[1] = 1;
        for (int i = 2; i <= n; i++) {
            opt[i] = 0;
            if (s.charAt(i - 1) != '0') {
                opt[i] += opt[i - 1];
            }
            int value = Integer.valueOf(s.substring(i-2, i));
            if (value <= 26 && value >= 10) {
                opt[i] += opt[i - 2];
            }
        }
        return opt[n];
    }
}