import java.io.*;
import java.util.*;

// 수열과 쿼리 21
public class Main {
    static final int MAX = 400_001;
    static long idxtree[] = new long[MAX];  // 인덱스 트리
    static long lazy[] = new long[MAX];  // 레이지 전파

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            long a = Long.parseLong(st.nextToken());
            update(i, a, 1, 1, N);
        }

        int M = Integer.parseInt(br.readLine());
        for(int m=1; m<=M; m++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 1) {  // 구간에 값 더하기
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                Long k = Long.parseLong(st.nextToken());
                rangeAdd(i, j, k, 1, 1, N);

            } else if(cmd == 2) {   // 출력하기
                int x = Integer.parseInt(st.nextToken());
                long result = sum(x, x, 1, 1, N);

                bw.write(result + "\n");
            }
        }
        bw.flush();
    }

    public static void propagate(int node, int nodeL, int nodeR) {
        if(lazy[node] != 0) {
            idxtree[node] = (nodeL - nodeR + 1) * lazy[node];

            if(nodeL != nodeR) {
                lazy[2*node] += lazy[node];
                lazy[2*node+1] += lazy[node];
            }

            lazy[node] = 0;
        }
        return
    }

    public static void rangeAdd(int idx1, int idx2, long val, int node, int nodeL, int nodeR) {
        propagate(node, nodeL, nodeR);

        if(idx2 < nodeL || nodeR < idx2) {
            return
        }

        if(idx1 <= nodeL && nodeR <= idx2) {
            lazy[node] += val;
            propagate(node, nodeL, nodeR);
            return
        }

        int mid = (nodeL + nodeR) /2;

        rangeAdd(idx1, idx2, val, 2*node, nodeL, mid);
        rangeAdd(idx1, idx2, val, 2*node+1, mid+1, nodeR);
        idxtree[node] = idxtree[node*2] + idxtree[node*2+1];

        return;
    }

    public static void propagate(int node, int nodeL, int nodeR) {
        if(lazy[node] != 0) {
            idxtree[node] += (nodeR - nodeL + 1) * lazy[node];

            if(nodeL != nodeR) {
                lazy[node*2] += lazy[node];
                lazy[node*2+1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    public static void rangeAdd(int idx1, int idx2, long val, int node, int nodeL, int nodeR) {
        propagate(node, nodeL, nodeR);

        // 구간 밖
        if (idx2 < nodeL || idx1 > nodeR) {
            return;
        }

        // 완전히 속한 경우
        if (idx1 <= nodeL && nodeR <=idx2) {
            lazy[node] += val;
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