
import java.util.*;

class BinarySearch {
    public static int lowerBound(List<Long> list, long key) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) >= key) {
                right = mid; // Move left to find the first occurrence
            } else {
                left = mid + 1;
            }
        }
        return left; // First index where element is >= key
    }
    public static int upperBound(List<Long> list, long key) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > key) {
                right = mid; // Move left to find the first greater element
            } else {
                left = mid + 1;
            }
        }
        return left; // First index where element is > key
    }
    
}
