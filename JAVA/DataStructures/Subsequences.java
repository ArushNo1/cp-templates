import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

}
