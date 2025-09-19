import java.util.*;
import java.lang.*;
import java.io.*;

// BOJ1759 - 암호 만들기
class BOJ1759 {
    static String[] output, arr;
    static List<String> MO = Arrays.asList("a", "e", "i", "o", "u");
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 암호 구성
        // 서로 다른 L개의 알파벳 소문자
        // - 최소 한 개의 모음(a, e, i, o, u)
        // - 최소 두 개의 자음

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        output = new String[L];
        arr = new String[C];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            arr[i] = st.nextToken();
        }
        Arrays.sort(arr);

        combination(0, C, L, 0, 0, 0);
    }

    // 조합
    public static void combination(int depth, int n, int r, int start, int ja, int mo) {
        if(depth == r) {
            // 가능성 있는 암호인지 판단
            if(mo >=1 && ja >= 2) {
                //출력
                for(String o: output) {
                    System.out.print(o);
                }
                System.out.print("\n");
            }
            return;
        }

        for(int i=start; i<n; i++) {
            output[depth] = arr[i];

            // 모음인 경우
            if(MO.contains(arr[i])) {
                combination(depth+1, n, r, i+1, ja, mo+1);
            } else {
                combination(depth+1, n, r, i+1, ja+1, mo);
            }
        }
    }
}