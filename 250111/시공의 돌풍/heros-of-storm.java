import java.io.*;
import java.util.*;

public class Main {
    static int n, m, t, storm;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] SplitedInput = br.readLine().split(" ");
        n = Integer.parseInt(SplitedInput[0]);
        m = Integer.parseInt(SplitedInput[1]);
        t = Integer.parseInt(SplitedInput[2]);
        matrix = new int[n][m];
        for (int i = 0; i < n; i++){
            SplitedInput = br.readLine().split(" ");
            for (int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(SplitedInput[j]);
                if (matrix[i][j] == -1){
                    storm = i;
                } 
            }
        }
        for (int i = 0; i < t; i++) {
            spreadDust();
            cleanUpDust();
        }
        int answer = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                answer += matrix[i][j];
            }
        }
        System.out.println(answer + 2);



    }
    // 구현1 (먼지의 확산)
    static void spreadDust() {
        int[][] sumMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int spreadAmount = matrix[i][j] / 5;
                for (int d = 0; d < 4; d++) {
                    int newR = i + dr[d];
                    int newC = j + dc[d];
                    if (0 <= newR && newR < n && 0 <= newC && newC < m && matrix[newR][newC] != -1) {
                        sumMatrix[newR][newC] += spreadAmount;
                        matrix[i][j] -= spreadAmount;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] += sumMatrix[i][j];
            }
        }
    }
    
    // 구현2 (시공의 돌풍 청소)
    static void cleanUpDust() {
        int r = storm - 2;
        while (r > 0) {
            matrix[r][0] = matrix[r - 1][0];
            r--;
        }
        int c = 0;
        while (c < m - 1) {
            matrix[0][c] = matrix[0][c + 1];
            c++;
        }
        while (r <= storm - 2) {
            matrix[r][m - 1] = matrix[r + 1][m - 1];
            r++;
        }
        while (c > 1) {
            matrix[r][c] = matrix[r][c - 1];
            c--;
        }
        matrix[r][c] = 0;

        r = storm + 1;
        while (r < n - 1) {
            matrix[r][0] = matrix[r + 1][0];
            r++;
        }
        c = 0;
        while (c < m - 1) {
            matrix[r][c] = matrix[r][c + 1];
            c++;
        }
        while (r >= storm + 1) {
            matrix[r][m - 1] = matrix[r - 1][m - 1];
            r--;
        }
        while (c > 1) {
            matrix[r][c] = matrix[r][c - 1];
            c--;
        }
        matrix[r][c] = 0;

    }
}