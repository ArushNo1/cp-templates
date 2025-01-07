template <typename T, typename U, typename V>
std::ostream& operator<<(std::ostream& os, const std::priority_queue<T, U, V>& pq) {
    std::priority_queue<T, U, V> copy = pq;
    os << "[";
    bool first = true;
    while (!copy.empty()) {
        if (!first) os << ", ";
        os << copy.top();
        copy.pop();
        first = false;
    }
    os << "]";
    return os;
}