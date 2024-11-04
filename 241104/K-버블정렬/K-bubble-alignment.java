import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        arr = new int[n];
        k = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        for (int i = 0; i < k; i++) {
            doSingleBubbleSort();
        }
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }
    
    // 버블정렬 1단계 진행하는 함수
    static void doSingleBubbleSort() {
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                int tmp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = tmp;
            }
        }
    }
}