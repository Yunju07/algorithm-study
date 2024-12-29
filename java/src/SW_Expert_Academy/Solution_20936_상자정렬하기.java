package SW_Expert_Academy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.Arrays.sort;

class Solution_20936_상자정렬하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case < T + 1; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] boxes = new int[N+1];

            // 구해야 되는 출력값
            int K = 0;
            List<Integer> move = new ArrayList<>();

            int val = 0;
            int idx = 0;

            // 상자 초기화
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++){
                boxes[i] = Integer.parseInt(st.nextToken());

                // 값이 다른 경우
                if (i+1 != boxes[i]) {
                    K++;
                    if (val<boxes[i]) {
                        val = boxes[i];
                        idx = i;
                    }
                }
            }
            int empty_idx = N;
            boxes[N] = 0;

            if (K > 0) {
                K++;
            }

            for (int i = 0; i < K; i ++) {
                move.add(idx+1);
                boxes[empty_idx] = val;
                boxes[idx] = 0;

                empty_idx = idx;
                val = idx + 1;
                for(int j = 0; j < N+1; j ++) {
                    if (boxes[j] == val) {
                        idx = j;
                    }
                }
            }



            // 출력
            System.out.println(K);
            String result = "";
            for (Integer i : move) {
                result += Integer.toString(i) + " ";
            }
            System.out.println(result);
        }
    }
}
