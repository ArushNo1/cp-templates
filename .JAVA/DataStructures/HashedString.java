import java.util.*;

class HashedString {
    private static final long M = 1_000_000_009L;  // Modulo value
    private static final long B = 9973;  // Base value

    // pow[i] contains B^i % M
    private static List<Long> pow = new ArrayList<>(Collections.singletonList(1L));

    // p_hash[i] is the hash of the first i characters of the given string
    private List<Long> p_hash;

    // Constructor that initializes the hashed string
    public HashedString(String s) {
        p_hash = new ArrayList<>(s.length() + 1);
        while (pow.size() <= s.length()) {
            pow.add((pow.get(pow.size() - 1) * B) % M);
        }
        p_hash.add(0L);  // p_hash[0] is 0
        for (int i = 0; i < s.length(); i++) {
            p_hash.add((p_hash.get(i) * B + s.charAt(i)) % M);
        }
    }

    // Get the hash value for a substring from index 'start' to 'end' (non-inclusive of end)
    public long getHash(int start, int end) {
        long rawVal = (p_hash.get(end) - (p_hash.get(start) * pow.get(end - start + 1))) % M;
        return (rawVal + M) % M;  // Ensure non-negative result
    }

    // Main method to test
    public static void main(String[] args) {
        HashedString hs = new HashedString("example");
        System.out.println("Hash of substring (0, 3): " + hs.getHash(0, 3));  // Hash for "exam"
    }
}