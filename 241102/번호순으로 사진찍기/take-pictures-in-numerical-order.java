import java.io.*;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {
        int x1, x2;

        Info(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        @Override
        public int compareTo(Info o) {
            return this.x2 - o.x2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            line = br.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int x2 = Integer.parseInt(line[1]);
            if (x2 < x1) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            pq.add(new Info(x1, x2));
        }
        
        int lastx = 0;
        int count = 1;
        while (!pq.isEmpty()) {
            Info current = pq.poll();
            if (current.x1 >= lastx) {
                count++;
                lastx = current.x2;
            }
        }

        System.out.println(count);
    }
}