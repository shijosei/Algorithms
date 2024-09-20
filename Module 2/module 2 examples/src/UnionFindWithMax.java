public class UnionFindWithMax {
    private int[] parent;  // parent[i] points to the parent of i
    private int[] size;    // size[i] stores the size of the tree for which i is the root
    private int[] max;     // max[i] stores the maximum element in the component for which i is the root

    // Constructor to initialize Union-Find structure with N elements
    public UnionFindWithMax(int N) {
        parent = new int[N];
        size = new int[N];
        max = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;  // Initially, each element is its own parent
            size[i] = 1;    // Each component is of size 1 initially
            max[i] = i;     // Initially, the max element in the component is the element itself
        }
    }

    // find the root of a node with path compression
    public int findRoot(int p) {
        System.out.println("Finding root of " + p);
        if (p != parent[p]) {
            parent[p] = findRoot(parent[p]);  // Path compression
        }
        System.out.println("Root of " + p + " is " + parent[p]);
        return parent[p];
    }

    // Find the largest element in the component containing p
    public int find(int p) {
        int root = findRoot(p);  // Find the root of p
        return max[root];     // Return the maximum element for this component
    }

    // Union two elements p and q
    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        if (rootP == rootQ) return;  // They are already in the same component

        // Union by size: smaller tree points to the larger tree
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
            max[rootQ] = Math.max(max[rootP], max[rootQ]);  // Update max for rootQ
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            max[rootP] = Math.max(max[rootP], max[rootQ]);  // Update max for rootP
        }
    }

    // Check if p and q are in the same component
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }
}

