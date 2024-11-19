package SW_Expert_Academy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solutions_20934_방울마술 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case < T + 1; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String S = st.nextToken();
            int K = Integer.parseInt(st.nextToken());

            // 초기 상자별 방울이 있을 확률
            double left = 0;
            double center = 0;
            double right = 0;

            if (S.equals("o..")) {
                left = 1;
            }
            if (S.equals(".o.")) {
                center = 1;
            }
            if (S.equals("..o")) {
                right = 1;
            }

            // K 횟수 만큼 반복
            for (int i = 0; i < K; i++) {
                if (center == 1) {
                    center = 0;
                    left = 0.5;
                    right = 0.5;
                }
                else {
                    center = 1;
                    left = 0;
                    right = 0;
                }

            }

            int max_posiible = 0;
            if (left < center) {
                max_posiible = 1;
            }
            if (left < right) {
                max_posiible = 2;
            }

            String result = String.format("#%d %d", test_case, max_posiible);
            System.out.println(result);
        }
    }
}
