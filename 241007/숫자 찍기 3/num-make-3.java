import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = makeMap(n);
        if (m == 1) printType1(n, map);
        else if (m == 2) printType2(n, map);
        else if (m == 3) printType3(n, map);
    }

    // 종류1 2차원 배열 만들어주는 함수
    static int[][] makeMap(int n) {
        int[][] map = new int[n + 1][n + 1];
        map[1][1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                map[i][j] = map[i-1][j-1] + map[i-1][j];
            }
        }
        return map;
    }

    // 종류1 출력하는 함수
    static void printType1(int n, int[][] map) {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                System.out.print(map[i][j] + " ");
            }
                System.out.println();
        }
    }

    // 종류2 출력하는 함수
    static void printType2(int n, int[][] map) {
        for (int i = n; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                if (map[i][j] == 0) System.out.print(" ");
                else System.out.print(map[i][j] + " ");
            }
                System.out.println();
        }
    }

    // 종류3 출력하는 함수
    static void printType3(int n, int[][] map) {
        for (int i = n; i > 0; i--) {
            for (int j = n; j > i - 1; j--) {
                System.out.print(map[j][i] + " ");
            }
                System.out.println();
        }
    }
}