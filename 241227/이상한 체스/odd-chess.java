import java.util.*;
import java.io.*;

public class Main {
    //상우하좌
    static boolean canGo[][][] = {
        {},
        {
            {true,false,false,false},
            {false,true,false,false},
            {false,false,true,false},
            {false,false,false,true}
        },
        {
            {true,false,true,false},
            {false,true,false,true}
        },
        {
            {true,true,false,false},
            {false,true,true,false},
            {false,false,true,true},
            {true,false,false,true}
        },
        {
            {true,true,false,true},
            {true,true,true,false},
            {false,true,true,true},
            {true,false,true,true}
        },
        {
            {true,true,true,true}
        }
    };
    static class Pos{
        int type;
        int x;
        int y;
        public Pos(int x, int y,int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
        public int HashCode(){
            return Objects.hash(type,x,y);
        }
        public boolean equals(Object o){
            Pos p = (Pos) o;
            if(p.x == this.x && p.y == this.y && p.type == this.type){
                return true;
            }
            return false;
        }
    }
    static ArrayList<Pos> list;
    static int[][] map;
    static int[][] origin;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visited;
    static int ans = Integer.MAX_VALUE;
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        N = n;
        M = m;
        map = new int[n][m];
        origin = new int[n][m];
        visited = new boolean[n][m];
        list = new ArrayList<Pos>();

        int size = n*m;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = origin[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0 && map[i][j] < 6){
                    list.add(new Pos(i,j,map[i][j]));
                    size--;
                }else if(map[i][j] == 6){
                    size--;
                }
            }
        }
        dfs(0,map,size);
        System.out.println(ans);
    }

    public static void dfs(int dep,int[][] map, int size){
        if(dep == list.size()){
            ans = Math.min(ans, size);
            return;
        }
        
        Pos p = list.get(dep);
        int[][] map2 = new int[N][M];
        for(int i=0; i<N; i++){
            map2[i] = map[i].clone();
        }

        for(int i=0; i<canGo[p.type].length; i++){
            int count = go(p,i,map2);
            dfs(dep+1,map2,size-count);
            for(int j=0; j<N; j++){
                map2[j] = map[j].clone();
            }
        }
    }

    public static int go(Pos p,int i,int[][] map2){
        int count = 0;
        for(int d=0; d<4; d++){
            if(canGo[p.type][i][d]){
                int x = p.x;
                int y = p.y;

                int nx = x;
                int ny = y;
                while(true){
                    nx += dx[d];
                    ny += dy[d];
                    if(nx<0||ny<0||nx>=N||ny>=M||map2[nx][ny] == 6){
                        nx -= dx[d];
                        ny -= dy[d];
                        break;
                    }else if(map2[nx][ny] == 0){
                        map2[nx][ny] = p.type;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}