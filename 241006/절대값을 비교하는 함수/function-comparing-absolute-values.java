import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int A, B;
        for (int i = 0; i < n ; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            if (A < 0) {
                A = -A;
            }
            if (B < 0) {
                B = -B;
            }
            if (A > B) {
                System.out.println(A);
            } else {
                System.out.println(B);
            }
        }
    }
}