import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static boolean[][] matrix;

    public static class Position {
        int r, c;
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 리스트를 뒤로 순회하는 방식으로 수정 필요
    static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directionList = new ArrayList<>();
        matrix[x][y] = true;
        x = x + dr[d];
        y = y + dc[d];
        matrix[x][y] = true;
        directionList.add(d);
        for(int i = 0; i < g; i++) {
            for(int j = directionList.size() - 1; j >= 0 ; j--) {
                int newD = (directionList.get(j) + 1) % 4;
                x = x + dr[newD];
                y = y + dc[newD];
                matrix[x][y] = true;
                directionList.add(newD);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] splitedInput;
        matrix = new boolean[100][100]; // 점과 선정보를 모두 표시할 것이다.
        for (int i = 0; i < n; i++) {
            splitedInput = br.readLine().split(" ");
            int x = Integer.parseInt(splitedInput[0]);
            int y = Integer.parseInt(splitedInput[1]);
            int d = Integer.parseInt(splitedInput[2]);
            int g = Integer.parseInt(splitedInput[3]);
            drawDragonCurve(x, y, d, g);
        }
        int answer = 0;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if(!matrix[i][j]) continue;
                if(!matrix[i + 1][j]) continue;
                if(!matrix[i][j + 1]) continue;
                if(!matrix[i + 1][j + 1]) continue;
                answer++;
            }
        }
        System.out.println(answer);
        // System.out.println(Arrays.deepToString(matrix));

    } 


}