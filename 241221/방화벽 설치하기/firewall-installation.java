import java.io.*;
import java.util.*;

public class Main {
    static int n, m, min = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] matrix;
    static List<Position> firePositionList;

    static class Position {
        int r, c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int bfs() {
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for (Position start : firePositionList) {
            q.add(start);
            visited[start.r][start.c] = true;
        }
        int count = 0;
        while (!q.isEmpty()) {
            if (count > min) return Integer.MAX_VALUE;
            Position now = q.poll();
            for (int d = 0; d < 4; d++) {
                int newR = now.r + dr[d];
                int newC = now.c + dc[d];
                if(0 <= newR && newR < n && 0 <= newC && newC < m) {
                    if (matrix[newR][newC] == 0 && !visited[newR][newC]) {
                        q.add(new Position(newR, newC));
                        visited[newR][newC] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    } 

    // 브루트포스 적인 풀이 8x8 에서 3점을 뽑는 경우의 수는 대략 40000
    // 40000번 8x8 에서 BFS 단 이미 구한 최소보다 커지면 탐색 중단(백트랙킹)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedInput = br.readLine().split(" ");
        n = Integer.parseInt(splitedInput[0]);
        m = Integer.parseInt(splitedInput[1]);
        firePositionList = new ArrayList<>();
        matrix = new int[n][m]; 
        int answer = 0;
        for (int i = 0; i < n; i++) {
            splitedInput = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(splitedInput[j]);
                matrix[i][j] = tmp;
                if (tmp == 2) firePositionList.add(new Position(i, j));
                if (tmp == 0) answer++;
            }
        }
        // (n * m 행렬을 1줄로 만드는 느낌) 3중 for 문
        for (int i = 0; i < n * m; i++) {
            int r1 = i / m, c1 = i % m; 
            if (matrix[r1][c1] != 0) continue;
            matrix[r1][c1] = 1;

            for (int j = i + 1; j < n * m; j++) {
                int r2 = j / m, c2 = j % m; 
                if (matrix[r2][c2] != 0) continue;
                matrix[r2][c2] = 1;

                for (int k = j + 1; k < n * m; k++) {
                    int r3 = k / m, c3 = k % m; 
                    if (matrix[r3][c3] != 0) continue;
                    matrix[r3][c3] = 1;
                    min = Math.min(min, bfs());

                    matrix[r3][c3] = 0;
                }
                matrix[r2][c2] = 0;
            }
            matrix[r1][c1] = 0;
        }
        answer = answer - min - 3;
        System.out.println(answer);
    }
}