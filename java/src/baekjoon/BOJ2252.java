import java.io.*;
import java.util.*;

// 줄 세우기
public class BOJ2252 {
    static int[] ind;   // indegree: 특정 노드로 들어가는 간선의 수
    static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());	// 학생 수
        int M = Integer.parseInt(st.nextToken());	// 비교 횟수

        // 그래프 초기화
        ind = new int[N+1];
        edges = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            edges[i] = new ArrayList<Integer>();
            ind[i] = 0;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());	// A가 B 앞에 서야한다.

            edges[A].add(B);
            ind[B]+=1;
        }

        // 위상 정렬
        // 큐 초기화 - 큐에 정렬이 필요하면 우선순위 큐를 사용하자
        Deque<Integer> Q = new LinkedList<Integer>();
        for(int i=1; i<=N; i++) {
            if(ind[i] == 0) {
                Q.add(i);
            }
        }

        List<Integer> results = new ArrayList();
        while(!Q.isEmpty()) {
            int node = Q.poll();
            results.add(node);
            for(int neighbor: edges[node]) {
                ind[neighbor] -= 1;
                if(ind[neighbor] == 0) {
                    Q.add(neighbor);
                }
            }
        }

        // 위상정렬의 결과 출력
        for(int result: results) {
            bw.write(result+" ");
        }
        bw.flush();
    }
}
