public class Pair<T extends Comparable<T>, U extends Comparable<U>> implements Comparable<Pair<T, U>> {
    public T first;
    public U second;

    // Constructor
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
  
    // toString for easy printing
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    // Equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return first.equals(pair.first) && second.equals(pair.second);
    }
  
    // CompareTo method for sorting
    @Override
    public int compareTo(Pair<T, U> other) {
        int firstComparison = this.first.compareTo(other.first);
        if (firstComparison != 0) {
            return firstComparison;
        }
        return this.second.compareTo(other.second);
    }
}
