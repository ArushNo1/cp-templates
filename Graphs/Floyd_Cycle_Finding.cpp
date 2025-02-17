#include <bits/stdc++.h>
using namespace std;
template<typename T>
std::pair<T, int> floyd_find_cycle(T start, function<T(T)> f) {
    T tort = start;
    T hare = start;
    while(tort != hare){
        tort = f(tort);
        hare = f(f(hare));
    }
    hare = start;
    while(tort != hare){
        tort = f(tort);
        hare = f(hare);
    }
    T mu = tort;
    hare = f(tort);
    int lambda = 1;
    while(tort != hare){
        hare = f(hare);
        lambda++;
    }
    return {mu, lambda};
}