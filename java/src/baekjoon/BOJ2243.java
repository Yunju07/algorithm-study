import java.io.*;
import java.util.*;

// 사탕상자
// 세그먼트 트리
// 이분 탐색
public class BOJ2243 {
    static long[] idxtree = new long[4000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                // b 순위의 사탕을 하나 빼주세요 -> 누적합 + 세그먼트 + 이분탐색 ?
                int low = 1, high = 1000000;

                while(true) {
                    int mid = (high + low) / 2;	// mid 맛의 캔디가 순위를 만족하는지 이분탐색
                    long count = sum(1, mid, 1, 1, 1000000);

                    if (count < b) {
                        low = mid + 1;
                    } else if (count >= b) {		// 사탕 넉넉
                        high = mid - 1;
                    }

                    // 종료 조건
                    if (high < low) {
                        break;
                    }
                }
                int key = low;
                long count = sum(key, key, 1, 1, 1000000) - 1;
                update(key, count, 1, 1, 1000000);

                bw.write(key+"\n");
                bw.flush();

            } else if (a == 2) {
                // b = 맛, c = 갯수
                long c = Long.parseLong(st.nextToken());

                long count = sum(b, b, 1, 1, 1000000) + c;
                update(b, count , 1, 1, 1000000);

            }
        }
        bw.flush();

    }

    public static long update(int idx, long val, int node, int nodeL, int nodeR) {
        // 구간 밖
        if (idx < nodeL || idx > nodeR) {
            return idxtree[node];
        }

        // 리프 노드
        if (nodeL == nodeR) {
            return idxtree[node] = val;
        }

        // 구간 쪼개고 내려가기
        int mid = (nodeL + nodeR) / 2;

        return idxtree[node] = update(idx, val, node*2, nodeL, mid) +
                update(idx, val, node*2+1, mid+1, nodeR);
    }

    public static long sum(int idx1, int idx2, int node, int nodeL, int nodeR) {
        // 구간 밖
        if (idx2 < nodeL || idx1 > nodeR) {
            return 0;
        }

        // 구간이 완전 속하거나
        if (idx1 <= nodeL && nodeR <= idx2) {
            return idxtree[node];
        }
        // 내려가거나
        int mid = (nodeL + nodeR) / 2;

        return sum(idx1, idx2, node*2, nodeL, mid) +
                sum(idx1, idx2, node*2+1, mid+1, nodeR);

    }
}