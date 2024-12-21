import java.io.*;
import java.util.*;

public class Main {
    static int n, m, cnt, minValue = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] matrix;
    static boolean[] selectedArr;
    static List<Position> hospitalPositionList;

    static class Position {
        int r, c;

        public Position (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void makeSelectedArr(int index, int count) {
        if (count == m) {
            minValue = Math.min(minValue, bfs());
            return;
        }
        if (index == selectedArr.length) return;
        selectedArr[index] = true;
        makeSelectedArr(index + 1, count + 1);
        selectedArr[index] = false;
        makeSelectedArr(index + 1, count);
    }

    static int bfs() {
        Queue<Position> q = new LinkedList<>();
        int[][] visited = new int[n][n];
        for (int i = 0; i < selectedArr.length; i++) {
            if (selectedArr[i]) {
                Position start = hospitalPositionList.get(i);
                q.add(start);
                visited[start.r][start.c] = 1;
            }
        }
        int count = 0;
        int maxVisited = 1;
        while (!q.isEmpty()) {
            if (count >= cnt) break;
            Position now = q.poll();
            for (int d = 0; d < 4; d++) {
                int newR = now.r + dr[d];
                int newC = now.c + dc[d];
                if(0 <= newR && newR < n && 0 <= newC && newC < n) {
                    if (matrix[newR][newC] != 1 && visited[newR][newC] == 0) {
                        q.add(new Position(newR, newC));
                        int nextVisited = visited[now.r][now.c] + 1;
                        visited[newR][newC] = nextVisited;
                        maxVisited = nextVisited;
                        if (matrix[newR][newC] == 0) count++;
                    }
                }
            }
        }
        if (count != cnt) return Integer.MAX_VALUE;
        return maxVisited - 1;
    } 

    // 모든 병원에서 bfs이후 최소로 업데이트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedInput = br.readLine().split(" ");
        n = Integer.parseInt(splitedInput[0]);
        m = Integer.parseInt(splitedInput[1]);
        hospitalPositionList = new ArrayList<>();
        matrix = new int[n][n];
        cnt = 0;
        for (int i = 0; i < n; i++) {
            splitedInput = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int tmp = Integer.parseInt(splitedInput[j]);
                matrix[i][j] = tmp;
                if(tmp == 2) hospitalPositionList.add(new Position(i, j));
                if(tmp == 0) cnt++;
            }
        }
        selectedArr = new boolean[hospitalPositionList.size()];
        makeSelectedArr(0, 0);
        if (minValue == Integer.MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(minValue);
        }
    }
}