import java.io.*;
import java.util.*;

// 플로이드
public class BOJ11404 {
	static long[][] dist;
	static long INF = Long.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		dist = new long[n+1][n+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				dist[i][j] = INF;
			}
		}

		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			dist[a][b] = Math.min(c, dist[a][b]);
		}

		// 플로이드 워셜
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if (i == j || i == k || j == k) {
						continue;
					}
					if (dist[i][k] == INF || dist[k][j] == INF) {
						continue;
					}
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		// 출력
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(dist[i][j] == INF) {
					bw.write("0 ");
				} else {
					bw.write(dist[i][j]+ " ");
				}
			}
			bw.write("\n");
		}
		bw.flush();
	}

}
