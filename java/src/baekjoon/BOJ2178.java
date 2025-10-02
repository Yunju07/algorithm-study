import java.util.*;
import java.lang.*;
import java.io.*;

// 미로탐색 - bfs
class BOJ2178 {
    static int N,M;
    static int[][] maps, dir;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        dir = new int[4][2];
        dir[0] = new int[]{-1, 0};
        dir[1] = new int[]{1, 0};
        dir[2] = new int[]{0, -1};
        dir[3] = new int[]{0, 1};

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        for(int i=0; i<N; i++) {
            String[] s = br.readLine().split("");
            for(int j=0; j<M; j++) {
                maps[i][j] = Integer.parseInt(s[j]);
            }
        }

        // [r, c, move_count]
        Queue<int[]> que = new LinkedList<int[]>();
        que.add(new int[]{0, 0, 1});

        visited = new boolean[N][M];

        int r, c, move;
        int result = -1;
        while(!que.isEmpty()){
            int[] point = que.poll();
            r = point[0];
            c = point[1];
            move = point[2];

            if(r==N-1 && c==M-1) {
                result = move;
                break;
            }
            
            if(visited[r][c]) {
                continue;
            }
            visited[r][c] = true;

            for(int[] d: dir) {
                int nr = r+d[0];
                int nc = c+d[1];

                if(0<=nr && nr<N && 0<=nc && nc<M) {
                    if(maps[nr][nc] == 1) {
                        que.add(new int[]{nr, nc, move+1});    
                    }
                }
            }
        }

        bw.write(result+"\n");
        bw.flush();
    }
}
