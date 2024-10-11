import java.util.*;
import java.io.*;

public class Main {
    static int n, maxMoney;
    static int[][] works;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        works = new int[n][2];
        checked = new boolean[n];
        String[] line;
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            works[i][0] = Integer.parseInt(line[0]);
            works[i][1] = Integer.parseInt(line[1]);
        }
        System.out.println(solve(n, works));
    }

    static int solve(int n, int[][] works) {
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int t = works[i][0];
            int p = works[i][1];
            
            if (i + 1 <= n) {
                dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            }
            
            if (i + t <= n) {
                dp[i + t] = Math.max(dp[i + t], dp[i] + p);
            }
        }
        
        return dp[n];
    }
}