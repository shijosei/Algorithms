import java.util.Arrays;

public class UnionFind {
    private int[] parent;
    private int[] size;
    private int components;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        components = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // find the root of a node with path compression
    public int find(int p) {
        System.out.println("Finding root of " + p);
        if (p != parent[p]) {
            parent[p] = find(parent[p]);  // Path compression
        }
        System.out.println("Root of " + p + " is " + parent[p]);
        return parent[p];
    }


    // union two roots
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        System.out.println("Root of " + p + " is " + pRoot + ", Root of " + q + " is " + qRoot);

        if (pRoot != qRoot) {
            if (size[pRoot] < size[qRoot]) {
                parent[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }
            else {
                parent[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }

            components--; // decrease number of components
        }
        System.out.println("Number of components: " + components);
        System.out.println("Parent array after union: " + Arrays.toString(parent));
    }

    // check if all components are connected
    public boolean connected() {
        return components == 1;
    }
}



