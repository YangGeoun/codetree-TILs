import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[] arr = new int[n];
        line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        for (int a = 0; a < m; a++) {
            line = br.readLine().split(" ");
            int flag = Integer.parseInt(line[0]);
            int i = Integer.parseInt(line[1]);
            int j = Integer.parseInt(line[2]);
            if (flag == 1) arr = method1(arr, i, j);
            else if (flag == 2) arr = method2(arr, i, j);
            else if (flag == 3) arr = method3(arr, i, j);
            else arr = method4(arr, i, j);
        
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }

    static int[] method1(int[] arr, int i, int x) {
        arr[i - 1] = x;
        return arr;
    }

    static int[] method2(int[] arr, int i, int j) {
        for (int a = i - 1; a < j; a++) {
            arr[a] = (arr[a] == 0) ? 1 : 0;
        }
        return arr;
    }

    static int[] method3(int[] arr, int i, int j) {
        for (int a = i - 1; a < j; a++) {
            arr[a] = 0;
        }
        return arr;
    }

    static int[] method4(int[] arr, int i, int j) {
        for (int a = i - 1; a < j; a++) {
            arr[a] = 1;
        }
        return arr;
    }
}