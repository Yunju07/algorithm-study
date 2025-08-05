import java.io.*;
import java.util.*;

// 도로 네트워크
// LCA

// 오늘의 교훈
// 다른 메서드에서 디버깅 하려고 BufferedWriter 생성했으면 제출할때는 지우자..
// 특히 재귀적으로 호출되는 메서드면, 메모리 초과를 만든다..
public class BOJ3176 {
    static int N, P, depth[], parent[][], s[][], l[][];
    static List<int[]>[] edges;
    static Integer max;
    static Integer min;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        P = 0;
        while ((1<<P) <= N) P++;

        parent = new int[P][N+1];
        s = new int[P][N+1];
        l = new int[P][N+1];
        edges = new ArrayList[N+1];
        depth = new int[N+1];

        for(int i=1; i<=N; i++) {
            depth[i] = 0;
            edges[i] = new ArrayList();
        }

        // 간선 정보
        for(int i=1; i<=N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());	// 도로의 길이

            edges[a].add(new int[] {b, c});
            edges[b].add(new int[] {a, c});
        }

        // 모든 노드 루트 가능 -> 그냥 1을 루트로 두기
        depth[1] = 1;
        dfs(1);

        for(int k=1; k<P; k++) {
            for(int i=1; i<=N; i++) {
                parent[k][i] = parent[k-1][parent[k-1][i]];
                s[k][i] = Math.min(s[k-1][i], s[k-1][parent[k-1][i]]);
                l[k][i] = Math.max(l[k-1][i], l[k-1][parent[k-1][i]]);

            }
        }

        int M = Integer.parseInt(br.readLine());
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            lca(a, b);

            bw.write(min + " " + max + "\n");
        }
        bw.flush();
    }

    static void dfs(int cur) {
        for(int[] edge: edges[cur]) {
            int next = edge[0];
            int len = edge[1];

            if(depth[next] == 0) {
                depth[next] = depth[cur] + 1;

                parent[0][next] = cur;
                s[0][next] = len;
                l[0][next] = len;

                dfs(next);
            }
        }

    }

    static int lca(int a, int b) {
        // a를 더 얕은 노드로 만들어주기
        if(depth[a] > depth[b]) {
            int temp = b;
            b = a;
            a = temp;
        }

        for(int k=P; k>=0; k--) {
            if(depth[b] - depth[a] >= (1<<k)) {
                min = Math.min(min, s[k][b]);
                max = Math.max(max, l[k][b]);

                b = parent[k][b];
            }
        }
        if(a == b) {
            return a;
        }

        for(int k=P-1; k>=0; k--) {
            if(parent[k][a] != parent[k][b]) {
                min = Math.min(min, s[k][a]);
                min = Math.min(min, s[k][b]);

                max = Math.max(max, l[k][a]);
                max = Math.max(max, l[k][b]);

                b = parent[k][b];
                a = parent[k][a];
            }
        }
        min = Math.min(min, s[0][a]);
        min = Math.min(min, s[0][b]);

        max = Math.max(max, l[0][a]);
        max = Math.max(max, l[0][b]);

        return parent[0][a];
    }
}
