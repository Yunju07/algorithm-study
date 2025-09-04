import java.util.*;
import java.lang.*;
import java.io.*;


// 주사위 굴리기 2
class BOJ23288 {
    static int N, M, K, dfs_count;
    static int[][] board, dice, visited;
    static int[][] forward = new int[4][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 보드판 초기화
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N+1][M+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                int a = Integer.parseInt(st.nextToken());
                board[i][j] = a;
            }
        }

        // 주사위의 초기 위치와 방향
        int r = 1;
        int c = 1;
        int d = 1;// 북:0 동:1 남:2 서:3

        // 주사위 전개도
        dice = new int[4][3];
        dice[0] = new int[]{0, 2, 0};
        dice[1] = new int[]{4, 1, 3};
        dice[2] = new int[]{0, 5, 0};
        dice[3] = new int[]{0, 6, 0};

        // 동서남북 방향키 설정 (전진)
        forward[0] = new int[]{-1, 0};
        forward[1] = new int[]{0, 1};
        forward[2] = new int[]{1, 0};
        forward[3] = new int[]{0, -1};

        // K번 수행
        int result = 0;
        for(int k=0; k<K; k++) {
            // 1. 주사위 이동
            if(!isOk(r+forward[d][0], c+forward[d][1])) {
                d = (d+2) % 4; // 반대 방향으로 이동

            }
            move(d);
            r = r+forward[d][0];
            c = c+forward[d][1];

            // 2. 점수 획득
            result += calculate(r, c);

            // 3. 이동 방향 결정
            int A = dice[3][1]; // 주사위 아랫면
            int B = board[r][c];

            if(A > B) {
                d = (d+1) % 4;

            } else if(A < B) {
                d = d-1;
                if(d == -1) {d = 3;}
            }
        }

        bw.write(result+" ");
        bw.flush();

    }

    // 칸 x,y에 대한 점수 구하기
    public static int calculate(int x, int y) {
        int n = board[x][y];

        // dfs로 주변 연속된 칸 갯수 구하기
        visited = new int[N+1][M+1];
        dfs_count = 0;
        dfs(x, y, n);

        return dfs_count * n;
    }

    public static void dfs(int r, int c, int n) {
        if(!isOk(r, c) || visited[r][c] != 0) {
            return;
        }
        visited[r][c] = 1;

        if(board[r][c] == n) {
            dfs_count += 1;

            // 동서남북 방향으로 탐색
            for(int[] d: forward) {
                int nr = r+d[0];
                int nc = c+d[1];
                dfs(nr, nc, n);

            }
        }
    }

    // 주사위 굴리기 d 방향으로 1칸
    public static void move(int direction) {
        int a = dice[0][1];
        int b = dice[1][0];
        int c = dice[1][1];
        int d = dice[1][2];
        int e = dice[2][1];
        int f = dice[3][1];

        // 북:0 동:1 남:2 서:3
        if(direction == 0) {
            dice[0][1] = c;
            dice[1][0] = b;
            dice[1][1] = e;
            dice[1][2] = d;
            dice[2][1] = f;
            dice[3][1] = a;
        } else if(direction == 1) {
            dice[0][1] = a;
            dice[1][0] = f;
            dice[1][1] = b;
            dice[1][2] = c;
            dice[2][1] = e;
            dice[3][1] = d;
        } else if(direction == 2) {
            dice[0][1] = f;
            dice[1][0] = b;
            dice[1][1] = a;
            dice[1][2] = d;
            dice[2][1] = c;
            dice[3][1] = e;
        } else {
            dice[0][1] = a;
            dice[1][0] = c;
            dice[1][1] = d;
            dice[1][2] = f;
            dice[2][1] = e;
            dice[3][1] = b;
        }
    }

    // 좌표의 유효성 검사
    public static boolean isOk(int r, int c) {
        if(1<=r && r<=N && 1<=c && c<=M) {
            return true;
        }
        return false;
    }
}