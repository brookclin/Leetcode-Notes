class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new int[0];
        }
        UnionFind union = new UnionFind(edges.length + 1); // #edges == #vertices, [0] ignored
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (union.isUnion(u, v)) {
                return new int[]{u, v};
            }
            union.connect(u, v);
        }
        return new int[0];
    }
}
class UnionFind {
    private int[] father;
    public UnionFind(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }
    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    public void connect(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
        }
    }
    public boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }
    
}