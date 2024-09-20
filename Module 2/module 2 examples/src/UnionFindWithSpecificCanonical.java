public class UnionFindWithSpecificCanonical {
    public static void main(String[] args) {
        UnionFindWithMax uf = new UnionFindWithMax(10);

        // perform union connection between {1,2,6,9}
        uf.union(1, 2);
        uf.union(2, 6);
        uf.union(6, 9);

        // now test the find() method on each of the elements in {1, 2, 6, 9}
        System.out.println("find(1) = " + uf.find(1)); // Should return 9
        System.out.println("find(2) = " + uf.find(2)); // Should return 9
        System.out.println("find(6) = " + uf.find(6)); // Should return 9
        System.out.println("find(9) = " + uf.find(9)); // Should return 9

        // check if 1 and 9 are connected (they should be)
        System.out.println("Are 1 and 9 connected? " + uf.connected(1, 9)); // Should return true

        // check if 1 and 3 are connected (they should not be)
        System.out.println("Are 1 and 3 connected? " + uf.connected(1, 3)); // Should return false
    }
}
