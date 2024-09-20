public class SocialNetworkConnectivity {
    public static int earliestTimeToConnectAll(int n, int[][] logs) {
            UnionFind unionFind = new UnionFind(n);

            for (int[] log : logs) {
                int timestamp = log[0];
                int p = log[1];
                int q = log[2];

                System.out.println("Processing log: " + timestamp + " connecting " + p + " and " + q);
                unionFind.union(p, q);

                if (unionFind.connected()) {
                    System.out.println("All members connected at timestamp: " + timestamp);
                    return timestamp;
                }
            }
            return -1; // if all the members are not connected
    }

    public static void main(String[] args) {
        int[][] logs = {
                {20190101, 0, 1},
                {20190104, 3, 4},
                {20190107, 2, 3},
                {20190211, 1, 5},
                {20190224, 2, 4},
                {20190301, 0, 3},
                {20190312, 1, 2}
        };
        int n = 6;
        int result = earliestTimeToConnectAll(n, logs);
        System.out.println("The earliest timestamp when all members are connected: " + result);
    }
}
