class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new int[2];
        }
        int n = edges.length;
        int[] can1 = new int[]{-1, -1};
        int[] can2 = new int[]{-1, -1};
        int[] parents = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (parents[v] == 0) {
                parents[v] = u;
            } else {
                can1 = new int[]{parents[v], v};    //parent
                can2 = new int[]{u, v};     // new int[]
                edges[i][1] = 0;
            }
        }
        UnionFind union = new UnionFind(n + 1);     // Unionfind
        for (int i = 0; i < n; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (v == 0) {
                continue; // temporarily removed edge 
            }
            if (union.isUnion(u, v)) {
                if (can1[0] == -1) {
                    return new int[]{u, v};
                }
                return can1;
            }
            union.connect(u, v);
        }
        return can2;
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
    public void connect(int parent, int child) {
        int root_p = find(parent);
        int root_c = find(child);
        if (root_p != root_c) {
            father[root_c] = root_p;
        }
    }
    public boolean isUnion(int parent, int child) {
        return find(parent) == find(child);
    }
}