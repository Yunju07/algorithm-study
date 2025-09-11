import java.util.*;
import java.lang.*;
import java.io.*;

// 전력난
class BOJ6497 {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());  // 집의 수
            int n = Integer.parseInt(st.nextToken());  // 길의 수

            if(m == 0 && n==0){
                break;  // 입력 종료
            }

            PriorityQueue<int[]> edges = new PriorityQueue<int[]>(Comparator.comparing(a -> a[2]));
            long result = 0;
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());  // 거리

                edges.add(new int[]{x, y, z});
                result += z;
            }

            parent = new int[m+1];
            for(int i=1; i<=m; i++) {
                parent[i] = i;
            }

            int edge_count = 0;
            int[] edge = new int[3];
            while(!edges.isEmpty()) {
                if(edge_count == m-1) {
                    break;
                }

                edge = edges.poll();
                int x = edge[0];
                int y = edge[1];
                int z = edge[2];

                if(find(x) != find(y)) {
                    result -= z;
                    union(x, y);
                    edge_count++;
                }
            }

            bw.write(result + "\n");
            bw.flush();
        }

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