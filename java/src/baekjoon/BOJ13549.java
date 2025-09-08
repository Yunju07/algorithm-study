import java.util.*;
import java.lang.*;
import java.io.*;

// 숨바꼭질 3
class BOJ13549 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] minRoute = new int[100001];
        for(int i=0; i<100001; i++){
            minRoute[i] = -1;
        }
        minRoute[N] = 0;

        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>(Comparator.comparing(n -> n[1]));
        PQ.add(new int[]{N, 0});

        // 다익스트라
        while(!PQ.isEmpty()){
            int[] node = PQ.poll();
            int n = node[0];
            int w = node[1];

            // 목적지에 도착한 경우 바로 break - 시간 단축
            if(n == K){
                break;
            }

            // 1. v: n-1 w: 1
            if(0 <= n-1) {
                int v = n-1;
                int newCost = w + 1;
                if(minRoute[v] == -1 || minRoute[v] > newCost) {
                    minRoute[v] = newCost;
                    PQ.add(new int[]{v, newCost});
                }
            }

            // 2. v: n+1 w: 1
            if(n+1 <= 100_000) {
                int v = n+1;
                int newCost = w + 1;
                if(minRoute[v] == -1 || minRoute[v] > newCost) {
                    minRoute[v] = newCost;
                    PQ.add(new int[]{v, newCost});
                }
            }

            // 3. v: 2*n w: 0
            if(2*n <= 100_000) {
                int v = 2*n;
                int newCost = w;
                if(minRoute[v] == -1 || minRoute[v] > newCost) {
                    minRoute[v] = newCost;
                    PQ.add(new int[]{v, newCost});
                }
            }
        }

        bw.write(minRoute[K]+"\n");
        bw.flush();
    }
}