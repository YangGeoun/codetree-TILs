import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        diff = new int[N + 1];

        for (int i = 0; i < K; i++) {
            line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]) - 1;
            int end = Integer.parseInt(line[1]);
            diff[start]++;
            diff[end]--;
        }

        int sum = 0;
        int[] count = new int[K + 1];
        for (int i = 0; i < N; i++) {
            sum += diff[i];
            count[sum]++;
        }

        int total = 0;
        for (int i = K; i >= 0; i--) {
            total += count[i];
            if (total >= (N + 1) / 2) {
                System.out.println(i);
                break;
            }
        }
    }
}