import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        int max = 0;
        HashSet<Integer> numSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
            numSet.add(num);
        }
        for (int el : numSet){
            max = Math.max(max, check(el));
        }
        System.out.println(max);
    }
    static int check(int n) {
        int now = arr[0];
        int cnt = 0;
        boolean isUpWater = now > n;
        for (int i = 1; i < N; i++) {
            if (isUpWater) {
                if(arr[i] <= n) {
                    cnt++;
                    isUpWater = false;
                }
            } else {
                if(arr[i] > n) isUpWater = true;
            }
        }
        return isUpWater ? cnt + 1 : cnt;
    }
}