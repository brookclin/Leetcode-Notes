// 
// Generated by fetch-leetcode-submission project on GitHub.
// https://github.com/gitzhou/fetch-leetcode-submission
// Contact Me: aaron67[AT]aaron67.cc
// 
// Reverse Words in a String II
// https://leetcode.com/problems/reverse-words-in-a-string-ii/
// 

class Solution {
    public void reverseWords(char[] str) {
        swap(str, 0, str.length - 1);
        int i = 0, j = 0;
        while (i < str.length) {
            while (j < str.length && str[j] != ' ') {
                j++;
            }
            swap(str, i, j - 1);
            i = j + 1;
            j = i;
        }
    }
    private void swap(char[] str, int start, int end) {
        while (start < end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
}
