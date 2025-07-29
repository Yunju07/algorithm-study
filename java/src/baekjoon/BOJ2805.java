import java.io.*;
import java.util.*;

// 나무 자르기
// 목재 절단기
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 최소 가져가야하는 나무

        Integer[] trees = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees, Collections.reverseOrder());

        long H = trees[0];	// 제일 키 큰 나무

        // 이분 탐색
        long max = H;
        long min = 0;
        long res = 0;
        while(true) {
            long mid = (min + max) / 2;

            long result = calculate(trees, M, mid);

            if (result < M) {
                // 나무 부족 -> max 감소
                max = mid - 1;


            } else if (result > M) {
                // 나무 많음 -> min 증가 + 우선 res 업데이트
                res = Math.max(res, mid);
                min = mid + 1;

            } else {
                // 딱맞아 그러면 ?
                res = Math.max(res, mid);
                break;
            }

            // 종료 조건
            if (min > max) {
                break;
            }
        }
        bw.write(res+"");
        bw.flush();

    }

    public static long calculate(Integer[] trees, long M, long height) {
        long result = 0;

        for (Integer t: trees ) {
            if(t > height) {
                result += t - height;
            }
        }
        return result;
    }

}
