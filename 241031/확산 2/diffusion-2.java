import java.io.*;
import java.util.*;

public class Main {
    static int[][] matrix;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1 ,0, 0};
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int numOfZero = 0;
    static int n, m;
    static Queue<Point> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        matrix = new int[n][m];
        for(int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                int num = Integer.parseInt(line[j]);
                if(num == 1) {
                    q.add(new Point(i, j));
                } else if (num == 0) numOfZero++;
                matrix[i][j] = num;
            }
        }
        System.out.println(bfs());
    }
    static int bfs() {
        int answer = 0;
        int cnt = 0;
        while(!q.isEmpty()){
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (0 <= nr && 0 <= nc && nr < n && nc < m){
                    if (matrix[nr][nc] == 0) {
                        matrix[nr][nc] = matrix[now.r][now.c] + 1;
                        answer = matrix[nr][nc];
                        cnt++;
                        q.add(new Point(nr,nc));
                    }
                }
            }
        }
        if (cnt == numOfZero) return answer - 1;
        return -1;
    }
}