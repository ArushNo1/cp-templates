#ifndef SETDBG_H
#define SETDBG_H

template <typename T>
ostream& operator<< (ostream& os, const set<T>& arr){
	os << "[";
    for(const T x : arr){
		os << x << " ";
	}
	os << "]";
    return os;
}

template <typename T>
ostream& operator<< (ostream& os, const multiset<T>& arr){
    os << "[";
    for(const T x : arr){
        os << x << " ";
    }
    os << "]";
    return os;
}

template <typename T>
ostream& operator<< (ostream& os, const unordered_set<T>& arr){
    os << "[";
    for(const T x : arr){
        os << x << " ";
    }
    os << "]";
    return os;
}

ostream& operator<<(ostream& os, const indexed_set& s) {
    os << "{";
    bool first = true;
    for (auto it = s.begin(); it != s.end(); ++it) {
        if (!first) os << ", ";
        os << *it;
        first = false;
    }
    os << "}";
    return os;
}
#endif