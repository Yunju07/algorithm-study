import java.io.*;
import java.util.*;

// 게임 개발
public class BOJ1516 {
    static int[] ind;   // 나 이전에 먼저 지어져야하는 건물의 갯수
    static int[] T;		// 건물 짓는데 걸리는 시간
    static List<Integer>[] edges;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 그래프 초기화
        ind = new int[N+1];
        T = new int[N+1];
        edges = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            ind[i] = 0 ;
            T[i] = 0;
            edges[i] = new ArrayList();
        }

        for(int i=1; i<=N; i++) {
            // i 건물의 정보 (i: 1 ~ N)
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());

            while(true) {
                int a = Integer.parseInt(st.nextToken());
                if(a == -1) {
                    break;
                } else {
                    edges[a].add(i);
                    ind[i] += 1;
                }
            }
        }

        // 위상 정렬
        PriorityQueue<int[]> Q = new PriorityQueue<int[]>(Comparator.comparing(n -> n[0]));
        for(int i=1; i<=N; i++) {
            if(ind[i] == 0) {
                int[] row = new int[] {T[i], i};
                Q.offer(row);
            }
        }

        while(!Q.isEmpty()) {
            int[] node = Q.poll();
            int nodeT = node[0];
            int nodeidx = node[1];
            for(int neigh : edges[nodeidx]) {
                ind[neigh] -= 1;
                if(ind[neigh] == 0) {
                    T[neigh] += T[nodeidx];
                    int[] row = new int[] {T[neigh], neigh};   // 항상 마지막에 나온 노드가 최대 시간임을 보장
                    Q.add(row);
                }
            }
        }

        // 출력
        for(int i=1; i<=N; i++) {
            bw.write(T[i]+"\n");
        }
        bw.flush();
    }
}
