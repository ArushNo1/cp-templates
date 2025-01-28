import java.util.Objects;

public class Pair<T extends Comparable<T>, U extends Comparable<U>> {
    private T first;
    private U second;

    // Constructor to initialize the pair
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // Getter for the first element
    public T getFirst() {
        return first;
    }

    // Getter for the second element
    public U getSecond() {
        return second;
    }

    // Method to compare the first elements of the pair
    public int compareFirst(Pair<T, U> other) {
        return this.first.compareTo(other.getFirst());
    }

    // Method to compare the second elements of the pair
    public int compareSecond(Pair<T, U> other) {
        return this.second.compareTo(other.getSecond());
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
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
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
