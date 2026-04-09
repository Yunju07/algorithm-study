import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class BOJ1766 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] degree = new int[N+1];

        List<Integer>[] edges = new List[N+1];
        for(int i=1; i<N+1; i++) {
            degree[i] = 0;
            edges[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            degree[B]++;
            edges[A].add(B);
        }

        // [node]
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        for(int i=1; i<=N; i++) {
            if(degree[i]==0) {
                PQ.add(i);
            }
        }

        while(!PQ.isEmpty()) {
            int node = PQ.poll();
            bw.write(node+" ");

            for(int e: edges[node]) {
                degree[e]--;
                if(degree[e]==0) {
                    PQ.add(e);
                }
            }
        }

        bw.flush();
    }
}