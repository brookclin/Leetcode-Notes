// 
// Generated by fetch-leetcode-submission project on GitHub.
// https://github.com/gitzhou/fetch-leetcode-submission
// Contact Me: aaron67[AT]aaron67.cc
// 
// Plus One Linked List
// https://leetcode.com/problems/plus-one-linked-list/
// 

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        helper(head);
        if (head.val == 0) {
            return dummy;
        }
        return head;
    }
    private int helper(ListNode node) { // return carry
        if (node.next == null) {
            if (node.val == 9) {
                node.val = 0;
                return 1;
            } else {
                node.val += 1;
                return 0;
            }
        } else {
            int carry = helper(node.next);
            if (carry == 1) {
                if (node.val == 9) {
                    node.val = 0;
                    return 1;
                } else {
                    node.val += 1;
                    return 0;
                }
            }
        }
        return 0;
    }
}
