#include<bits/stdc++.h>
using namespace std;

typedef double pointtype;
typedef complex<pointtype> point;
#define x real()
#define y imag()

pointtype dot(point a, point b){
    return (conj(a) * b).real();
}

pointtype cross(point a, point b){
    return (conj(a) * b).imag();
}

pointtype dist(point a, point b){
    return abs(a - b);
}

pointtype dist2(point a, point b){
    return norm(a - b);
}

pointtype angle(point a, point b){
    return arg(b - a);
}

pointtype slope(point a, point b){
    return tan(arg(b - a));
}

point tocartesian(pointtype r, pointtype theta){
    return polar(r, theta);
}

point topolar(point cart){
    return point(abs(cart), arg(cart));
}

point rotate(point a, pointtype theta){
    return tocartesian(abs(a), arg(a) + theta);
}

point rotate(point a, pointtype theta, point orig){
    return rotate(a - orig, theta) + orig;
}

pointtype anglebetween(point a, point b, point c){
    return abs(remainder(arg(a-b) - arg(c-b), 2.0 * M_PI));
}

pointtype turn(point a, point b, point c){
    // positive -> left turn, negative -> right turn, 0 -> collinear
    return (c.y - a.y) * (b.x - a.x) - (c.x - a.x) * (b.y - a.y);
}

point project(point p, point v){
    return v * dot(p, v) / norm(v);
}

point project(point p, point a, point b){
    return project(p, b - a) + a;
}

point reflect(point p, point a, point b){
    return a + conj((p - a) / (b - a)) * (b - a);
}

point intersection(point a, point b, point p, point q) {
    double c1 = cross(p - a, b - a), c2 = cross(q - a, b - a);
    return (c1 * q - c2 * p) / (c1 - c2); // undefined if parallel
}



int main(){
    cout << "H" << endl;
}