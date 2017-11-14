class Solution {
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length < 2) {  // nums == length ????
            return 0;
        }
        int max = 0;
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            Set<Integer> set = new HashSet<>();
            mask = mask | (1 << i); // set from msb to i -> 1
            for (int num : nums) {
                if (!set.contains(num & mask)) {        // omitted !
                    set.add(num & mask);
                }
            }
            int tmp = max | (1 << i); // set ith bit to 1
            for (Integer num : set) {
                if (set.contains(tmp ^ num)) {
                    max = tmp;
                }
            }
        }
        return max;
    }
}