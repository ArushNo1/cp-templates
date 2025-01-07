template<typename T>
T first_true(T lo, T hi, std::function<bool(T)> f) {
	hi++;
	while (lo < hi) {
		T mid = lo + (hi - lo) / 2;
		if (f(mid)) {
			hi = mid;
		} else {
			lo = mid + 1;
		}
	}
	return lo;
}
template<typename T>
T last_true(T lo, T hi, std::function<bool(T)> f) {
    lo--;
    while (lo < hi) {
        T mid = lo + (hi - lo + 1) / 2;
        if (f(mid)) {
            lo = mid;
        } else {
            hi = mid - 1;
        }
    }
    return lo;
}
