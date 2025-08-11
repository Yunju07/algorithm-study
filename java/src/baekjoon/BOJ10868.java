import java.util.*;
import java.lang.*;
import java.io.*;

// 최솟값
class BOJ10868 {
    static long[] minTree = new long[400001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long v;
        for(int i=1; i<=N; i++) {
            v = Long.parseLong(br.readLine());
            updateMin(i, v, 1, 1, N);
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long min = getMin(a, b, 1, 1, N);

            bw.write(min+"\n");

        }
        bw.flush();

    }

    public static long getMin(int idx1, int idx2, int node, int nodeL, int nodeR) {
        // 구간 밖이면
        if(idx2 < nodeL || nodeR < idx1) {
            return Long.MAX_VALUE;
        }

        // 해당 구간은 완전이 포함될 때
        if(idx1 <= nodeL && nodeR <= idx2) {
            return minTree[node];
        }

        int mid = (nodeL + nodeR) / 2;

        return Math.min(getMin(idx1, idx2, node*2, nodeL, mid),
                getMin(idx1, idx2, node*2 + 1, mid+1, nodeR));
    }


    public static long updateMin(int idx, long value, int node, int nodeL, int nodeR) {
        // 구간 밖이면
        if(idx < nodeL || nodeR < idx) {
            return minTree[node];
        }

        // 리프 노드
        if(nodeL == nodeR) {
            return minTree[node] = value;
        }

        int mid = (nodeL + nodeR) / 2;

        return minTree[node] = Math.min(updateMin(idx, value, node*2, nodeL, mid),
                updateMin(idx, value, node*2 + 1, mid+1, nodeR));
    }
}