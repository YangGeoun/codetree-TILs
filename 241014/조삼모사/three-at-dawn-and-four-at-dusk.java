import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] matrix;
    static boolean[] checked;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        checked = new boolean[n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        combi(0, 0);
        System.out.println(minDiff);
    }

    static void combi(int index, int count) {
        if (count == n / 2) {
            checkDiff();
            return;
        }
        if (index == n) return;

        checked[index] = true;
        combi(index + 1, count + 1);
        checked[index] = false;
        combi(index + 1, count);
    }

    static void checkDiff() {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (checked[i] && checked[j]) {
                    sum1 += matrix[i][j] + matrix[j][i];
                } else if (!checked[i] && !checked[j]) {
                    sum2 += matrix[i][j] + matrix[j][i];
                }
            }
        }
        int diff = Math.abs(sum1 - sum2);
        minDiff = Math.min(minDiff, diff);
    }
}