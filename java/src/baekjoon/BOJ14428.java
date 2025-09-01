import java.util.*;
import java.io.*;
import java.math.*;

// 수열과 쿼리 16
class BOJ14428 {
    static long[][] idxtree = new long[400_001][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 세그먼트 트리 초기화
        for(int i=0; i<400_001; i++) {
            idxtree[i] = new long[]{-1, Long.MAX_VALUE};
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int a = Integer.parseInt(st.nextToken());
            update(i, a, 1, 1, N);
        }

        // 쿼리
        int M = Integer.parseInt(br.readLine());
        for(int m=1; m<=M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a == 1) {
                int i = Integer.parseInt(st.nextToken());
                long v = Long.parseLong(st.nextToken());

                update(i, v, 1, 1, N);

            } else if(a == 2) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());

                long[] result = min(i, j, 1, 1, N);
                bw.write(result[0]+"\n");
            }
        }

        bw.flush();
    }

    public static long[] min(int idx1, int idx2, int node, int nodeL, int nodeR) {
        if(idx2 < nodeL || nodeR < idx1) {
            return new long[]{-1, Long.MAX_VALUE};
        }

        // 완전히 속함
        if(idx1 <= nodeL && nodeR <= idx2) {
            return idxtree[node];
        }

        int mid = (nodeL + nodeR) / 2;
        long[] left = min(idx1, idx2, 2*node, nodeL, mid);
        long[] right = min(idx1, idx2, 2*node+1, mid+1, nodeR);

        if(left[1] <= right[1]) {
            return left;

        } else {
            return right;
        }
    }

    public static long[] update(int idx, long val, int node, int nodeL, int nodeR) {
        // 범위를 벗어남
        if (idx < nodeL || nodeR < idx) {
            return idxtree[node];
        }

        // 리프노드
        if (nodeL == nodeR) {
            idxtree[node][0] = idx;
            idxtree[node][1] = val;

            return idxtree[node];
        }

        int mid = (nodeL + nodeR) / 2;
        long[] left = update(idx, val, 2*node, nodeL, mid);
        long[] right = update(idx, val, 2*node+1, mid+1, nodeR);

        // 최소로 업데이트
        if(left[1] < right[1]) {
            idxtree[node][0] = left[0];
            idxtree[node][1] = left[1];

        } else if (left[1] > right[1]) {
            idxtree[node][0] = right[0];
            idxtree[node][1] = right[1];
        } else {
            // 값이 같으면 작은 인덱스로 업데이트
            if(left[0] < right[0]){
                idxtree[node][0] = left[0];
                idxtree[node][1] = left[1];
            } else {
                idxtree[node][0] = right[0];
                idxtree[node][1] = right[1];
            }
        }

        return idxtree[node];
    }
}