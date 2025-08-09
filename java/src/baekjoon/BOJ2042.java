import java.io.*;
import java.util.*;

// 구간 합 구하기
public class BOJ2042 {

    static long idxtree[] = new long[4000001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());	// 수의 개수
        int M = Integer.parseInt(st.nextToken()); 	// 수 변경 횟수
        int K = Integer.parseInt(st.nextToken());	// 구간합 구하는 횟수

        for(int i = 1; i <= N; i++) {
            long data = Long.parseLong(br.readLine());
            update(i, data, 1, 1, N);
        }

        for(int i = 1; i <= M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(b, c, 1, 1, N);	// 수변경

            } else if (a == 2) {
                // (b ~ c)구간합 구하기
                long sum = sum(b, (int)c, 1, 1, N);
                bw.write(sum+"\n");
            }
        }

        bw.flush();
    }

    public static long sum(int idx1, int idx2, int node, int nodeL, int nodeR) {
        // idx1 ~ idx2 구간의 합을 구한다.
        // 구간 밖
        if (idx2 < nodeL || idx1 > nodeR) {
            return 0;
        }

        // 완전히 속한 경우
        if (idx1 <= nodeL && nodeR <=idx2) {
            return idxtree[node];
        }

        // 완전히 속하지는 않아서 또 쪼개야함
        int mid = (nodeL + nodeR) / 2;

        return sum(idx1, idx2, node*2, nodeL, mid)
                + sum(idx1, idx2, node*2+1, mid+1, nodeR);
    }

    public static long update(int idx, long data, int node, int nodeL, int nodeR) {
        // 구간 밖
        if (idx < nodeL || idx > nodeR) {
            return idxtree[node];
        }

        // 리프 노드
        if (nodeL == nodeR) {
            idxtree[node] = data;
            return idxtree[node];
        }

        int mid = (nodeL + nodeR) / 2;

        idxtree[node] = update(idx, data, node*2, nodeL, mid)
                + update(idx, data, node*2+1, mid+1, nodeR);

        return idxtree[node];
    }
}