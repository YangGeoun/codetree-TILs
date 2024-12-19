import java.io.*;
import java.util.*;

public class Main {
    static int n, L, R;
    static int[] dr = {-1, 0, 1, 0}, dc ={0, -1, 0, 1};
    static int[][] matrix, visited, copy;
    
    static class Position {
        int r, c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static void bfs(Position position, int num) {
        Queue<Position> q = new LinkedList<>();
        Stack<Position> positionStack = new Stack<>();
        int count = 1;
        int sum = matrix[position.r][position.c];
        q.add(position);
        positionStack.push(position);
        visited[position.r][position.c] = num;
        while (!q.isEmpty()) {
            Position now = q.poll();
            for (int d = 0; d < 4; d++) {
                int newR = now.r + dr[d];
                int newC = now.c + dc[d];
                if(0 <= newR && newR < n && 0 <= newC && newC < n) {
                    int diff = Math.abs(matrix[now.r][now.c] - matrix[newR][newC]);
                    if (L <= diff && diff <= R) {
                        if (visited[newR][newC] == 0) {
                            Position tmp = new Position(newR, newC);
                            q.add(tmp);
                            positionStack.push(tmp);
                            count++;
                            sum += matrix[newR][newC];
                            visited[newR][newC] = num;
                        }
                    }
                }
            }
        }
        while (!positionStack.isEmpty()){
            Position now = positionStack.pop();
            matrix[now.r][now.c] = sum / count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedInput = br.readLine().split(" ");
        n = Integer.parseInt(splitedInput[0]); 
        L = Integer.parseInt(splitedInput[1]); 
        R = Integer.parseInt(splitedInput[2]);
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            splitedInput = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(splitedInput[j]);
            }
        }
        int answer = 0;
        while (true) {
            copy = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    copy[i][j] = matrix[i][j];
                }
            }
            visited = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        bfs(new Position(i, j), 1);
                    }
                }
            }
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (copy[i][j] != matrix[i][j]){
                        flag = true;
                    }
                }
            }
            if (flag) break;
            answer++;
        }
        System.out.println(answer);
    }

}