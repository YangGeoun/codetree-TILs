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
        solve(0,0);
        System.out.println(maxMoney);
        
    }
     static void solve(int index, int moneySum) {
        if (index >= n) {
            maxMoney = Math.max(maxMoney, moneySum);
            return;
        }
        solve(index + 1, moneySum);
        if (index + works[index][0] <= n) {
            solve(index + works[index][0], moneySum + works[index][1]);
        }
    }
}