
public class Subsequences {

    /**
     * Computes the Longest Common Subsequence (LCS) between two strings
     * iteratively using dynamic programming. This approach avoids recursion by
     * filling a 2D DP table.
     *
     * @param X The first string.
     * @param Y The second string.
     * @param m The length of the first string X.
     * @param n The length of the second string Y.
     * @return The length of the Longest Common Subsequence (LCS).
     */
    public static int lcs(String X, String Y, int m, int n) {
        // Initialize a 2D dp table where dp[i][j] represents the LCS length of substrings X[0..i-1] and Y[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Fill the dp table iteratively
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If the characters match, increment the LCS length from the previous diagonal
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // If they don't match, take the maximum value from the left or top cell
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The bottom-right corner contains the length of the LCS of X and Y
        return dp[m][n];
    }

    /**
     * Function to compute the length of the Longest Increasing Subsequence
     * (LIS).
     *
     * @param a The input array.
     * @return The length of the Longest Increasing Subsequence.
     */
    public static int lenLIS(List<Integer> a) {
        List<Integer> dp = new ArrayList<>();

        for (int i : a) {
            // Use binary search to find the position where 'i' can be placed
            int pos = Collections.binarySearch(dp, i);

            // If 'i' is not found, binarySearch returns a negative value, which indicates the position to insert
            if (pos < 0) {
                pos = -(pos + 1);
            }

            // If pos is at the end of dp, we extend the sequence with a new element
            if (pos == dp.size()) {
                dp.add(i);
            } else {
                // Otherwise, we replace the existing value to maintain the smallest possible ending element
                dp.set(pos, i);
            }
        }

        return dp.size();
    }

    /**
     * Function to generate the Longest Increasing Subsequence (LIS) from the
     * input array.
     *
     * @param nums The input array.
     * @return A List containing the Longest Increasing Subsequence.
     */
    public static List<Integer> generateLIS(List<Integer> nums) {
        if (nums.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> sub = new ArrayList<>(); // To store the indices of the subsequence
        int[] indices = new int[nums.size()];  // To store the indices of elements in the current subsequence
        int[] prev = new int[nums.size()];     // To store the previous index in the LIS for backtracking

        Arrays.fill(prev, -1); // Initialize the prev array

        for (int i = 0; i < nums.size(); ++i) {
            // Find the position where nums[i] can be placed using binary search
            int pos = Collections.binarySearch(sub, nums.get(i));

            // If pos is negative, find the insertion position
            if (pos < 0) {
                pos = -(pos + 1);
            }

            // If the position is at the end, add the index
            if (pos == sub.size()) {
                sub.add(i);
            } else {
                // Otherwise, replace the index in the subsequence
                sub.set(pos, i);
            }

            indices[pos] = i;
            // Set the previous index for backtracking later
            if (pos > 0) {
                prev[i] = indices[pos - 1];
            }
        }

        // Reconstruct the LIS from the indices
        List<Integer> lis = new ArrayList<>();
        for (int i = indices[sub.size() - 1]; i != -1; i = prev[i]) {
            lis.add(nums.get(i));
        }

        // Reverse to get the LIS in correct order
        Collections.reverse(lis);
        return lis;
    }

}
