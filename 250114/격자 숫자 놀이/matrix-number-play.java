import java.util.*;
import java.io.*;

public class Main {
    static int r, c, k, maxR = 2, maxC = 2;
    static int[][] matrix;

    static class NumCounter implements Comparable<NumCounter> {
        int num;
        int count;

        NumCounter(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(NumCounter o) {
            if (this.count == o.count) {
                return this.num - o.num; // 숫자가 작은 순서대로
            }
            return this.count - o.count; // 출현 빈도 수가 적은 순서대로
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        matrix = new int[101][101]; // 최대 크기 제한
        for (int i = 0; i < 3; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        int answer = -1;
        for (int time = 0; time <= 100; time++) { // 최대 100초 제한
            if (matrix[r - 1][c - 1] == k) {
                answer = time;
                break;
            }
            sortMatrix();
        }

        System.out.println(answer);
    }

    static void sortMatrix() {
        if (maxR >= maxC) {
            maxC = sortRows();
        } else {
            maxR = sortColumns();
        }
    }

    static int sortRows() {
        int newMaxC = 0;
        
        for (int i = 0; i <= maxR; i++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            
            // 숫자 빈도 계산
            for (int j = 0; j <= maxC; j++) {
                if (matrix[i][j] != 0) { // 0은 무시
                    countMap.put(matrix[i][j], countMap.getOrDefault(matrix[i][j], 0) + 1);
                }
            }

            // 빈도 기반 정렬
            PriorityQueue<NumCounter> pq = new PriorityQueue<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                pq.add(new NumCounter(entry.getKey(), entry.getValue()));
            }

            // 정렬 결과를 행에 반영
            int index = 0;
            while (!pq.isEmpty()) {
                NumCounter nc = pq.poll();
                matrix[i][index++] = nc.num;
                matrix[i][index++] = nc.count;
                if (index >= 100) break; // 최대 크기 제한
            }

            // 나머지 칸은 0으로 채움
            while (index <= maxC) {
                matrix[i][index++] = 0;
            }

            newMaxC = Math.max(newMaxC, index - 1);
        }
        
        return newMaxC;
    }

    static int sortColumns() {
        int newMaxR = 0;

        for (int i = 0; i <= maxC; i++) {
            Map<Integer, Integer> countMap = new HashMap<>();

            // 숫자 빈도 계산
            for (int j = 0; j <= maxR; j++) {
                if (matrix[j][i] != 0) { // 0은 무시
                    countMap.put(matrix[j][i], countMap.getOrDefault(matrix[j][i], 0) + 1);
                }
            }

            // 빈도 기반 정렬
            PriorityQueue<NumCounter> pq = new PriorityQueue<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                pq.add(new NumCounter(entry.getKey(), entry.getValue()));
            }

            // 정렬 결과를 열에 반영
            int index = 0;
            while (!pq.isEmpty()) {
                NumCounter nc = pq.poll();
                matrix[index++][i] = nc.num;
                matrix[index++][i] = nc.count;
                if (index >= 100) break; // 최대 크기 제한
            }

            // 나머지 칸은 0으로 채움
            while (index <= maxR) {
                matrix[index++][i] = 0;
            }

            newMaxR = Math.max(newMaxR, index - 1);
        }

        return newMaxR;
    }
}
