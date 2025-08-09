import java.util.*;
import java.lang.*;
import java.io.*;

class BOJ11505 {
    static long[] idxTree = new long[4000001];
    static int DIV = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long v;
        for(int i=1; i<=N; i++) {
            v = Long.parseLong(br.readLine());
            update(i, v, 1, 1, N);
        }

        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // 수 변경
            if(a == 1) {
                update(b, c, 1, 1, N);
            } else if (a == 2){
                // 구간의 곱
                bw.write(multify(b, (int)c, 1, 1, N)+ "\n");
            }

        }
        bw.flush();

    }

    public static long multify(int idx1, int idx2, int node, int nodeL, int nodeR) {
        // 구간 밖이면
        if(idx2 < nodeL || nodeR < idx1) {
            return 1;
        }

        // 해당 구간은 완전이 포함될 때
        if(idx1 <= nodeL && nodeR <= idx2) {
            return idxTree[node];
        }

        int mid = (nodeL + nodeR) / 2;

        return multify(idx1, idx2, node*2, nodeL, mid) *
                multify(idx1, idx2, node*2 + 1, mid+1, nodeR) % DIV;
    }

    public static long update(int idx, long value, int node, int nodeL, int nodeR) {
        // 구간 밖이면
        if(idx < nodeL || nodeR < idx) {
            return idxTree[node];
        }

        // 리프 노드
        if(nodeL == nodeR) {
            return idxTree[node] = value;
        }

        int mid = (nodeL + nodeR) / 2;

        return idxTree[node] = update(idx, value, node*2, nodeL, mid)*
                update(idx, value, node*2 + 1, mid+1, nodeR) % DIV;
    }

}