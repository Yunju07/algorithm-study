import java.util.*;
import java.lang.*;
import java.io.*;

// BOJ1722 - 순열의 순서
class BOJ1722 {
    static int count, k;
    static int[] arr, output, input;
    static boolean isFind = false;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = i+1;
        }

        output = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());

        if(num == 1) {
            count = 0;
            k = Integer.parseInt(st.nextToken());
            findPermutation(0, N, N);

        } else if(num == 2) {
            count = 0;
            input = new int[N];
            for(int i=0; i<N; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }
            countPermutation(0, N, N);

        }
    }

    // k번째 수열이 뭔지 찾기
    public static void findPermutation(int depth, int n, int r) {
        if(depth == r) {
            count++;
            if(count == k) {
                for(int o: output) {
                    System.out.print(o+" ");
                }
            }
            return;
        }

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                findPermutation(depth+1, n, r);
                visited[i] = false;
            }
        }

    }

    // 수열이 몇 번째인지 찾기
    public static void countPermutation(int depth, int n, int r) {
        if(depth == r) {
            count++;

            // output이랑 input이랑 같은 지 비교
            for(int a=0; a<n; a++) {
                if(output[a] != input[a]) {
                    return;
                }
            }

            // 찾았으면
            System.out.println(count);
            isFind = true;

            return;
        }

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                countPermutation(depth+1, n, r);

                if(isFind) {
                    return;
                }

                visited[i] = false;
            }
        }
    }
}