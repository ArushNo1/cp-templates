#ifndef PQUEUEDBG_H
#define PQUEUEDBG_H

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

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::stack<T>& st) {
    std::stack<T> copy = st;
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

#endif