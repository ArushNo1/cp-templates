#ifndef MAPDBG_H
#define MAPDBG_H

#ifndef PAIRDBG_H
#include "pairdbg.h"
#endif

template <typename T, typename U>
ostream& operator<< (ostream& os, const map<T, U>& arr){
	os << "{\n";
    for(const pair<T,U> x : arr){
		os << "    " << x << endll;
	}
	os << "}";
    return os;
}

template <typename T, typename U>
ostream& operator<< (ostream& os, const unordered_map<T, U>& arr){
    os << "{\n";
    for(const pair<T,U>& x : arr){
        os << "    " << x << endll;
    }
    os << "}";
    return os;
}

template <typename T, typename U>
ostream& operator<< (ostream& os, const multimap<T, U>& arr){
    os << "{\n";
    for(const pair<T,U>& x : arr){
        os << "    " << x << endll;
    }
    os << "}";
    return os;
    
}
#endif