import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static int d = 4, r = 0, n, m, max = 0;
    static Chess[] chessArr = new Chess[8];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] matrix;
    static Map<Integer, List<Position>[]> positionMap = new HashMap<>();

    record Position(int r, int c) {}
    record Chess(int r, int c, int kind) {}

    public static void generatePermutations(List<Integer> current) {
        if (current.size() == r) {
            max = Math.max(max, check(current));
            return;
        }
        for (int i = 0; i < d; i++) {
            List<Integer> next = new ArrayList<>(current);
            next.add(i);
            generatePermutations(next);
        }
    }

    static void fillHashMap() {
        for (int i = 0; i < r; i++) {
            Chess chess = chessArr[i];
            List<Position>[] tmp = new ArrayList[4];
            for (int j = 0; j < 4; j++) {
                tmp[j] = new ArrayList<>();
            }
            
            for (int j = 0; j < 4; j++) {
                int directions = chess.kind == 5 ? 4 : (chess.kind == 2 ? 2 : 1);
                for (int k = 0; k < directions; k++) {
                    int dir = chess.kind == 2 ? (j / 2) * 2 + k : (j + k) % 4;
                    int newR = chess.r + dr[dir];
                    int newC = chess.c + dc[dir];
                    while ((0 <= newR && newR < n && 0 <= newC && newC < m) && matrix[newR][newC] != 6) {
                        if (matrix[newR][newC] == 0) {
                            tmp[j].add(new Position(newR, newC));
                        }
                        newR += dr[dir];
                        newC += dc[dir];
                    }
                }
                if (chess.kind == 2) {
                    tmp[j + 2] = tmp[j];
                    j++;
                } else if (chess.kind == 5) {
                    tmp[1] = tmp[2] = tmp[3] = tmp[0];
                    break;
                }
            }
            positionMap.put(i, tmp);
        }
    }

    static int check(List<Integer> directionList) {
        return (int) IntStream.range(0, r)
            .flatMap(i -> positionMap.get(i)[directionList.get(i)].stream()
                .mapToInt(p -> p.r * m + p.c))
            .distinct()
            .count();
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
        generatePermutations(new ArrayList<>());
        System.out.println(answer - max);
    }
}
