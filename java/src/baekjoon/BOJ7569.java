import java.util.*;
import java.lang.*;
import java.io.*;

// 토마토 - bfs / 3차원
class BOJ7569 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] dir = {
                new int[]{-1, 0, 0},
                new int[]{1, 0, 0},
                new int[]{0, -1, 0},
                new int[]{0, 1, 0},
                new int[]{0, 0, -1},
                new int[]{0, 0, 1}
        };

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] box = new int[H][N][M];
        // 0안익음 1익음 -1토마토없음
        // [r, c, day]
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt((int[] n) -> n[3]));
        int untomato = 0;
        for(int h=0; h<H; h++) {
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    box[h][i][j] = Integer.parseInt(st.nextToken());

                    if(box[h][i][j] == 0) {
                        untomato++;
                    }

                    if(box[h][i][j] == 1) {
                        PQ.add(new int[]{h, i, j, 0});
                    }
                }
            }

        }

        // bfs
        int mindays = 0;
        while(!PQ.isEmpty()) {
            int[] t = PQ.poll();

            // 해당 토마토의 상하좌우 검사
            for(int[] d: dir) {
                int nr = d[0] + t[1];
                int nc = d[1] + t[2];
                int nh = d[2] + t[0];

                if(0<=nr && nr<N && 0<=nc && nc<M && 0<=nh && nh<H) {
                    if(box[nh][nr][nc] == -1) continue;

                    if(box[nh][nr][nc] == 0) {  // 안익은 토마토~
                        box[nh][nr][nc] = 1;
                        PQ.add(new int[]{nh, nr, nc, t[3]+1});
                        untomato--;
                        mindays = Math.max(mindays, t[3]+1);
                    }
                }
            }

        }

        if(untomato!=0) {
            System.out.println(-1);
        } else {
            System.out.println(mindays);
        }
    }
}

