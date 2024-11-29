import java.util.*;
import java.io.*;


// 1. 골렘의 이동 구형
// 2. 요정의 이동 구현

public class Main {
    static int[] dr = {-1, 0, 1, 0};   // 북, 동, 남, 서 
    static int[] dc = {0, 1, 0, -1};
    static int R, C, K;
    static int[][] forestMatrix;
    static boolean[][] exitMatrix;

    static int positionGolem(int CenterC, int d, int t) {
        int centerR = 0
        // 수직으로 내려갈 수 있는 만큼 내려감
        while(true) {
            for (int d = 1; d < 4; d++) {
                int newR = centerR + dr[d];
                int newC = CenterC + dc[d];
                if(newR >= R || forestMatrix[newR][newC] > 0) {
                    break;
                }
            }
        }

        for (int d = 0; d < 4; d++) {
            forestMatrix[centerR + dr[d]][CenterC + dc[d]] = t;
        }

        return centerR;
    }

    static void positionFairy(int R, int C) {

    }

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine.split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);
        forestMatrix = int[R][C];
        exitMatrix = boolean[R[C];
        
        for (int t = 0; t < K; t++) {
            line = br.readLine.split(" ");
            positionGolem(line[0], line[1], t + 1);
        }

        System.out.println(Arrays.deepToString(forestMatrix));
    }
}