import java.util.Objects;

class Pair<T extends Comparable<T>, U extends Comparable<U>> {
    T first;
    U second;

    // Constructor to initialize the pair
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // Method to compare the first elements of the pair
    public int compareFirst(Pair<T, U> other) {
        return this.first.compareTo(other.first);
    }

    // Method to compare the second elements of the pair
    public int compareSecond(Pair<T, U> other) {
        return this.second.compareTo(other.second);
    }

    // Method to compare the whole pair lexicographically: first then second
    public int compare(Pair<T, U> other) {
        int firstComparison = this.compareFirst(other);
        if (firstComparison != 0) {
            return firstComparison;
        }
        return this.compareSecond(other);
    }

    // Overriding equals() method
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }

    // Overriding hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
