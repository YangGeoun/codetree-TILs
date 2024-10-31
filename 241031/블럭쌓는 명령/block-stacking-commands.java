import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] blockList;
    static int[] count;
    // 
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        blockList = new int[N];
        K = Integer.parseInt(line[1]);
        count = new int[K + 1];
        for (int i = 0; i < K; i++) {
            line = br.readLine().split(" ");
            for (int j = Integer.parseInt(line[0]) - 1; j < Integer.parseInt(line[1]); j++) {
                blockList[j]++;
            } 
        }
        for(int el : blockList) {
            count[el]++;
        }
        int index = 0;
        int cnt = 0;
        while(cnt < (N / 2 + 1)) {
            cnt += count[index++];
            
        }
        System.out.println(index - 1);

    }
}