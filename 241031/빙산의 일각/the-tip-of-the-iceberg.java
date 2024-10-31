import java.util.*;
import java.io.*;

public class Main {
    static class Point implements Comparable<Point> {
        int height, index;
        boolean isStart;
        
        Point(int height, int index, boolean isStart) {
            this.height = height;
            this.index = index;
            this.isStart = isStart;
        }
        
        @Override
        public int compareTo(Point other) {
            if (this.height != other.height)
                return Integer.compare(other.height, this.height);  // 높이 내림차순
            if (this.isStart != other.isStart)
                return this.isStart ? -1 : 1;  // 시작점 우선
            return Integer.compare(this.index, other.index);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            points.add(new Point(height, i, true));
            points.add(new Point(height, i, false));
        }
        
        Collections.sort(points);
        
        int maxChunks = 0;
        int currentChunks = 0;
        TreeSet<Integer> activeIndices = new TreeSet<>();
        
        for (Point p : points) {
            if (p.isStart) {
                if (activeIndices.isEmpty() || p.index > activeIndices.last()) {
                    currentChunks++;
                }
                activeIndices.add(p.index);
            } else {
                activeIndices.remove(p.index);
                if (activeIndices.isEmpty() || p.index > activeIndices.last()) {
                    currentChunks--;
                }
            }
            maxChunks = Math.max(maxChunks, currentChunks);
        }
        
        System.out.println(maxChunks);
    }
}