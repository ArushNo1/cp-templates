#ifndef PAIRDBG_H
#define PAIRDBG_H

template <typename T, typename U>
ostream& operator<< (ostream& os, const pair<T, U> x){
    os << "(" << x.first << ", " << x.second << ")";
    return os;
}
#endif