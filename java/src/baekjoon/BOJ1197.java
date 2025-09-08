import java.util.*;
import java.lang.*;
import java.io.*;

// 최소 스패닝 트리
class BOJ1197 {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<int[]> edges = new ArrayList<int[]>();
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges.add(new int[]{A, B, C});
        }
        Collections.sort(edges, Comparator.comparing(n -> n[2]));

        // 부모 배열 초기화
        parent = new int[V+1];
        for(int v=1; v<=V; v++) {
            parent[v] = v;
        }

        // 최소 스패닝 트리
        long result = 0;
        int edge_count = 0;
        for(int[] edge: edges) {
            if(edge_count == V-1) {
                break;
            }

            int a = edge[0];
            int b = edge[1];
            int c = edge[2];

            if(find(a) != find(b)) {
                result += c;
                union(a, b);
            }
        }

        bw.write(result +"\n");
        bw.flush();

    }

    public static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if(A != B) {
            parent[B] = A;
        }
    }

    public static int find(int a) {
        if(parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }
}
