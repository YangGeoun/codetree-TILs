import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tmp;
        int max = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 2 ; i++) {
            tmp = Integer.parseInt(st.nextToken());
            if (max < tmp) max = tmp;
        }
        System.out.println(max);
    }
}