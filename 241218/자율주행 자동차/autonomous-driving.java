import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[][] matrix = new int[n][m];
        line = br.readLine().split(" ");
        int r = Integer.parseInt(line[0]);
        int c = Integer.parseInt(line[1]);
        int d = Integer.parseInt(line[2]);
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for(int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        int answer = 0;
        while(true) {
            boolean flag = true;
            for (int i = 1; i < 5; i++) {
                int newD = (d + 3 * i) % 4;
                int newR = r + dr[newD];
                int newC = c + dc[newD];
                if (matrix[newR][newC] == 0) {
                    matrix[r][c] = 2;
                    r = newR;
                    c = newC;
                    d = newD;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                int newR = r + dr[(d + 2) % 4];
                int newC = c + dc[(d + 2) % 4];
                if (matrix[newR][newC] == 1) break;
                matrix[r][c] = 2;
                r = newR;
                c = newC;
            }
        }
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (matrix[i][j] == 2) answer++;
            }
        }
        System.out.println(answer);
    }
}