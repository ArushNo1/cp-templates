public class BIT<T extends Number> {
    private final int size;
    private final T[] bit;
    private final T[] arr;

    // Constructor
    public BIT(int size) {
        this.size = size;
        bit = (T[]) new Number[size + 1];  // BIT array (size + 1)
        arr = (T[]) new Number[size];       // arr array
    }

    // Set the value at index ind to val
    public void set(int ind, T val) {
        add(ind, subtract(val, arr[ind]));
    }

    // Add val to the element at index ind
    public void add(int ind, T val) {
        arr[ind] = add(arr[ind], val);  // Update the arr at index

        ind++;  // Convert to 1-based index
        for (; ind <= size; ind += ind & -ind) {
            bit[ind] = add(bit[ind], val);  // Update BIT array
        }
    }

    // Get the sum of all values in [0, ind]
    public T pref_sum(int ind) {
        ind++;
        T total = (T) Integer.valueOf(0); // Assuming we're working with Integer for simplicity
        for (; ind > 0; ind -= ind & -ind) {
            total = add(total, bit[ind]);
        }
        return total;
    }

    // Utility method for adding numbers (generic for any type extending Number)
    private T add(T a, T b) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() + b.intValue());
        } else if (a instanceof Long) {
            return (T) Long.valueOf(a.longValue() + b.longValue());
        } else if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() + b.doubleValue());
        }
        throw new UnsupportedOperationException("Unsupported number type");
    }

    // Utility method for subtracting numbers (generic for any type extending Number)
    private T subtract(T a, T b) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() - b.intValue());
        } else if (a instanceof Long) {
            return (T) Long.valueOf(a.longValue() - b.longValue());
        } else if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() - b.doubleValue());
        }
        throw new UnsupportedOperationException("Unsupported number type");
    }
}
 {
    
}
