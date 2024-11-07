import java.io.*;
import java.util.*;

public class Main {
    // 최대 100,000,000(1억)회 계산 (100 * 1,000,000)
    static boolean[] isRemoved;
    static String S, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        isRemoved = new boolean[S.length()];
        for (int index = 0; index < S.length() - T.length() + 1; index++){
            checkAndRemove(index);
        }
        System.out.println(Arrays.toString(isRemoved));
    }

    // 체크해서 일치하면 이전으로 돌아가 없앤 상태에서 다시 확인해서 지우는 함수
    static void checkAndRemove(int index) {
        // 현재 인덱스부터 뒤로 확인
        for(int i = 0; i < T.length(); i++) {
            if (S.charAt(index + i) != T.charAt(i)) return;
        }
        // 모두 일치하면 제거
        for(int i = 0; i < T.length(); i++) {
            isRemoved[i + index] = true;
        }
        // 제거했으니 앞으로 돌아가서 추가로 삭제 가능한지 확인후 제거
        for(int i = index - T.length() + 1; i < index; i++) {
            // S의 범위 밖이면 return
            if(i < 0 && index + T.length() >= S.length()) return;
            boolean flag = true;
            for(int j = 0; j < T.length(); j++) {
                char now = i + j < index ? S.charAt(i + j) : S.charAt(i + j + T.length());
                if (now != T.charAt(j)) {
                    flag = false; 
                    break;
                }
            }
            if (flag) {
                for(int j = 0; j < T.length(); j++) {
                    int now = i + j < index ? i + j : i + j + T.length();
                    isRemoved[now] = true;
                }
            }
        }
    }

}