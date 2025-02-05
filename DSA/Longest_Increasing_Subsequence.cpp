/**
 * Function to compute the length of the Longest Increasing Subsequence (LIS) iteratively.
 *
 * @param a The input array.
 * @return The length of the Longest Increasing Subsequence.
 */
int len_lis(const vector<int> &a) {
    vector<int> dp;
    for (int i : a) {
        // Use lower_bound to find the position where 'i' can be placed
        int pos = lower_bound(dp.begin(), dp.end(), i) - dp.begin();
        
        // If pos is at the end of dp, we extend the sequence with a new element
        if (pos == dp.size()) {
            dp.push_back(i);
        } else {
            // Otherwise, we replace the existing value to maintain the smallest possible ending element
            dp[pos] = i;
        }
    }
    return dp.size();
}

/**
 * Function to generate the Longest Increasing Subsequence (LIS) from the input array.
 *
 * @param nums The input array.
 * @return A vector containing the Longest Increasing Subsequence.
 */
vector<int> generate_lis(const vector<int>& nums) {
    if (nums.empty()) return {};
    
    vector<int> sub;              // To store the indices of the subsequence
    vector<int> indices(nums.size()); // To store the indices of elements in the current subsequence
    vector<int> prev(nums.size(), -1); // To store the previous index in the LIS for backtracking
    
    for (size_t i = 0; i < nums.size(); ++i) {
        // Find the position where nums[i] can be placed using lower_bound
        auto it = lower_bound(sub.begin(), sub.end(), nums[i], 
                               [&](int index, int val) { return nums[index] < val; });
        size_t pos = distance(sub.begin(), it);

        // If the position is at the end, we add the new index
        if (it == sub.end()) {
            sub.push_back(i);
        } else {
            // Otherwise, replace the index in the subsequence
            *it = i;
        }

        indices[pos] = i;
        // Set the previous index for backtracking later
        if (pos > 0) {
            prev[i] = indices[pos - 1];
        }
    }

    // Reconstruct the LIS from the indices
    vector<int> lis;
    for (int i = indices[sub.size() - 1]; i != -1; i = prev[i]) {
        lis.push_back(nums[i]);
    }

    // Reverse to get the LIS in correct order
    reverse(lis.begin(), lis.end());
    return lis;
}/**
 * Function to compute the length of the Longest Increasing Subsequence (LIS) iteratively.
 *
 * @param a The input array.
 * @return The length of the Longest Increasing Subsequence.
 */
int len_lis(const vector<int> &a) {
    vector<int> dp;
    for (int i : a) {
        // Use lower_bound to find the position where 'i' can be placed
        int pos = lower_bound(dp.begin(), dp.end(), i) - dp.begin();
        
        // If pos is at the end of dp, we extend the sequence with a new element
        if (pos == dp.size()) {
            dp.push_back(i);
        } else {
            // Otherwise, we replace the existing value to maintain the smallest possible ending element
            dp[pos] = i;
        }
    }
    return dp.size();
}

/**
 * Function to generate the Longest Increasing Subsequence (LIS) from the input array.
 *
 * @param nums The input array.
 * @return A vector containing the Longest Increasing Subsequence.
 */
vector<int> generate_lis(const vector<int>& nums) {
    if (nums.empty()) return {};
    
    vector<int> sub;              // To store the indices of the subsequence
    vector<int> indices(nums.size()); // To store the indices of elements in the current subsequence
    vector<int> prev(nums.size(), -1); // To store the previous index in the LIS for backtracking
    
    for (size_t i = 0; i < nums.size(); ++i) {
        // Find the position where nums[i] can be placed using lower_bound
        auto it = lower_bound(sub.begin(), sub.end(), nums[i], 
                               [&](int index, int val) { return nums[index] < val; });
        size_t pos = distance(sub.begin(), it);

        // If the position is at the end, we add the new index
        if (it == sub.end()) {
            sub.push_back(i);
        } else {
            // Otherwise, replace the index in the subsequence
            *it = i;
        }

        indices[pos] = i;
        // Set the previous index for backtracking later
        if (pos > 0) {
            prev[i] = indices[pos - 1];
        }
    }

    // Reconstruct the LIS from the indices
    vector<int> lis;
    for (int i = indices[sub.size() - 1]; i != -1; i = prev[i]) {
        lis.push_back(nums[i]);
    }

    // Reverse to get the LIS in correct order
    reverse(lis.begin(), lis.end());
    return lis;
}