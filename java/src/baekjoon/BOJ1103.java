import java.io.*;
import java.util.*;

// 게임
// 가장 왼쪽 위에 동전을 하나 올려놓는다
// 동전이 있는 곳에 쓰여 있는 숫자 X를 본다.
// 위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
// 동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.
public class BOJ1103 {
    static int N, M;
    static String[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static boolean isInfinite = false;

    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = String.valueOf(row.charAt(j));
            }
        }

        int result = dfs(0, 0);

        System.out.println(isInfinite ? -1 : result);
    }

    public static int dfs(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M || map[r][c].equals("H")) {
            return 0;
        }

        if (visited[r][c]) {
            isInfinite = true; // 사이클 감지
            return 0;
        }

        if (dp[r][c] != 0) return dp[r][c];

        visited[r][c] = true;
        int move = Integer.parseInt(map[r][c]);
        int max = 0;

        for (int[] d : dir) {
            int nr = r + d[0] * move;
            int nc = c + d[1] * move;

            int next = dfs(nr, nc);
            if (isInfinite) return 0;

            max = Math.max(max, next);
        }

        visited[r][c] = false;
        dp[r][c] = max + 1;
        return dp[r][c];
    }
}
