import java.util.*;
import java.io.*;

// 굳이 그래프 탐색할 필요없이 풀 수 있을거 같음
public class Main {
    // static int[][] mat = new int[101][101];
    static boolean[] rList = new boolean[101];
    static boolean[] cList = new boolean[101];
    static int n, r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int rCount = 0;
        int cCount = 0;
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            r = Integer.parseInt(line[0]);
            c = Integer.parseInt(line[1]);
            rList[r] = true;
            cList[c] = true;
        }
        for (int i = 0; i < 101; i++) {
            if (rList[i]) rCount++;
            if (cList[i]) cCount++;
        }
        System.out.println(2 * (rCount + cCount));
    }
}