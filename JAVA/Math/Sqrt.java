public class Sqrt {
    
    /**
     * Computes the integer square root of x. The result is the largest integer
     * whose square is not greater than x.
     *
     * @param x the value to compute the square root of
     * @return the integer square root of x
     */
    public static int sqrt(int x){
        int low = 0, high = x;
        while(low < high){
            int mid = low + (high - low + 1) / 2;
            int mid2 = mid * mid;
            if(mid2 == x){
                return mid;
            }
            if(mid2 < x){
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * Computes the integer square root of x. The result is the largest integer
     * whose square is not greater than x.
     *
     * @param x the value to compute the square root of
     * @return the integer square root of x
     */
    public static long sqrt(long x){
        long low = 0, high = x;
        while(low < high){
            long mid = low + (high - low + 1) / 2;
            long mid2 = mid * mid;
            if(mid2 == x){
                return mid;
            }
            if(mid2 < x){
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
