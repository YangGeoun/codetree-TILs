import java.io.*;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {
        boolean isA; // true: 시작점, false: 끝점
        int num;

        Info(boolean isA, int num) {
            this.isA = isA;
            this.num = num;
        }

        @Override
        public int compareTo(Info o) {
            // num 기준으로 정렬, 같으면 시작점이 먼저 오도록
            if (this.num == o.num) {
                return this.isA ? -1 : 1; // 시작점이 끝점보다 우선
            }
            return this.num - o.num;
        }

        @Override
        public String toString() {
            return "{isA : " + isA + ", num : " + num + "}";
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
            pq.add(new Info(true, Integer.parseInt(line[0]))); // 시작점 추가
            pq.add(new Info(false, Integer.parseInt(line[1]))); // 끝점 추가
        }
        
        int currentCount = 0; // 현재 열려있는 구간의 개수
        int maxCount = 0; // 최대 열려있는 구간의 개수
        
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            if (now.isA) { // 시작점일 경우
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else { // 끝점일 경우
                currentCount--;
            }
        }
        
        System.out.println(maxCount + 1);
    }
}