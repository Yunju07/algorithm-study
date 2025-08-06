import java.io.*;
import java.util.*;

// LCA 2
public class BOJ11438 {
    static int N, depth[], parent[][], K=17;
    static ArrayList<Integer>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        depth = new int[N+1];
        parent = new int[K][N+1];
        edges = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            edges[i] = new ArrayList<>();
            depth[i] = 0;
        }

        // 트리의 간선의 갯수는 늘 N-1개임 (N: 노드 갯수)
        for(int i=1; i<=N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edges[s].add(e);
            edges[e].add(s);
        }
        depth[1] = 1;
        dfs(1);

        for (int k=1; k<K; k++) {
            for (int i=1; i<=N; i++) {
                parent[k][i] = parent[k-1][parent[k-1][i]];
            }
        }

        // 최소 공통 조상
        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(lca(a, b)+"\n");
        }
        bw.flush();

    }

    // dfs(root)를 통해 모든 노드들의 깊이와 자기의 바로 위 조상 노드까지 초기화
    static void dfs(int cur) {
        for(int edge: edges[cur]) {
            if(depth[edge] == 0) {
                depth[edge] = depth[cur] + 1;
                parent[0][edge] = cur;  // 2^0 번째 (1번째) 조상 노드의 번호
                dfs(edge);
            }
        }
    }

    static int lca(int a, int b) {
        if(depth[a] > depth[b]) {
            int temp = b;
            b = a;
            a = temp;
        }

        for (int k=K-1; k>=0; k--) {
            // b가 a보다 depth가 더 높아지지 않는 최대한 올라갈 수 있는 만큼을 구한다
            // 1<<k: 1을 왼쪽으로 k비트만큼 밀어낸다, 2^k 와 같음
            if(depth[b] - depth[a] >= (1<<k)) {
                b = parent[k][b];
            }
        }

        if(a == b) {
            return a;   // b를 올렷더니 a에 도착
        }

        for (int k=K-1; k>=0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        return parent[0][a];
    }
}