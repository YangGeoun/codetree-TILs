import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int a = 0; a < m; a++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            if (flag == 1) arr = method1(arr, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            else if (flag == 2) arr = method2(arr, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            else if (flag == 3) arr = method3(arr, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            else arr = method4(arr, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        
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