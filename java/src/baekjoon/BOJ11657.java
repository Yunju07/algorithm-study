import java.io.*;
import java.util.*;

// 타임머신
public class BOJ11657 {
	static long dist[];
	static List<int[]> edges;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		dist = new long[N+1];
		for(int i=1; i<=N; i++) {
			dist[i] = Long.MAX_VALUE;
		}
		dist[1] = 0; 	// 시작도시는 1번

		edges = new ArrayList<>();
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edges.add(new int[] {a, b, c});
		}

		// 모든 간선 업데이트 N-1 번 수행
		int s, e, w;
		for(int i=1; i<=N-1; i++) {
			for(int j=0; j<M; j++) {
				int[] edge = edges.get(j);
				s = edge[0];	// 출발
				e = edge[1];	// 도착
				w = edge[2];	// 가중치

				if(dist[s] == Long.MAX_VALUE) {
					continue;	//해당 경로 에서는 출발할 수 없음
				}
				dist[e] = Math.min(dist[e], dist[s]+w);
			}
		}

		// 한번 더 수행할 때 변화가 있으면 음수 사이클이 있는 것
		boolean isflag = false;
		for(int j=0; j<M; j++) {
			int[] edge = edges.get(j);
			s = edge[0];	// 출발
			e = edge[1];	// 도착
			w = edge[2];	// 가중치

			if(dist[s] == Long.MAX_VALUE) {
				continue;	//해당 경로 에서는 출발할 수 없음
			}

			if (dist[e] > dist[s] + w) {
				isflag = true;
				break;
			}
		}

		if (isflag) {
			bw.write("-1");
			bw.flush();
			return;
		}

		// 사이클이 없다면 다 출력
		for(int i=2; i<=N; i++) {
			if(dist[i] == Long.MAX_VALUE) {
				bw.write("-1\n");
			} else {
				bw.write(dist[i]+"\n");
			}
		}
		bw.flush();
	}
}
