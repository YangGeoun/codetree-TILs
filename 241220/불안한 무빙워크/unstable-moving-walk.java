import java.io.*;
import java.util.*;

public class Main {
    static int n, k, count;
    static MovingWork movingWork;

    static class Person {
        int index;

        public Person(int index) {
            this.index = index;
        }

        public void move(int beforeIndex) {
            int nextIndex = this.index + 1;
            if (movingWork.safetyArr[nextIndex] > 0 && nextIndex < beforeIndex) {
                this.index = nextIndex;
                movingWork.safetyArr[nextIndex]--;
                if (movingWork.safetyArr[nextIndex] == 0) count++;
            }
        }

    }

    static class MovingWork {
        int[] safetyArr;

        public MovingWork(String[] splitedInput) {
            this.safetyArr = new int[2 * n];
            for (int i = 0; i < 2 * n; i++) {
                this.safetyArr[i] = Integer.parseInt(splitedInput[i]);
            }
        }

        public void move() {
            int tmp = safetyArr[2 * n - 1];
            for (int i = 2 * n - 1; i > 0 * n; i--) {
                safetyArr[i] = safetyArr[i - 1];
            }
            safetyArr[0] = tmp;
        }

        @Override
        public String toString() {
            return Arrays.toString(safetyArr);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedInput = br.readLine().split(" ");
        n = Integer.parseInt(splitedInput[0]);
        k = Integer.parseInt(splitedInput[1]);
        splitedInput = br.readLine().split(" ");
        movingWork = new MovingWork(splitedInput);
        Queue<Person> q = new LinkedList<>();
        count = 0;
        int answer = 0;
        while(count < k) {
            answer++;
            movingWork.move();
            int before = 2 * n;
            for (Person p : q) {
                p.index++;
                if (p.index >= n - 1) continue;
                p.move(before);
                before = p.index;
            }
            if(!q.isEmpty() && q.peek().index >= n - 1) {
                q.poll();
            }
            if(movingWork.safetyArr[0] > 0){
                q.add(new Person(0));
                movingWork.safetyArr[0]--;
                if (movingWork.safetyArr[0] == 0) count++;
            }
        }
        System.out.println(answer);
    }
}