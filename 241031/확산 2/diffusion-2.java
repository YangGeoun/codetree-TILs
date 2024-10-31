import java.io.*;
import java.util.*;

public class Main {
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int numOfZero = 0;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        matrix = new int[n][m];
        visited = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                int num = Integer.parseInt(line[j]);
                if(num == 1) {
                    q.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                } else if (num == 0) numOfZero++;
                matrix[i][j] = num;
            }
        }
        System.out.println(bfs(q));
    }

    static int bfs(Queue<int[]> q) {
        if (numOfZero == 0) return 0;
        int maxDays = 0;
        int cnt = 0;

        while(!q.isEmpty()){
            int[] now = q.poll();
            int r = now[0], c = now[1], days = now[2];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && matrix[nr][nc] == 0){
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, days + 1});
                    maxDays = Math.max(maxDays, days + 1);
                    if (++cnt == numOfZero) return maxDays;
                }
            }
        }
        return -1;
    }
}