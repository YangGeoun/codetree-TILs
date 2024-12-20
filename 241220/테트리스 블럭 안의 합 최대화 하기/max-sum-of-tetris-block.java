import java.util.*;
import java.io.*;

// bds탐색의 모든 경우의 수 같지 않을까? (메모리는 조금 더 적게 쓰지만 오래 걸리는)
public class Main {
    static int n, m;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix;
    static boolean[][] visited;

    static class Position {
        int r, c, d, sum, num;
        public Position(int r, int c, int d, int sum, int num) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.sum = sum;
            this.num = num;
        }
    }

    // bfs를 사용해서 해당칸의 최댓값을 찾는 함수 **(ㅗ 모양 표현 못함)**
    static int getMax(int r, int c) {
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(r, c, 5, matrix[r][c], 1));
        int max = 0;
        while (!q.isEmpty()) {
            Position now = q.poll();
            for (int d = 0; d < 4; d++) {
                if (now.d == ((d + 2) % 4)) continue;
                int newR = now.r + dr[d];
                int newC = now.c + dc[d];
                if (0 <= newR && newR < n && 0 <= newC && newC < m) {
                    if (now.num < 3) {
                        q.add(new Position(newR, newC, d , (now.sum + matrix[newR][newC]), now.num + 1));
                    } else if (now.num == 3) {
                        max = Math.max(max, now.sum + matrix[newR][newC]);
                        
                    }
                }
            }
        }
        return max;
    }
    
    static int checkT() {
        int max = 0;
        for(int i = 0; i < n - 2; i++) {
            for(int j = 0; j < m - 1; j++) {
                int sum = matrix[i][j];
                sum += matrix[i + 1][j];
                sum += matrix[i + 2][j];
                sum += matrix[i + 1][j + 1];
                max = Math.max(max, sum);
            }
        }
        for(int i = 0; i < n - 2; i++) {
            for(int j = 1; j < m; j++) {
                int sum = matrix[i][j];
                sum += matrix[i + 1][j];
                sum += matrix[i + 2][j];
                sum += matrix[i + 1][j - 1];
                max = Math.max(max, sum);
            }
        }
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < m - 2; j++) {
                int sum = matrix[i][j];
                sum += matrix[i][j + 1];
                sum += matrix[i][j + 2];
                sum += matrix[i + 1][j + 1];
                max = Math.max(max, sum);
            }
        }
        for(int i = 1; i < n - 1; i++) {
            for(int j = 0; j < m - 2; j++) {
                int sum = matrix[i][j];
                sum += matrix[i][j + 1];
                sum += matrix[i][j + 2];
                sum += matrix[i - 1][j + 1];
                max = Math.max(max, sum);
            }
        }
        return max;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                answer = Math.max(answer, getMax(i, j));
            }
        }
        answer = Math.max(answer, checkT());
        System.out.println(answer);

    }
}