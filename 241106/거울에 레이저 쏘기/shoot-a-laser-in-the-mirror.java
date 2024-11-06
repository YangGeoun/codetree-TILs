import java.io.*;
import java.util.*;

// 모두 to 기준

// '/'면 아래(0) <-> 왼(1), 위(2) <-> 오(3)
// '\'면 아래(0) <-> 오(3), 위(2) <-> 왼(1)

// 빛은 현재 위치와 어디로 가는지 정보를 가지고 있다.
public class Main {
    //               아래, 왼, 위, 오
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int n, m;
    static boolean[][] matrix;   // true 슬레시('/'), false 백슬래시('\')
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        matrix = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line2 = br.readLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = (line2.charAt(j) == '/');
            }
        }
        int maxReflection = 0;
        for (int i = 0; i < n; i++) {
            maxReflection = Math.max(maxReflection, howManyReflection(i,m - 1,1));
            maxReflection = Math.max(maxReflection, howManyReflection(i,0,3));
        }
        for (int i = 0; i < m; i++) {
            maxReflection = Math.max(maxReflection, howManyReflection(0,i,0));
            maxReflection = Math.max(maxReflection, howManyReflection(n - 1,i,2));
        }
        System.out.println(maxReflection);
    }

    // *** todo - 조건문과 try문 비교해보기 ***
    // 시작점 (startR, startC)와 어디로 가는지 direction으로 얼마나 반사되는지 계산하는 함수
    static int howManyReflection(int startR, int startC, int direction){
        int cnt = 0;
        int nowR = startR;
        int nowC = startC;
        while (0 <= nowR && nowR < n && 0 <= nowC && nowC < m) {
            if (matrix[nowR][nowC]) {
                switch(direction){
                    case 0 : direction = 1;
                        break;
                    case 1 : direction = 0;
                        break;
                    case 2 : direction = 3;
                        break;
                    case 3 : direction = 2;
                        break;
                }
            } else {
                switch(direction){
                    case 0 : direction = 3;
                        break;
                    case 1 : direction = 2;
                        break;
                    case 2 : direction = 1;
                        break;
                    case 3 : direction = 0;
                        break;
                }
            }
            nowR = nowR + dr[direction];
            nowC = nowC + dc[direction];
            cnt++;
        }
        return cnt;
    }
    
}