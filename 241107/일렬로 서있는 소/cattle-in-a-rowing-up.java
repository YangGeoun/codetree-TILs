import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        System.out.println(solve1(arr));
    }

    //브루트포스 (최대 조합의 수 = 1000C3 = 166167000 )
    static int solve1(int[] arr){
        int cnt = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for(int j = i + 1; j < arr.length - 1; j++) {
                for(int k = j + 1; k < arr.length; k++ ) {
                    if ( (arr[j] - arr[i]) <= (arr[k] - arr[j]) && (arr[k] - arr[j]) <= 2 * (arr[j] - arr[i]) ) cnt++;
                }
            }
        }
        
        return cnt;
    }
}