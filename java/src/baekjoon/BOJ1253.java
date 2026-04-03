import java.util.*;
import java.lang.*;
import java.io.*;

// BOJ1253 - 좋다
class BOJ1253 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(arr);

        int count = 0;
        for(int i=0; i<N; i++) {
            // num이 좋은 숫자인지 판단
            if(isGood(i)) count++;

            // System.out.println("#:"+arr[i]);
            // System.out.println(isGood(i));
        }
        System.out.println(count);

    }

    public static boolean isGood(int idx) {
        int num = arr[idx];

        int left = 0;
        int right = N-1;
        while(left<right) {
            if(left == idx){
                left++;
                continue;
            }
            if(right == idx){
                right--;
                continue;
            }

            if(arr[left] + arr[right] == num) {
                return true;
            }
            else if(arr[left] + arr[right] < num) left++;
            else if(arr[left] + arr[right] > num) right--;
        }

        return false;
    }
}