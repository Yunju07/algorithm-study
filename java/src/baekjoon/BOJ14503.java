import java.util.*;
import java.lang.*;
import java.io.*;


// 로봇 청소기
class BOJ14503 {
    static int N, M;
    static int[][] room;
    static int[][] forward = new int[4][2];
    static int[][] backward = new int[4][2];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());   // 0-북 / 1-동 / 2-남 / 3-서

        // 동서남북 방향키 설정 (전진/후진)
        forward[0] = new int[]{-1, 0};
        forward[1] = new int[]{0, 1};
        forward[2] = new int[]{1, 0};
        forward[3] = new int[]{0, -1};

        backward[0] = new int[]{1, 0};
        backward[1] = new int[]{0, -1};
        backward[2] = new int[]{-1, 0};
        backward[3] = new int[]{0, 1};

        // 장소의 상태
        // 0 - 빈칸 / 1 - 벽 / 2 - 청소됨
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int a = Integer.parseInt(st.nextToken());
                room[i][j] = a;
            }
        }

        // 로봇 청소기 작동 반복
        int result = 0;
        while(true) {
            // 1.
            if(room[r][c] == 0) {
                room[r][c] = 2;
                result ++;
                //bw.write(result+"\n");
            }

            // 2.
            if(isClear(r, c)) {
                int nr = r + backward[d][0];
                int nc = c + backward[d][1];
                if(0<=nr && nr<=N-1 && 0<=nc && nc<=M-1) {
                    if(room[nr][nc] == 1) {
                        break;
                    } else {
                        r = nr;
                        c = nc;
                    }
                }

            } else {
                // 3.
                // 90 도 회전
                d -= 1;
                if(d == -1){d = 3;}

                int nr = r + forward[d][0];
                int nc = c + forward[d][1];
                if(0<=nr && nr<=N-1 && 0<=nc && nc<=M-1) {
                    if(room[nr][nc] == 0) {
                        r = nr;
                        c = nc;
                    }
                }
            }
        }

        bw.write(result+" ");
        bw.flush();

    }

    // 현재 칸 주변 4칸 청소 상태 확인
    public static boolean isClear(int r, int c) {
        // 동
        if(0<r && r<N-1 && 0<c+1 && c+1<M-1) {
            if(room[r][c+1] == 0) {
                return false;
            }
        }

        // 서
        if(0<r && r<N-1 && 0<c-1 && c-1<M-1) {
            if(room[r][c-1] == 0) {
                return false;
            }
        }

        // 남
        if(0<r+1 && r+1<N-1 && 0<c && c<M-1) {
            if(room[r+1][c] == 0) {
                return false;
            }
        }

        // 북
        if(0<r-1 && r-1<N-1 && 0<c && c<M-1) {
            if(room[r-1][c] == 0) {
                return false;
            }
        }

        return true;
    }
}