import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int start = 65;
        for (int i = 0 ; i < n ; i++) {
            int tmp = start + (i % 26);
            for (int j = 0; j < 2 * (n - i - 1) ; j++) {
                System.out.print(" ");
            }
            System.out.print((char)tmp);
            for (int j = -1; j < i - 1 ; j++) {
                tmp = (tmp + ((n - j - 2) % 26));
                if (tmp > 90) tmp = tmp - 26; 
                System.out.print(" "+ (char)tmp);
            }
            System.out.println();
        }
    }


}