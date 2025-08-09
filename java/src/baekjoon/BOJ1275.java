import java.util.*;
import java.lang.*;
import java.io.*;

// 커피숍2
class BOJ1275 {
    static long[] idxTree = new long[400001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        long v;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            v = Long.parseLong(st.nextToken());
            update(i, v, 1, 1, N);
        }

        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (x > y) {
                int temp = y;
                y = x;
                x = temp;
            }

            long sum = sum(x, y, 1, 1, N);
            update(a, b, 1, 1, N);

            bw.write(sum+"\n");

        }
        bw.flush();

    }

    public static long sum(int idx1, int idx2, int node, int nodeL, int nodeR) {
        // 구간 밖이면
        if(idx2 < nodeL || nodeR < idx1) {
            return 0;
        }

        // 해당 구간은 완전이 포함될 때
        if(idx1 <= nodeL && nodeR <= idx2) {
            return idxTree[node];
        }

        int mid = (nodeL + nodeR) / 2;

        return sum(idx1, idx2, node*2, nodeL, mid)+
                sum(idx1, idx2, node*2 + 1, mid+1, nodeR);
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

        return idxTree[node] = update(idx, value, node*2, nodeL, mid)+
                update(idx, value, node*2 + 1, mid+1, nodeR);
    }
}