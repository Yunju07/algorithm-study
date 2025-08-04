import java.io.*;
import java.util.*;

// 네트워크 연결
public class BOJ1922 {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());	// 컴퓨터 갯수
        int M = Integer.parseInt(br.readLine());

        List<int[]> edges = new ArrayList<int[]>();
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());   // a 컴퓨터
            int b = Integer.parseInt(st.nextToken());   // b 컴퓨터
            int c = Integer.parseInt(st.nextToken());   // 연결하는데 드는 cost

            edges.add(new int[] {a, b, c});
        }
        Collections.sort(edges, Comparator.comparing(n -> n[2]));

        // 가중치 낮은 것부터 연결 시도 (유니온-파인드 적용)
        parent = new int[N+1];
        for(int i =1; i<=N; i++) {
            parent[i] = i;
        }

        int result = 0;
        int edge_count = 0;
        for(int[] edge: edges) {
            // 선택된 경우 (a-b가 아직 연결이 안되어 있는 경우)
            if(find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                result += edge[2];
                edge_count += 1;
            }

            // 현재까지 연결된 간선 수를 이용해서 신장 트리가 완성되었는지 검증
            if(edge_count == N-1) {
                break;
            }
        }

        bw.write(result+"");
        bw.flush();
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
