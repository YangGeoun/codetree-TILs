import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int flag,i,j;
        int[] arr = new int[n];
        line = br.readLine().split(" ");
        for (int k = 0; k < n; k++) {
            arr[k] = Integer.parseInt(line[k]);
        }
        for (int a = 0; a < m; a++) {
            line = br.readLine().split(" ");
            flag = Integer.parseInt(line[0]);
            i = Integer.parseInt(line[1]);
            j = Integer.parseInt(line[2]);
            if (flag == 1) method1(arr, i, j);
            else if (flag == 2) method2(arr, i, j);
            else if (flag == 3) method3(arr, i, j);
            else method4(arr, i, j);
        
        }
        for (int k = 0; k < n; k++) {
            System.out.print(arr[k]);
            System.out.print(" ");
        }
    }

    static void method1(int[] arr, int i, int x) {
        arr[i - 1] = x;
    }

    static void method2(int[] arr, int i, int j) {
        for (int a = i - 1; a < j; a++) {
            arr[a] = (arr[a] == 0) ? 1 : 0;
        }
    }

    static void method3(int[] arr, int i, int j) {
        for (int a = i - 1; a < j; a++) {
            arr[a] = 0;
        }
    }

    static void method4(int[] arr, int i, int j) {
        for (int a = i - 1; a < j; a++) {
            arr[a] = 1;
        }
    }
}