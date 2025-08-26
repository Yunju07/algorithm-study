import java.io.*;
import java.util.*;

// 앱
public class BOJ7579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] apps = new int[N+1][2];
        int C = 0;

        StringTokenizer memories = new StringTokenizer(br.readLine());
        StringTokenizer costs = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            apps[i][0] = Integer.parseInt(memories.nextToken());
            apps[i][1] = Integer.parseInt(costs.nextToken());
            C += apps[i][1];
        }

        long[][] dp = new long[N+1][C+1];
        for(int a=1; a<=N; a++) {
            for(int c=0; c<=C; c++) {
                dp[a][c] = dp[a-1][c];

                // 끌래 안끌래
                int m = apps[a][0];
                int cost = apps[a][1];

                if(c-cost >= 0) {
                    // 라면 끌 수도 있다
                    dp[a][c] = Math.max(dp[a][c], dp[a-1][c-cost] + m);

                }
            }
        }

        // M 바이트 확보할 수 있는 최소의 cost
        int cost = C;
        for(int c=1; c<=C; c++) {
            if (dp[N][c] >= M) {
                cost = c;
                break;
            }
        }
        bw.write(cost+" ");
        bw.flush();

    }
}
