package SW_Expert_Academy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 자바 제곱근 구하기
* Math.pow(x, 2) -> 제곱
* Math.pow(x, 3) -> 세제곱
* */
class Solution_16910_원안의점 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case < T+1; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int count = 0;

            // 이중 for 문
            for (int x = -N; x <= N; x++) {
                for (int y = -N; y <= N; y ++) {
                    if (x*x + y*y <= N*N) {
                        count++;
                    }
                }
            }
            String result = String.format("#%d %d", test_case, count);
            System.out.println(result);
        }
        br.close();
    }
}
