import java.io.*;
import java.util.*;

// DDR
public class BOJ2342 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = st.countTokens()-1;
		int[][][] dp = new int[N+1][5][5];

		for(int i=0; i<=N; i++) {
			for(int L=0; L<5; L++) {
				for(int R=0; R<5; R++) {
					dp[i][L][R] = Integer.MAX_VALUE;
				}
			}
		}

		dp[0][0][0] = 0;	// 초기 상태

		for(int i=1; i<=N+1; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(n == 0) {
				break;
			}

			for(int L=0; L<5; L++) {
				for(int R=0; R<5; R++) {
					if(dp[i-1][L][R] != Integer.MAX_VALUE) {
						dp[i][L][n] = Math.min(dp[i][L][n], dp[i-1][L][R] + cost(R, n));
						dp[i][n][R] = Math.min(dp[i][n][R], dp[i-1][L][R] + cost(L, n));
					}
				}
			}
		}

		int min_power = Integer.MAX_VALUE;
		for(int L=0; L<5; L++) {
			for(int R=0; R<5; R++) {
				if(dp[N][L][R] != Integer.MAX_VALUE) {
					min_power = Math.min(min_power, dp[N][L][R]);
				}
			}
		}

		bw.write(min_power+" ");
		bw.flush();
	}

	public static int cost(int a, int b) {
		if(a == 0) {
			return 2;
		} else if(a == b) {
			return 1;
		} else if(Math.abs(a-b) == 2) {
			return 4;
		} else {
			return 3;
		}
	}

}
