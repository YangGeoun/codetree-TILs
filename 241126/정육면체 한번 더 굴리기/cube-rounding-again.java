import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {0, 1, 0, -1}; // 상, 우, 하, 좌 (위에서 시계방향)
    static int[] dc = {-1, 0, 1, 0}; // 상, 우, 하, 좌 (위에서 시계방향)
    static class Dice {
        int[] position; // {r, c}
        int direction;  // { 0 : 상, 1 : 우, 2 : 하, 3 : 좌 }
        int numOfBottom;
        int[] nextBottom;
        int point;

        Dice() {
            this.position = new int[]{0, 0};
            this.direction = 1;
            this.numOfBottom = 6;
            this.nextBottom = new int[]{5, 3, 2, 4};
            this.point = 0;
        }

        // 현재 위치와 방향을 가지고 어디로 가야하는지 계산하는 메서드
        public void move(int[][] matrix, int n) {
            // 위치 이동(갈 수 있으면 가고 갈 수 없다면 반대 방향)
            int newR = this.position[0] + dr[this.direction];
            int newC = this.position[1] + dc[this.direction];
            if (0 <= newR && newR < n && 0 <= newC && newC < n) {
                this.position[0] = newR;
                this.position[1] = newC;
            } else {
                this.direction = (direction + 2) % 4;   // 반대 방향
                this.position[0] = this.position[0] + dr[this.direction];
                this.position[1] = this.position[1] + dc[this.direction];
            }
            // 이동 후 현재 바닥 번호 변경
            int now = this.numOfBottom;
            this.numOfBottom = nextBottom[this.direction];
            this.nextBottom[direction] = 7 - now;
            this.nextBottom[(direction + 2) % 4] = now;
            // 다음 가야할 방향 계산
            if (this.numOfBottom > matrix[this.position[0]][this.position[1]]){
                 this.direction = (this.direction + 1) % 4;
            }
            else if (this.numOfBottom < matrix[this.position[0]][this.position[1]]) {
                this.direction = (this.direction + 3) % 4;
            }
            // 이동한 위치에서 점수 계산
            getPoint(matrix, n);
        }

        // bfs를 통해 현재 위치의 점수 계산
        private void getPoint(int[][] matrix, int n) {
            int point = matrix[this.position[0]][this.position[1]];
            boolean[][] visited = new boolean[n][n];
            Queue<int[]> q = new LinkedList<>();
            q.add(this.position);
            visited[this.position[0]][this.position[1]] = true;
            this.point += point;
            while(!q.isEmpty()) {
                int[] now = q.poll();
                for (int d = 0; d < 4 ;d++) {
                    int newR = now[0] + dr[d];
                    int newC = now[1] + dc[d];
                    if (0 <= newR && newR < n && 0 <= newC && newC < n) {
                        if (!visited[newR][newC] && point == matrix[newR][newC]){
                            this.point += point;
                            q.add(new int[]{newR, newC});
                            visited[newR][newC] = true;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n ; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[j][i] = Integer.parseInt(line[j]);
            }
        }
        Dice dice = new Dice();
        for (int i = 0; i < m; i++) {
            dice.move(matrix, n);
        }
        System.out.println(dice.point);
    }
}