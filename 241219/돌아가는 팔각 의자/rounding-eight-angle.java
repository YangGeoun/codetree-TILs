import java.io.*;
import java.util.*;

public class Main {
    static Stack<Table> LeftTableStack = new Stack<>();
    static Stack<Table> rightTableStack = new Stack<>();

    static class Table {
        boolean[] isNorthList;   // 오른쪽은 [2], 왼쪽은 [6]
        Table leftTable, rightTable;

        public Table (String str) {
            boolean[] isNorthList = new boolean[8];
            for (int i = 0; i < 8; i++) {
                isNorthList[i] = str.charAt(i) == '1';
            }
            this.isNorthList = isNorthList;
        }

        public void spin(int direction) {
            if (direction == 1) {
                boolean tmp = this.isNorthList[7];
                for (int i = 6; i >= 0; i--) {
                    this.isNorthList[i + 1] = this.isNorthList[i];
                }
                this.isNorthList[0] = tmp;
            } else {
                boolean tmp = this.isNorthList[0];
                for (int i = 0; i < 7; i++) {
                    this.isNorthList[i] = this.isNorthList[i + 1];
                }
                this.isNorthList[7] = tmp;
            }
        }

        public void checkLeft(int direction) {
            if (this.leftTable != null && this.isNorthList[6] != this.leftTable.isNorthList[2]) {
                if (direction == 1) {
                    LeftTableStack.push(this.leftTable);
                    this.leftTable.checkLeft(-1);
                } else {
                    rightTableStack.push(this.leftTable);
                    this.leftTable.checkLeft(1);
                }
            }
        }
        
        public void checkRight(int direction) {
            if (this.rightTable != null && this.isNorthList[2] != this.rightTable.isNorthList[6]) {
                if (direction == 1) {
                    LeftTableStack.push(this.rightTable);
                    this.rightTable.checkRight(-1);
                } else {
                    rightTableStack.push(this.rightTable);
                    this.rightTable.checkRight(1);
                }
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(this.isNorthList);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        List<Table> tableList = new ArrayList<>();
        Table right, left = new Table(input);
        tableList.add(left);
        for (int i = 0; i < 3; i++) {
            input = br.readLine();
            right = new Table(input);
            left.rightTable = right;
            right.leftTable = left;
            left = right;
            tableList.add(left);
        }
        int k = Integer.parseInt(br.readLine());
        for(int i = 0; i < k; i++) {
            String[] splitedInput = br.readLine().split(" ");
            int tableNum = Integer.parseInt(splitedInput[0]);
            int direction = Integer.parseInt(splitedInput[1]);
            Table table = tableList.get(tableNum - 1);
            table.checkLeft(direction);
            table.checkRight(direction);
            table.spin(direction);
            while (!LeftTableStack.isEmpty()) {
                LeftTableStack.pop().spin(-1);
            }
            while (!rightTableStack.isEmpty()) {
                rightTableStack.pop().spin(1);
            }
        } 

        int answer = 0;
        for(int i = 0; i < 4; i++) {
            if (tableList.get(i).isNorthList[0]) {
                answer += (int) Math.pow(2, i);
            }
        } 
        System.out.println(answer);

    }
}