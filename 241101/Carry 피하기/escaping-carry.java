import java.io.*;
import java.util.*;

public class Main {
    // n이 20 이하이므로 시간초과를 생각 안 해도 될것 같다.
    // 1. carry가 발생하는지 검사하는 함수 작성
    // 2. 모든 조합을 만드는 코드 작성
    // 3. 모든 조합마다 carry가 발생하는지 않는지 검사한 후 최댓값을 구한다.
    static int n, max;
    static int[] arr;
    static boolean[] selectedArr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        selectedArr = new boolean[n];
        max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        makeCombi(0, 0);
        System.out.println(max);
    }
    // 모든 조합 생성
    static void makeCombi(int index, int countSelect) {
        // 조합이 완성되면 
        if (index > n - 1) {
            // carry가 발생하지 않았다면
            if(!checkCarry()) {
                max = Math.max(max, countSelect);
            } 
            return;
        }
        selectedArr[index] = true;
        makeCombi(index + 1, countSelect + 1);
        selectedArr[index] = false;
        makeCombi(index + 1, countSelect);
    }
    // carry 발생 확인 (각 자리수 합이 10이 넘는지 확인)
    static boolean checkCarry() {
        int tmp = 10000000;  // 각 자리수
        while (tmp > 0) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (selectedArr[i]) sum += arr[i] / tmp % 10;
            }
            if (sum >= 10) return true;
            tmp /= 10;
        }
        return false;
    }
}