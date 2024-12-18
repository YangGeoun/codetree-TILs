import java.io.*;
import java.util.*;

public class Main {
    static int plus, minus, multipy, n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] tmpArr, numberArr, operatorArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numberArr = new int[n];
        tmpArr = new int[n - 1];
        operatorArr = new int[4];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numberArr[i] = Integer.parseInt(line[i]);
        }
        line = br.readLine().split(" ");
        operatorArr[1] = Integer.parseInt(line[0]);
        operatorArr[2] = Integer.parseInt(line[1]);
        operatorArr[3] = Integer.parseInt(line[2]);
        solve(0);
        System.out.println(min + " " + max);
    }

    //  n - 1 길이의 순열 만드는 함수
    static void solve(int index) {
        if (index > n - 1) return;
        if (index == n - 1) {
            int num = numberArr[0];
            for (int i = 0; i < n - 1; i++) {
                switch (tmpArr[i]) {
                    case 1:
                        num += numberArr[i + 1];
                        break;
                    case 2:
                        num -= numberArr[i + 1];
                        break;
                    case 3:
                        num *= numberArr[i + 1];
                        break;
                }
            }
            
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (operatorArr[i] > 0) {
                tmpArr[index] = i;
                operatorArr[i]--;
                solve(index + 1);
                tmpArr[index] = 0;
                operatorArr[i]++;
            }

        }
    }
    
}