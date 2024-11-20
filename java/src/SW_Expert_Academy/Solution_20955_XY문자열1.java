package SW_Expert_Academy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 두가지 연산
 *  1. S의 제일 뒤에 ‘X’를 붙인다.
 *  2. S를 뒤집은 다음 제일 뒤에 ‘Y’를 붙인다.
 * **/

class Solution_20955_XY문자열1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case < T + 1; test_case++) {
            String S = br.readLine();
            String E = br.readLine();

            // 연산 횟수
            int N = E.length() - S.length();
            StringBuilder sb = new StringBuilder(E);

            while (sb.length() > S.length()) {
                int len = sb.length();

                // 1번 연산
                if (sb.charAt(len-1) == 'X') {
                    sb.deleteCharAt(len-1);
                }
                // 2번 연산
                else {
                    sb.deleteCharAt(len-1);
                    sb.reverse();
                }
            }
            E = sb.toString();

            if (S.equals(E)) {
                System.out.println(String.format("#%d YES", test_case));
            } else {
                System.out.println(String.format("#%d NO", test_case));
            }
        }
    }
}
