int len_lis(const vector<int> &a) {
	vector<int> dp;
	for (int i : a) {
		int pos = lower_bound(dp.begin(), dp.end(), i) - dp.begin();
		if (pos == dp.size()) {
			// we can have a new, longer increasing subsequence!
			dp.push_back(i);
		} else {
			// oh ok, at least we can make the ending element smaller
			dp[pos] = i;
		}
	}
	return dp.size();
}

std::vector<int> generate_lis(const std::vector<int>& nums) {
    if (nums.empty()) return {};
    
    std::vector<int> sub;
    std::vector<int> indices(nums.size());
    std::vector<int> prev(nums.size(), -1);

    for (size_t i = 0; i < nums.size(); ++i) {
        auto it = std::lower_bound(sub.begin(), sub.end(), nums[i],
                                   [&](int index, int val) { return nums[index] < val; });
        size_t pos = std::distance(sub.begin(), it);

        if (it == sub.end()) {
            sub.push_back(i);
        } else {
            *it = i;
        }

        indices[pos] = i;
        if (pos > 0) {
            prev[i] = indices[pos - 1];
        }
    }

    std::vector<int> lis;
    for (int i = indices[sub.size() - 1]; i != -1; i = prev[i]) {
        lis.push_back(nums[i]);
    }

    std::reverse(lis.begin(), lis.end());
    return lis;
}
