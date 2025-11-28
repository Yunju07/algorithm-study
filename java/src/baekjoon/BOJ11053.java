import java.util.*;
import java.lang.*;
import java.io.*;

// 가장 긴 증가하는 부분 수열
class BOJ11053 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            //max = Math.max(max, A[i]);
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for(int i=0; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max = 0;
        for(int a: dp) {
            max = Math.max(max, a);
        }
        
        bw.write(max+" ");
        bw.flush();
        
    }
}
