class NumArray {
    private SegmentTreeNode root;
    public NumArray(int[] nums) {
        int n = nums.length - 1;
        root = buildTree(nums, 0, n);
    }
    
    public void update(int i, int val) {
        updateHelper(root, i, val);
    }
    public void updateHelper(SegmentTreeNode node, int i, int val) {
        if (node.start == node.end) {
            node.sum = val;
            return;
        }
        int mid = (node.start + node.end) / 2;
        if (node.start <= i && i <= mid) {
            updateHelper(node.left, i, val);
        }
        if (mid < i && i <= node.end) {
            updateHelper(node.right, i, val);
        }
        node.sum = node.left.sum + node.right.sum;
    }
    
    public int sumRange(int i, int j) {
        return query(root, i, j);
    }
    private int query(SegmentTreeNode node, int start, int end) {
        // 忘了加结束条件
        if (node.start == start && node.end == end) {
            return node.sum;
        }
        if (start > end) {
            return 0;
        }
        int mid = (node.start + node.end) / 2;
        int leftsum = 0;
        int rightsum = 0;
        if (start <= mid) {
            if (mid < end) { // 分裂
                leftsum = query(node.left, start, mid);
            } else { // 包含
                leftsum = query(node.left, start, end);
            }
        }
        if (mid < end) {
            if (start <= mid) {
                rightsum = query(node.right, mid + 1, end);
            } else {
                rightsum = query(node.right, start, end);
            }
        }
        return leftsum + rightsum;
    }
    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end, nums[start]);
        if (start == end) {
            return root;
        }
        int sum = 0;
        int mid = (root.start + root.end) / 2;
        root.left = buildTree(nums, start, mid);
        root.right = buildTree(nums, mid + 1, end);
        if (root.left != null) {
            sum += root.left.sum;
        }
        if (root.right != null) {
            sum += root.right.sum;
        }
        root.sum = sum;
        return root;
    }
}
class SegmentTreeNode {
    public int start, end, sum;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end, int sum) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        left = null;
        right = null;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */