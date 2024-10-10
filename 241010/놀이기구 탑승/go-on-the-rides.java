import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        String[] line;
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < n * n; i++) {
            line = br.readLine().split(" ");
            int num = Integer.parseInt(line[0]);
            int[] tmp = new int[4];
            for (int j = 0; j < 4; j++) {
                tmp[j] = Integer.parseInt(line[j + 1]);
            }
            Student s = new Student(num, tmp);
            studentList.add(s);
        }
        // for (Student s : studentList) {
        //     s.position();
        // }
        studentList.stream().forEach(Student::position);
        // System.out.println(Arrays.deepToString(map));
        int totalPoint = studentList.stream().mapToInt(Student::checkPoint).sum();
        System.out.println(totalPoint);
        
    }

    static class Student {
        int num;
        int[] likeNums;
        int r;
        int c;

        Student(int num, int[] likeNums) {
            this.num = num;
            this.likeNums = likeNums;
        }
        // 자기 자신을 적절한 좌표에 위치시키는 메서드
        void position() {
            int maxCount = -1, maxBlank = -1;
            int newR, newC, count, blank;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (map[r][c] == 0) {
                        count = 0;
                        blank = 0;
                        for (int d = 0; d < 4; d++) {
                            newR = r + dr[d];
                            newC = c + dc[d];
                            if (0 <= newR && newR< n && 0 <= newC && newC < n) {
                                if (map[newR][newC] == 0) blank++;
                                else {
                                    for (int i = 0; i < 4; i++) {
                                        if (map[newR][newC] == this.likeNums[i]) count++;
                                    }
                                }
                            }
                        }
                        if (maxCount < count || (maxCount == count && maxBlank < blank)){
                            maxCount = count;
                            maxBlank = blank;
                            this.r = r;
                            this.c = c;
                        }
                    }
                }
            }
            map[this.r][this.c] = this.num;
        }

        int checkPoint() {
            int count = 0;
            int newR, newC; 
            for (int d = 0; d < 4; d++) {
                newR = this.r + dr[d];
                newC = this.c + dc[d];
                if (0 <= newR && newR< n && 0 <= newC && newC < n) {
                    for (int i = 0; i < 4; i++) {
                        if (map[newR][newC] == this.likeNums[i]) count++;
                    }
                }
            }
            switch(count) {
            case 1: return 1;
            case 2: return 10;
            case 3: return 100;
            case 4: return 1000;
            default: return 0;
        }
        }


    }
    
}