import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * The Percolation class that models a percolation system.
 * @author Aytan Abdullayeva
 */
public class Percolation {
    private boolean[][] grid; // grid[a][b] = site at row a, column b
    private WeightedQuickUnionUF uf;
    private int len; // length of the grid
    private int top; // virtual top
    private int bottom; // virtual bottom
    private int openSites; // count of open sites

    /**
     * Creates n-by-n grid, with all sites initially blocked
     * @param n
     */
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Grid must be greater then 0.\n");

        this.len = n;
        this.grid = new boolean[n][n];
        this.uf = new WeightedQuickUnionUF(n * n + 2); // n * n sites + 2 virtual sites
        this.top = n * n; // virtual top site
        this.bottom = n * n + 1; // virtual bottom site
        this.openSites = 0;

    }

    /**
     * Opens the site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        validate(row, col);

        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true; // open the sites
            openSites++;

            int currentIndex = getIndex(row, col);

            // connect to open sites
            if (row == 1) {
                uf.union(currentIndex, top); // connects to the virtual top row
            }
            if (row == len) {
                uf.union(currentIndex, bottom); // connects to the virtual bottom row
            }
            if (row > 1 && isOpen(row - 1, col)) { // connects to the top neighbor
                uf.union(currentIndex, getIndex(row - 1, col));
            }
            if (row < len && isOpen(row + 1, col)) { // connects to the bottom neighbor
                uf.union(currentIndex, getIndex(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) { // connects to the left neighbor
                uf.union(currentIndex, getIndex(row, col - 1));
            }
            if (col < len && isOpen(row, col + 1)) { // connects to the right neighbor
                uf.union(currentIndex, getIndex(row, col + 1));
            }
        }
    }

    /**
     *  Helper method to convert 2D (row, col) to 1D index in union-find structure
     * @param row
     * @param col
     * @return
     */
    private int getIndex(int row, int col) {
        return (row - 1) * len + (col - 1);
    }

    /**,
     * Is the site (row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    /**
     * Is the site (row, col) full?
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.find(getIndex(row, col)) == uf.find(top);
    }

    /**
     * Returns the number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * Does the system percolate?
     * @return
     */
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom);
    }

    /**
     * Helper method to validate the row and col indices
     * @param row
     * @param col
     */
    private void validate(int row, int col) {
        if (row < 1 || row > len || col < 1 || col > len) {
            throw new IllegalArgumentException("Invalid row: " + row + ", col: " + col + ".");
        }
    }

    /**
     * Test client (optional)
     */
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(5, 1);
        System.out.println("Is (1, 1) open? " + percolation.isOpen(1, 1));
        System.out.println("Is (2, 1) open? " + percolation.isOpen(2, 1));
        System.out.println("Is (3, 1) open? " + percolation.isOpen(3, 1));
        System.out.println("Is (4, 1) open? " + percolation.isOpen(4, 1));
        System.out.println("Is (5, 1) open? " + percolation.isOpen(5, 1));

        System.out.println("Is (1, 1) full? " + percolation.isFull(1, 1));
        System.out.println("Is (2, 1) full? " + percolation.isFull(2, 1));
        System.out.println("Is (3, 1) full? " + percolation.isFull(3, 1));
        System.out.println("Is (4, 1) full? " + percolation.isFull(4, 1));
        System.out.println("Is (5, 1) full? " + percolation.isFull(5, 1));

        System.out.println("Number of open sites: " + percolation.numberOfOpenSites());
        System.out.println("Does the system percolate? " + percolation.percolates());
    }
}