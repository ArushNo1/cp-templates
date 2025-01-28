int lcs(string X, string Y, int m, int n) {
    // Initialize a 2D dp table with dimensions (m+1) x (n+1)
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    
    // Iterate over each character in both strings
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            // If the characters match, add 1 to the result from previous diagonal
            if (X[i - 1] == Y[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                // Otherwise, take the maximum between the left and top cells
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    
    // Return the value in the bottom-right corner, which holds the LCS length
    return dp[m][n];
}