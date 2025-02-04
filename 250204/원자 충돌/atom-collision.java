import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static HashMap<Integer, List<Atom>> map, newMap;

    static class Atom{
        int m, s, d;

        public Atom(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }

        // 원소의 이동 구현
        public void move (int r, int c) {
            int newR = Math.floorMod(r + dr[d] * s, n);
            int newC = Math.floorMod(c + dc[d] * s, n);
            int key = newR * 100 + newC;
            List<Atom> atomList = newMap.getOrDefault(key, new ArrayList<Atom>());
            atomList.add(this);
            newMap.put(key, atomList);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitedInput = br.readLine().split(" ");
        n = Integer.parseInt(splitedInput[0]);
        m = Integer.parseInt(splitedInput[1]);
        k = Integer.parseInt(splitedInput[2]);
        map = new HashMap<Integer, List<Atom>>();
        for (int i = 0; i < m; i++) {
            splitedInput = br.readLine().split(" ");
            int key = Integer.parseInt(splitedInput[0]) * 100 + Integer.parseInt(splitedInput[1]);
            List<Atom> atomList = map.getOrDefault(key, new ArrayList<Atom>());
            atomList.add(new Atom(Integer.parseInt(splitedInput[2]),
                                  Integer.parseInt(splitedInput[3]),
                                  Integer.parseInt(splitedInput[4])));
            map.put(key, atomList);
        }
        for (int i = 0; i < k; i++) {
            newMap = new HashMap<Integer, List<Atom>>();
            for (Map.Entry<Integer, List<Atom>> entry : map.entrySet()) {
                int key = entry.getKey();
                List<Atom> atoms = entry.getValue();
                int r = key / 100;
                int c = key % 100;
                for (Atom atom : atoms) {
                    atom.move(r, c);
                }
            }
            map = (HashMap<Integer, List<Atom>>) newMap.clone();
            fusion();
        }
        int answer = 0;
        for (Map.Entry<Integer, List<Atom>> entry : map.entrySet()) {
            for (Atom atom : entry.getValue()) {
                answer += atom.m;
            }
        }
        System.out.println(answer);
    }

    // 원소의 합성 구현
    static public void fusion() {
    HashMap<Integer, List<Atom>> newMap = new HashMap<>();
    
    for (Map.Entry<Integer, List<Atom>> entry : map.entrySet()) {
        List<Atom> atoms = entry.getValue();
        if (atoms.size() > 1) {
            boolean allEven = true;
            boolean allOdd = true;
            int massSum = 0;
            int speedSum = 0;
            
            for (Atom atom : atoms) {
                if (atom.d % 2 == 0) allOdd = false;
                else allEven = false;
                massSum += atom.m;
                speedSum += atom.s;
            }
            
            int newM = massSum / 5;
            if (newM > 0) {
                int newS = speedSum / atoms.size();
                List<Atom> newAtoms = new ArrayList<>();
                int[] newDirections = (allEven || allOdd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                
                for (int dir : newDirections) {
                    newAtoms.add(new Atom(newM, newS, dir));
                }
                
                newMap.put(entry.getKey(), newAtoms);
            }
        } else {
            newMap.put(entry.getKey(), atoms);
        }
    }
    
    map = newMap;
}


}