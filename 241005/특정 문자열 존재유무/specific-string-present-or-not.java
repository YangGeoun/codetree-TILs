import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String str = st.nextToken();
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            boolean found = false;
            for (int j = 0; j <= line.length() - str.length(); j++) {
                boolean flag = true;
                for (int k = 0; k < str.length(); k++) {
                    if (line.charAt(j + k) != str.charAt(k)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    found = true;
                    break;
                }
            }
            if (found) {
                answer.add(line);
            }
        }

        answer.forEach(System.out::println);
    }
}