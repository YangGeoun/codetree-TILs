import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double sq = Math.sqrt(n);
        Stack<Integer> s = new Stack();
        System.out.print(1);
        for (int i = 2; i < sq ; i++ ) {
            if (n % i == 0) {
                System.out.print(" ");
                System.out.print(i);
                s.push(n/i);
            }
        }
        if (sq == (int) sq) {
            System.out.print(" ");
            System.out.print(sq);
        }
        while (!s.isEmpty()) {
            System.out.print(" ");
            System.out.print(s.pop());
        }
        System.out.print(" ");
        System.out.print(n);
        
    }
}