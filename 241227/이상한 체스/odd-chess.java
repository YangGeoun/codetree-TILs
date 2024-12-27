import java.io.*;
import java.util.*;

// 각 말과 방향을 키로 갈수있는 좌표를 값으로 하는 해시맵에 생성하면 빠를 것 같다.
// chessArr 와 directionArr 을 사용해서 풀것이다
// directionArr는 [0, 0, 0] ~ [4, 4 ,4] 까지 만들고
public class Main {
    static int d = 4, r = 0, n, m, max = 0;
    static Chess[] chessArr = new Chess[8];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix;
    static Map<Integer, List<Position>[]> positionMap = new HashMap<>();

    public static class Position {
        int r, c;
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position position = (Position) obj;
            return r == position.r && c == position.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
    public static class Chess {
        int r, c, kind;
        public Chess(int r, int c, int kind) {
            this.r = r;
            this.c = c;
            this.kind = kind;
        }
    }

    public static void generatePermutations() {
        Stack<List<Integer>> stack = new Stack<>();
        stack.push(new ArrayList<>());

        while (!stack.isEmpty()) {
            List<Integer> current = stack.pop();

            if (current.size() == r) {
                // 현재 순열 출력
                max = Math.max(max, check(current));
                continue;
            }

            for (int i = 0; i < d; i++) {
                List<Integer> next = new ArrayList<>(current);
                next.add(i); // 0부터 n-1까지의 숫자를 추가
                stack.push(next);
            }
        }
    }

    static void fillHashMap() {
        for (int i = 0; i < r; i++) {
            Chess chess = chessArr[i];
            List<Position>[] tmp = new ArrayList[4];
            switch(chess.kind) {
                case 1 :
                    for (int j = 0; j < 4; j++){
                        List<Position> tmp2 =  new ArrayList<>();
                        int newR = chess.r + dr[j];
                        int newC = chess.c + dc[j];
                        while((0 <= newR && newR < n && 0 <= newC && newC < m) && matrix[newR][newC] != 6) {
                            if (matrix[newR][newC] == 0) {
                                tmp2.add(new Position(newR, newC));
                            }
                            newR += dr[j];
                            newC += dc[j];
                        }
                        tmp[j] = tmp2;
                    }
                    positionMap.put(i, tmp);
                    break;
                case 2 :
                    for (int j = 0; j < 2; j++){
                        List<Position> tmp2 =  new ArrayList<>();
                        for (int k = 0; k < 2; k++) {
                            int newR = chess.r + dr[j + (2 * k)];
                            int newC = chess.c + dc[j + (2 * k)];
                            while((0 <= newR && newR < n && 0 <= newC && newC < m) && matrix[newR][newC] != 6) {
                                if (matrix[newR][newC] == 0) {
                                    tmp2.add(new Position(newR, newC));
                                }
                                newR += dr[j + (2 * k)];
                                newC += dc[j + (2 * k)];
                            }
                        }
                        tmp[j] = tmp2;
                        tmp[j + 2] = tmp2;
                    }
                    positionMap.put(i, tmp);
                    break;
                case 3 :
                    for (int j = 0; j < 4; j++){
                        List<Position> tmp2 =  new ArrayList<>();
                        for (int k = 0; k < 2; k++) {
                            int newR = chess.r + dr[(j + k) % 4];
                            int newC = chess.c + dc[(j + k) % 4];
                            while((0 <= newR && newR < n && 0 <= newC && newC < m) && matrix[newR][newC] != 6) {
                                if (matrix[newR][newC] == 0) {
                                    tmp2.add(new Position(newR, newC));
                                }
                                newR += dr[(j + k) % 4];
                                newC += dc[(j + k) % 4];
                            }
                        }
                        tmp[j] = tmp2;
                    }
                    positionMap.put(i, tmp);
                    break;
                case 4 :
                    for (int j = 0; j < 4; j++){
                        List<Position> tmp2 =  new ArrayList<>();
                        for (int k = 0; k < 3; k++) {
                            int newR = chess.r + dr[(j + k) % 4];
                            int newC = chess.c + dc[(j + k) % 4];
                            while((0 <= newR && newR < n && 0 <= newC && newC < m) && matrix[newR][newC] != 6) {
                                if (matrix[newR][newC] == 0) {
                                    tmp2.add(new Position(newR, newC));
                                }
                                newR += dr[(j + k) % 4];
                                newC += dc[(j + k) % 4];
                            }
                        }
                        tmp[j] = tmp2;
                    }
                    positionMap.put(i, tmp);
                    break;
                case 5 :
                    List<Position> tmp2 =  new ArrayList<>();
                    for (int j = 0; j < 4; j++){
                        int newR = chess.r + dr[j];
                        int newC = chess.c + dc[j];
                        while((0 <= newR && newR < n && 0 <= newC && newC < m) && matrix[newR][newC] != 6) {
                            if (matrix[newR][newC] == 0) {
                                tmp2.add(new Position(newR, newC));
                            }
                            newR += dr[j];
                            newC += dc[j];
                        }
                    }
                    tmp[0] = tmp2;
                    tmp[1] = tmp2;
                    tmp[2] = tmp2;
                    tmp[3] = tmp2;
                    positionMap.put(i, tmp);
                    break;
            }
        }
    }

    static int check(List<Integer> directionList) {
        Set<Position> positionSet = new HashSet<Position>();
        for (int i = 0; i < r; i++) {
            for (Position p : positionMap.get(i)[directionList.get(i)]){
                positionSet.add(p);
            }
        }
        return positionSet.size();

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedInput = br.readLine().split(" ");
        n = Integer.parseInt(splitedInput[0]);
        m = Integer.parseInt(splitedInput[1]);
        matrix = new int[n][m];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            splitedInput = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(splitedInput[j]);
                matrix[i][j] = tmp;
                if (0 < tmp && tmp < 6) {
                    chessArr[r++] = new Chess(i, j, tmp);
                } 
                else if (tmp == 0) answer++;
            }
        }
        
        fillHashMap();
        List<Integer> tmp = new ArrayList<>();
        generatePermutations();
        System.out.println(answer - max);
    
    }
}
