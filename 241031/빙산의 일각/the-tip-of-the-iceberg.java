import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        TreeSet<Integer> userSet = new TreeSet<>();  // 빙산의 높이가 높은 순서대로 정렬하되, 중복되는 높이는 제거하기 위해서 TreeSet을 사용
        HashMap<Integer, Integer> userMap = new HashMap<>();    // 배열에서 빙산이 몇 번째로 높은지를 확인하기 위해서 HashMap을 사용
        int[] arr = new int[n + 2];
        ArrayList<ArrayList<Integer>> indexArr = new ArrayList<>(n + 2);
        boolean[] visited = new boolean[n + 2];

        for (int i = 0; i <= n + 1; i++) {
            indexArr.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            userSet.add(arr[i]);
        }

        int idx = 0;
        for (int elem : userSet.descendingSet()) {
            userMap.put(elem, idx++);
        }

        for (int i = 1; i <= n; i++) {
            indexArr.get(userMap.get(arr[i])).add(i);
        }

        int result = 0;
        int userSetSize = userSet.size();

        for (int i = userSetSize - 1; i >= 0; i--) { // 레벨(빙산의 높이)이 높은 순으로 loop
            for (int curIdx : indexArr.get(i)) {
                if (!visited[curIdx - 1] && !visited[curIdx + 1]) {  // 양 옆 빙산이 모두 체크되지 않았을 경우
                    result++;
                } else if (!visited[curIdx - 1]) { // 왼쪽 빙산만 체크된 경우
                    // 아무 작업 없음
                } else if (!visited[curIdx + 1]) {    // 오른쪽 빙산만 체크된 경우
                    // 아무 작업 없음
                } else {   // 양 옆 빙산이 모두 체크된 경우
                    result--;
                }
                visited[curIdx] = true;    // 현재 빙산을 체크
            }

            answer = Math.max(answer, result);
        }

        System.out.println(answer + 1);
    }
}