import java.io.*;
import java.util.*;

public class Main {
    // 최대 100,000,000(1억)회 계산 (100 * 1,000,000)
    static boolean[] isRemoved;
    static String S, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        String result = removeSubstring(S, T);
        System.out.println(result);
    }

    static String removeSubstring(String S, String T) {
        StringBuilder sb = new StringBuilder(S);
        int index;
        while ((index = sb.indexOf(T)) != -1) {
            sb.delete(index, index + T.length());
        }
        return sb.toString();
    }

}