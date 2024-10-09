import java.util.*;
import java.io.*;

public class Main {
    static int[][] direction = {{0, 0}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};
    static int[][] direction2 = {{-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[][] map = new int[n][n];
        boolean[][] before = new boolean[n][n];
        boolean[][] after = new boolean[n][n];
        before[n-1][0] = true;
        before[n-2][0] = true;
        before[n-1][1] = true;
        before[n-2][1] = true;
        for (int i = 0; i < n ; i++) {
            line = br.readLine().split(" ");
            for(int j = 0; j < n ; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            moveSupplements(before, after, 1, 3, n);
            giveSupplements(map, after, n);
            cutTree(before, after, map, n);
        }
        System.out.println(countTree(map, n));
        
    }

    // 영양제 이동 메소드 (after는 비어있고, before는 새로운 영양제로 초기화된 상태에서)
    // before는 after채워 넣을 때 빈배열로 초기화
    // after는 나무 잘라낼때 초기화(영양제를 준 나무 제외하서 그때까지 필요)
    static void moveSupplements(boolean[][] before, boolean[][] after, int d, int p, int n){
        int newI, newJ;
        for (int i = 0; i < n ; i++) {
            for(int j = 0; j < n ; j++) {
                if (before[i][j]) {
                    before[i][j] = false;
                    newI = (i + p * direction[d][0] + n) % n;
                    newJ = (j + p * direction[d][1] + n) % n;
                    after[newI][newJ] = true;
                }
            }
        }
    }
    
    // 영양제 주어 성장하는 메소드
    static void giveSupplements(int[][] map, boolean[][] after, int n){
        int count, newI, newJ;
        for (int i = 0; i < n ; i++) {
            for(int j = 0; j < n ; j++) {
                if (after[i][j]) {
                    count = 0;
                    for(int d = 0; d < 4; d++) {
                        newI = i + direction2[d][0];
                        newJ = j + direction2[d][1];
                        if (0 <= newI && newI < n && 0 <= newJ && newJ < n){
                            if(map[newI][newJ] > 0){
                                count++;
                            } 
                        }
                    }
                    map[i][j] += count + 1; 
                }
            }
        }
    }

    // 나무 잘라내서 영양제 추가하는 메소드
    static void cutTree(boolean[][] before, boolean[][] after, int[][] map, int n){
        for (int i = 0; i < n ; i++) {
            for(int j = 0; j < n ; j++) {
                if (!after[i][j]){
                    if (map[i][j] > 1) {
                        map[i][j] -= 2;
                        before[i][j] = true;
                    }
                } else after[i][j] = false;
            }
        }
    }
    static int countTree(int[][] map, int n){
        int count = 0;
        for (int i = 0; i < n ; i++) {
            for(int j = 0; j < n ; j++) {
                count += map[i][j];
            }
        }
        return count;
    }
}