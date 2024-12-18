import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] restaurantList = new int[N];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            restaurantList[i] = Integer.parseInt(line[i]);
        }
        line = br.readLine().split(" ");
        int ldr = Integer.parseInt(line[0]);
        float mbr = Integer.parseInt(line[1]);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int num = restaurantList[i];
            if (num <= ldr) answer++;
            else {
                answer++;
                num -= ldr;
                answer += (int) Math.ceil(num / mbr);
            }

        }
        System.out.println(answer);
    }
}