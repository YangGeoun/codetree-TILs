import java.io.*;
import java.util.*;

public class Main {
    static int n, m, x, y, k;
    static int[][] matrix;
    static class Dice {
        int front = 0, back = 0, left = 0, right = 0, top = 0, bottom = 0;
        // int front = 1, back = 2, left = 3, right = 4, top = 5, bottom = 6;
        int x ,y;

        public Dice(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "front = " + this.front +", back = " + this.back +", left = " + this.left +", right = " + this.right +", top = " + this.top +", bottom = " + this.bottom;
        }

        public void roll(char d) {
            switch (d) {
                case '1': rollEast();
                        break;
                case '2': rollWest();
                        break;
                case '3': rollSouth();
                        break;
                case '4': rollNorth();
                        break;

            }
        }

        private void rollEast() {
            if (this.y + 1 >= m) return;
            this.y++;
            int tmp = this.top;
            this.top = this.left;
            this.left = this.bottom;
            this.bottom = this.right;
            this.right = tmp;
            if (matrix[this.x][this.y] == 0) {
                matrix[this.x][this.y] = this.bottom;
            }
            else {
                this.bottom = matrix[this.x][this.y];
                matrix[this.x][this.y] = 0;
            }
            System.out.println(this.top);
        }

        private void rollWest() {
            if (y - 1 < 0) return;
            this.y--;
            int tmp = this.top;
            this.top = this.right;
            this.right = this.bottom;
            this.bottom = this.left;
            this.left = tmp;
            if (matrix[this.x][this.y] == 0) {
                matrix[this.x][this.y] = this.bottom;
            }
            else {
                this.bottom = matrix[this.x][this.y];
                matrix[this.x][this.y] = 0;
            }
            System.out.println(this.top);
        }

        private void rollSouth() {
            if (x - 1 < 0) return;
            this.x--;
            int tmp = this.top;
            this.top = this.back;
            this.back = this.bottom;
            this.bottom = this.front;
            this.front = tmp;
            if (matrix[this.x][this.y] == 0) {
                matrix[this.x][this.y] = this.bottom;
            }
            else {
                this.bottom = matrix[this.x][this.y];
                matrix[this.x][this.y] = 0;
            }
            System.out.println(this.top);
        }

        private void rollNorth() {
            if (x + 1 >= n) return;
            this.x++;
            int tmp = this.top;
            this.top = this.front;
            this.front = this.bottom;
            this.bottom = this.back;
            this.back = tmp;
            if (matrix[this.x][this.y] == 0) {
                matrix[this.x][this.y] = this.bottom;
            }
            else {
                this.bottom = matrix[this.x][this.y];
                matrix[this.x][this.y] = 0;
            }
            System.out.println(this.top);
        }
    }

    // Dice 클래스를 만드고 각 이동의 메소드 구현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedInput = br.readLine().split(" ");
        n = Integer.parseInt(splitedInput[0]);
        m = Integer.parseInt(splitedInput[1]);
        x = Integer.parseInt(splitedInput[2]);
        y = Integer.parseInt(splitedInput[3]);
        k = Integer.parseInt(splitedInput[4]);
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            splitedInput = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(splitedInput[j]);
            }
        }
        Dice dice = new Dice(x, y);
        String input = br.readLine();
        for (int i = 0; i < input.length(); i = i + 2) {
            dice.roll(input.charAt(i));
        }

    }
}