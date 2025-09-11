import java.util.*;
import java.io.*;

// 음악 프로그램
// 위상정렬
class BOJ2623 {
    static int[] ind;
    static List<Integer>[] edges;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ind = new int[N+1];
        edges = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            ind[i] = 0;
            edges[i] = new ArrayList();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int count = Integer.parseInt(st.nextToken());
            int[] singers = new int[count];
            for(int j=0; j<count; j++) {
                singers[j] = Integer.parseInt(st.nextToken());
            }

            for(int a=0; a<count; a++) {
                for(int b=a+1; b<count; b++) {
                    ind[singers[b]]++;
                    edges[singers[a]].add(singers[b]);
                }
            }
        }

        Deque<Integer> queue = new LinkedList<Integer>();
        for(int i=1; i<=N; i++) {
            if(ind[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> results = new ArrayList<Integer>();
        while(!queue.isEmpty()){
            int node = queue.poll();
            results.add(node);

            for(int neigh: edges[node]) {
                ind[neigh] -= 1;

                if(ind[neigh] == 0) {
                    queue.add(neigh);
                }
            }
        }

        if(results.size() == N) {
            for(int r: results) {
                bw.write(r+"\n");
            }
        } else {
            bw.write("0");
        }

        bw.flush();
    }
}