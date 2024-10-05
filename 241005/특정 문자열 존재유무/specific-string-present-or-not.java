import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        String s = st.nextToken();
        for(int i=0;i<N;i++){
            String input = in.readLine();
            if(input.contains(s)){
                System.out.println(input);
            }
        }
        
    }
}