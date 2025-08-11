import java.io.*;
import java.util.*;

// 구간 합 구하기 2
public class Main {
    static final int MAX = 4_000_001;
    static long idxtree[] = new long[MAX];  // 인덱스 트리
    static long lazy[] = new long[MAX];  // 레이지 전파

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
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                long d = Long.parseLong(st.nextToken());
                rangeAdd(b, c, d, 1, 1, N);

            } else if (a == 2) {
                // (b ~ c)구간합 구하기
                long sum = sum(b, c, 1, 1, N);
                bw.write(sum+"\n");
            }
        }

        bw.flush();
    }

    // lazy 값을 현재 노드에 반영하고, 자식에게 전파
    public static void propagate(int node, int start, int end) {
        if(lazy[node] != 0) {
            // 현재 구간의 합에 (구간 길이 * 지연 값)만큼 반영
            idxtree[node] += (end - start + 1) * lazy[node];

            // 리프가 아니면 자식들에게 지연값을 넘김
            if (start != end) {
                lazy[node*2] += lazy[node];
                lazy[node*2+1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    // idx1 ~ idx2 구간에 특정값 더하기
    static void rangeAdd(int idx1, int idx2, long val, int node, int nodeL, int nodeR) {
        propagate(node, nodeL, nodeR);

        // 구간 밖
        if (idx2 < nodeL || idx1 > nodeR) {
            return;
        }

        // 완전히 속한 경우
        if (idx1 <= nodeL && nodeR <=idx2) {
            lazy[node] += val;
            propagate(node, nodeL, nodeR);
            return;
        }

        int mid = (nodeL + nodeR) / 2;

        rangeAdd(idx1, idx2, val, 2*node, nodeL, mid);
        rangeAdd(idx1, idx2, val, 2*node+1, mid+1, nodeR);
        idxtree[node] = idxtree[2*node] + idxtree[2*node+1];

        return;
    }

    public static long sum(int idx1, int idx2, int node, int nodeL, int nodeR) {
        propagate(node, nodeL, nodeR);

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
            idxtree[node] += data;
            return idxtree[node];
        }

        int mid = (nodeL + nodeR) / 2;

        idxtree[node] = update(idx, data, node*2, nodeL, mid)
                + update(idx, data, node*2+1, mid+1, nodeR);

        return idxtree[node];
    }
}