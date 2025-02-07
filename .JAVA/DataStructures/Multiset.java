
import java.util.*;

public class Multiset<T> {
    private final NavigableMap<T, Integer> map;
    
    public Multiset() {
        map = new TreeMap<>();
    }
    
    public Multiset(Iterable<T> arr){ 
        map = new TreeMap<>();
        initialize(arr);
    }

    private void initialize(Iterable<T> arr) {
        for (T a : arr) add(a);
    }
    
    public void add(T a) {
        map.put(a, map.getOrDefault(a, 0) + 1);
    }
    
    public void remove(T a){
        if(map.containsKey(a)){
            map.put(a, map.get(a) - 1);
            if(map.get(a) == 0){
                map.remove(a);
            }
        }
    }
    
    public int count(T a) {
        return map.getOrDefault(a, 0);
    }
    
    // Additional methods
    public boolean isEmpty() {
        return map.isEmpty();
    }
    
    public int size() {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    public void clear() {
        map.clear();
    }
    
    public T first() {
        return map.firstKey();
    }
    
    public T last() {
        return map.lastKey();
    }
    
    public T ceiling(T a) {
        return map.ceilingKey(a);
    }
    
    public T floor(T a) {
        return map.floorKey(a);
    }
    
    public Set<T> elementSet() {
        return map.keySet();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey()).append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        return sb.append("]").toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Multiset)) return false;
        Multiset<?> multiset = (Multiset<?>) o;
        return map.equals(multiset.map);
    }
    
    @Override
    public int hashCode() {
        return map.hashCode();
    }
}
