import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = new int[]{-1,1, 0, 0, -1, -1, 1, 1};
    static int[] dc = new int[]{0, 0,-1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedStr = br.readLine().split(" ");
        int n = Integer.parseInt(splitedStr[0]);
        int m = Integer.parseInt(splitedStr[1]);
        int k = Integer.parseInt(splitedStr[2]);
        int c = Integer.parseInt(splitedStr[3]);
        int answer = 0;
        int[][] map = new int[n][n];  // 벽은 최대 년 수인 1000보다 더 큰 -2000으로 설정하였다.
        for(int i = 0; i < n; i++){
            splitedStr = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(splitedStr[j]);
                if (num > 0) map[i][j] = num;
                else if (num == -1) map[i][j] = -2000;
            }
        }
        for (int y = 0; y < m; y++){
            // 나무의 성장
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if (map[i][j] > 0){
                        for(int d = 0; d < 4; d++){
                            int newR = i + dr[d];
                            int newC = j + dc[d];
                            if(0 <= newR && newR < n && 0 <= newC && newC < n){
                                if(map[newR][newC] > 0) map[newR][newC]++;
                            }
                        }
                    }
                }
            }
            // 나무의 번식
            int[][] tmpMap = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if (map[i][j] > 0){
                        boolean[] isEmptyDirection = new boolean[4];
                        int cnt = 0;
                        for(int d = 0; d < 4; d++){
                            int newR = i + dr[d];
                            int newC = j + dc[d];
                            if(0 <= newR && newR < n && 0 <= newC && newC < n){
                                if(map[newR][newC] == 0) {
                                    cnt++;
                                    isEmptyDirection[d] = true;
                                }
                            }
                        }
                        for(int d = 0; d < 4; d++){
                            if (isEmptyDirection[d]) tmpMap[i + dr[d]][j + dc[d]] += map[i][j] / cnt;
                        }
                    }
                }
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    map[i][j] += tmpMap[i][j]; //tmpMap에 모아서 1번에 적용
                }
            }
            // 제초제 위치 선정
            int[][] sumMap = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if (map[i][j] > 0){
                        sumMap[i][j] += map[i][j];
                        for(int d = 4; d < 8; d++) {
                            for (int a = 1; a <= k; a++) {
                                int newR = i + a * dr[d];
                                int newC = j + a * dc[d];
                                if (!(0 <= newR && newR < n && 0 <= newC && newC < n && map[newR][newC] > 0)) break;
                                sumMap[newR][newC] += map[i][j];
                            }
                        }
                    }
                }
            }
            // 제초작업
            int row = 0, col = 0, maxV = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if (maxV <= sumMap[i][j]){
                        maxV = sumMap[i][j];
                        row = i;
                        col = j;
                    }
                }
            }
            map[row][col] = -c - 1;
            answer += maxV;
            for(int d = 4; d < 8; d++) {
                for (int a = 1; a <= k; a++) {
                    int newR = row + a * dr[d];
                    int newC = col + a * dc[d];
                    if (!(0 <= newR && newR < n && 0 <= newC && newC < n && map[newR][newC] >= 0)) break;
                    map[newR][newC] = -c - 1;
                }
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if (map[i][j] < 0) map[i][j]++;
                }
            }
        }
        System.out.println(answer);
    }
}