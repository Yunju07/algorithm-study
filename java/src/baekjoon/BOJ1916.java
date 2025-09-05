import java.util.*;
import java.lang.*;
import java.io.*;

// 최소비용 구하기
class Main {
    static int[] minRoute;
    static List<int[]>[] edges;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());  // 도시의 개수
        int M = Integer.parseInt(br.readLine());  // 버스의 수

        minRoute = new int[N+1];
        edges = new List[N+1];
        for(int i=1; i<=N; i++) {
            minRoute[i] = -1;
            edges[i] = new ArrayList();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[u].add(new int[]{v, w});
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        // 다익스트라
        minRoute[s] = 0;
        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>(Comparator.comparing(n -> n[1]));
        PQ.add(new int[]{s, 0});

        while(!PQ.isEmpty()) {
            int[] node = PQ.poll();

            // ⭐️다익스트라는 특정 노드가 큐에서 나오는 순간,
            // 이미 해당 노드까지의 최적의 경로가 계산된 것을 가정한다.
            // 따라서 현재 노드가 끝노드이면, 반복문 종료 (출력할 최단경로가 하나인 경우)
            if(node[0] == e){
                break;
            }

            for(int[] edge: edges[node[0]]) {
                int v = edge[0];
                int w = edge[1];
                int newCost = minRoute[node[0]] + w;

                if(minRoute[v] == -1 || newCost < minRoute[v]) {
                    minRoute[v] = newCost;
                    PQ.add(new int[]{v, newCost});
                }
            }
        }

        bw.write(minRoute[e]+" ");
        bw.flush();
    }
}