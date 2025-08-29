import java.io.*;
import java.util.*;

// K번째 최단경로 찾기 - 다익스트라
public class BOJ1854 {
    static List<int[]>[] edges;
    static PriorityQueue<Integer>[] routes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        routes = new PriorityQueue[n+1];
        edges = new List[n+1];
        for(int i=1; i<=n; i++) {
            edges[i] = new ArrayList();
            routes[i] = new PriorityQueue<Integer>(Collections.reverseOrder());
        }

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a].add(new int[] {b, c});
        }

        // 시작도시는 1
        routes[1].add(0);
        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>(Comparator.comparing(a -> a[1]));

        PQ.add(new int[] {1, 0});

        while(!PQ.isEmpty()) {
            int[] node = PQ.poll();
            int cv = node[0];
            int cost = node[1];

            for(int[] neigh: edges[node[0]]) {
                int v = neigh[0];
                int w = neigh[1];

                int newCost = w + cost;

                if (routes[v].size() < k) {
                    routes[v].add(newCost);
                    PQ.add(new int[]{v, newCost});
                } else if (routes[v].peek() > newCost) {
                    routes[v].poll();
                    routes[v].add(newCost);
                    PQ.add(new int[]{v, newCost});
                }

            }
        }

        for(int i=1; i<=n; i++) {
            if(routes[i].size() != k) {
                bw.write("-1\n");
            } else {
                bw.write(routes[i].peek()+"\n");
            }
        }
        bw.flush();
    }
}
