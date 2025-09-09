import java.util.*;
import java.io.*;
import java.math.*;

// 행성 터널
// N개의 행성과 N-1개의 터널 건설, 최소 비용 -> MST
class BOJ2887 {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<int[]> planet = new ArrayList<int[]>();
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            planet.add(new int[]{x, y, z, i});
        }

        //List<int[]> edges = new ArrayList<int[]>();
        PriorityQueue<int[]> edges = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        // x축 정렬을 기준으로 간선
        Collections.sort(planet, Comparator.comparing(n -> n[0]));
        for(int i=0; i<N-1; i++) {
            int c = Math.min(Math.abs(planet.get(i)[0]-planet.get(i+1)[0]), Math.abs(planet.get(i)[1]-planet.get(i+1)[1]));
            c = Math.min(c, Math.abs(planet.get(i)[2]-planet.get(i+1)[2]));

            edges.add(new int[]{planet.get(i)[3], planet.get(i+1)[3], c});
        }

        // y축 정렬을 기준으로 간선
        Collections.sort(planet, Comparator.comparing(n -> n[1]));
        for(int i=0; i<N-1; i++) {
            int c = Math.min(Math.abs(planet.get(i)[0]-planet.get(i+1)[0]), Math.abs(planet.get(i)[1]-planet.get(i+1)[1]));
            c = Math.min(c, Math.abs(planet.get(i)[2]-planet.get(i+1)[2]));

            edges.add(new int[]{planet.get(i)[3], planet.get(i+1)[3], c});
        }

        // z축 정렬을 기준으로 간선
        Collections.sort(planet, Comparator.comparing(n -> n[2]));
        for(int i=0; i<N-1; i++) {
            int c = Math.min(Math.abs(planet.get(i)[0]-planet.get(i+1)[0]), Math.abs(planet.get(i)[1]-planet.get(i+1)[1]));
            c = Math.min(c, Math.abs(planet.get(i)[2]-planet.get(i+1)[2]));
            edges.add(new int[]{planet.get(i)[3], planet.get(i+1)[3], c});
        }

        // 부모 배열 초기화
        parent = new int[N+1];
        for(int v=1; v<=N; v++) {
            parent[v] = v;
        }

        // 최소 스패닝 트리
        long result = 0;
        int edge_count = 0;
        int[] edge = new int[3];
        while(!edges.isEmpty()) {
            edge = edges.poll();

            if(edge_count == N-1) {
                break;
            }

            int a = edge[0];
            int b = edge[1];
            int c = edge[2];

            if(find(a) != find(b)) {
                result += c;
                union(a, b);
                edge_count += 1;
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
