#ifndef MAPDBG_H
#define MAPDBG_H

#ifndef PAIRDBG_H
#include "pairdbg.h"
#endif

template <typename T, typename U>
ostream& operator<< (ostream& os, const map<T, U>& arr){
	os << "{\n";
    for(pair<T,U> x : arr){
		os << "    " << x << endll;
	}
	os << "}";
    return os;
}

template <typename K1, typename K2, typename V>
ostream& operator<<(ostream& os, const map<K1, map<K2, vector<V>>>& arr) {
    os << "{\n";
    for (auto i = arr.begin(); i != arr.end(); i++) {
        for (auto j = i->second.begin(); j != i->second.end(); j++) {
            os << "    [" << i->first << "][" << j->first << "] -> [";
            for (size_t k = 0; k < j->second.size(); k++) {
                os << j->second[k];
                if (k != j->second.size() - 1) os << ", ";
            }
            os << "]\n";
        }
    }
    os << "}";
    return os;
}

template <typename T, typename U>
ostream& operator<< (ostream& os, const unordered_map<T, U>& arr){
    os << "{\n";
    for(pair<T,U>& x : arr){
        os << "    " << x << endll;
    }
    os << "}";
    return os;
}

template <typename T, typename U>
ostream& operator<< (ostream& os, const multimap<T, U>& arr){
    os << "{\n";
    for(pair<T,U>& x : arr){
        os << "    " << x << endll;
    }
    os << "}";
    return os;
    
}
#endif