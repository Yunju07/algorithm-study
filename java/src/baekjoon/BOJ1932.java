import java.io.*;
import java.util.*;

// 정수 삼각형
public class BOJ1932 {
    static int arr[][], dp[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arr = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N+1][N+1];
        dp[1][1] = arr[1][1];
        for(int i=2; i<=N; i++) {
            for(int j=1; j<=i; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
            }
        }

        int result=0;
        for(int i=1; i<=N; i++) {
            result = Math.max(result, dp[N][i]);

        }

        bw.write(result+" ");
        bw.flush();
    }

}
