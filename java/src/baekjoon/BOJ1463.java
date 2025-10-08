import java.util.*;
import java.lang.*;
import java.io.*;

// 1로 만들기 - dp
class BOJ1462 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // dp[i] - i를 1로 만드는데 드는 연산 횟수 

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        dp[1] = 0;

        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-1] + 1;    // 1을 뺀다

            if(i%2 == 0) {
                int idx = i/2;
                dp[i] = Math.min(dp[idx]+1, dp[i]);
            }

            if(i%3 == 0) {
                int idx = i/3;
                dp[i] = Math.min(dp[idx]+1, dp[i]);
            } 
        }

        bw.write(dp[N]+" ");
        bw.flush();
    }
}
