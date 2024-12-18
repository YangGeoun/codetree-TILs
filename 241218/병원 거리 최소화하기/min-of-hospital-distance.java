import java.io.*;
import java.util.*;

public class Main {
    static int n, m, numOfHospital, min = Integer.MAX_VALUE;
    static boolean[] selected;
    static List<Position> personList, hospitalList;
    static class Position {
        int r, c;

        public Position (int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getDistance(Position p) {
            return Math.abs(this.r - p.r) + Math.abs(this.c - p.c);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        int[][] matrix = new int[n][n];
        personList = new ArrayList<>();
        hospitalList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                int num = Integer.parseInt(line[j]);
                matrix[i][j] = num;
                if (num == 1) personList.add(new Position(i, j));
                else if (num == 2) hospitalList.add(new Position(i, j));
            }
        }
        numOfHospital = hospitalList.size();
        selected = new boolean[numOfHospital];
        solve(0, 0);
        System.out.println(min);
    }

    // 병원 선택정보가 담긴 arr 순열 만드는 함수
    static void solve(int index, int numOfSelect) {
        if (index == numOfHospital) {
            if (numOfSelect == m) {
                int sum = 0;
                for (int i = 0; i < personList.size(); i++) {
                    int oneMIN = Integer.MAX_VALUE;
                    for (int j = 0; j < numOfHospital; j++) {
                        if (selected[j]) {
                            int distance = personList.get(i).getDistance(hospitalList.get(j));
                            oneMIN = Math.min(oneMIN, distance);
                        }
                    }
                    sum += oneMIN;
                }
                min = Math.min(min, sum);
            }
            return;
        }
        if (numOfSelect <= m){
            selected[index] = true;
            solve(index + 1, numOfSelect + 1);
            selected[index] = false;
        }
        solve(index + 1, numOfSelect);
    }
}