
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Concise {
    static String[] getInput(Scanner sc, String pattern) {
        String token = sc.next();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(token);
        if (m.find()) {
            String[] groups = new String[m.groupCount()];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = m.group(i + 1);
            }
            return groups;
        }
        return new String[0];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 1;
        // t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
    }

    static void solve(Scanner sc) {
        
    }
}
