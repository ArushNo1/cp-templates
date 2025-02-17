#ifndef ARRAYDBG_H
#define ARRAYDBG_H

template<typename T, size_t u>
ostream& operator<< (ostream& os, const array<T, u>& arr){
    os << "(";
    for(const T x : arr){
        os << x << " ";
    }
    os << ")";
    return os;
}

#endif