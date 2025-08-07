import java.io.*;
import java.util.*;

// 공장
public class BOJ7578 {

	static long[] idxtree = new long[2000001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] idx = new int[1000001];
		long result = 0;

		// A
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			idx[Integer.parseInt(st.nextToken())] = i;
		}

		// B
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int b = idx[Integer.parseInt(st.nextToken())];

			// 인덱스 트리에서 구간합 먼저 구하기
			result += sum(b+1, N, 1, 1, N);

			// 인덱스 트리 업데이트
			update(b, 1, 1, 1, N);

		}

		bw.write(result+"");
		bw.flush();

	}

	public static long sum(int idx1, int idx2, int node, int nodeL, int nodeR) {
		if(idx2 < nodeL || nodeR < idx1) {
			return 0;
		}

		if (idx1 <= nodeL && nodeR <= idx2) {
			return idxtree[node];
		}

		int mid = (nodeL + nodeR) / 2;

		return sum(idx1, idx2, node*2, nodeL, mid) +
				sum(idx1, idx2, node*2+1, mid+1, nodeR);
	}

	public static long update(int idx, long data, int node, int nodeL, int nodeR) {
		if(nodeL > idx || idx > nodeR) {
			return idxtree[node];
		}

		if(nodeL == nodeR) {
			return idxtree[node] = data;
		}

		// 구간 쪼개기
		int mid = (nodeL + nodeR) / 2;

		idxtree[node] = update(idx, data, 2*node, nodeL, mid) +
				update(idx, data, 2*node+1, mid+1, nodeR);

		return idxtree[node];
	}
}
