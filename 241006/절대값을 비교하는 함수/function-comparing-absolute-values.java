import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IoException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Inteager.parseInt(br.readLine());
        int A, B;
        for (int i = 0; i < n ; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            A = Integear.parseInt(st.nextToken());
            B = Integear.parseInt(st.nextToken());
            if (A < 0) {
                A = 2 * (0 - A);
            }
            if (B < 0) {
                B = 2 * (0 - B);
            }
            if (A > B) {
                System.out.println(A);
            } else {
                System.out.println(B);
            }
        }
    }
}