import java.io.*;
import java.util.*;

public class Main {
    // 최대로 자를 수 있는 걸 어떻게 찾을 수 있을까?
    // stack 활용
    static class Info implements Comparable<Info> {
        boolean isA;
        int num;

        Info(boolean isA, int num){
            this.isA = isA;
            this.num = num;
        }

        @Override
        public int compareTo(Info o) {
            return num - o.num;
        }

        @Override
        public String toString() {
            return "{isA : " + isA + ", num : " + num + "}";
        }
    }

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Info> pq = new PriorityQueue<>();
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        for (int i = 0; i < K; i++) {
            line = br.readLine().split(" ");
            pq.add(new Info(true, Integer.parseInt(line[0])));
            pq.add(new Info(false, Integer.parseInt(line[1])));
        }
        int answer = 1;
        while(!pq.isEmpty()) {
            Info now = pq.poll();
            if (!now.isA) {
                while (pq.peek() != null && !pq.peek().isA) {
                    pq.poll();
                }
                answer++;
            }
        }
        System.out.println(answer);
    }
}