import java.util.Arrays;

public class UnionFind {

    //索引表示节点 0 至 n-1
    public int[] id;


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        id = new int[n];
        /*for (int i = 0; i < id.length; i++) {
            //用负数表示根节点，用值表示当前树中的节点数. -3 表示该根节点所在的树有3个节点
            id[i] = -1;
        }*/
        Arrays.fill(id, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex > id.length - 1) {
            throw new IllegalArgumentException(vertex + " is not a valid index!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return -id[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return id[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int v1Root = find(v1);
        int v2Root = find(v2);
        if (v1Root != v2Root) {
            int v1TreeSize = -id[v1Root];
            int v2TreeSize = -id[v2Root];
            if (v1TreeSize > v2TreeSize) {
                id[v2Root] = v1Root;
                id[v1Root] = - (v1TreeSize + v2TreeSize);
            } else {
                id[v1Root] = v2Root;
                id[v2Root] = - (v1TreeSize + v2TreeSize);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while (id[root] >= 0) {
            root = id[root];
        }
        //路径压缩
        while (vertex != root) {
            id[vertex] = root;
            vertex = parent(vertex);
        }
        return root;
    }

    public void resetIdArray(int[] id) {
        this.id = id;
    }
}
