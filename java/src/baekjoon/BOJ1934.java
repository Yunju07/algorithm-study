import java.util.*;
import java.lang.*;
import java.io.*;

// 정수론 - 최소공배수
class BOJ1934 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int GCD = gcd(A, B);
            int LCM = A * B / GCD;  // ⭐️ 최소 공배수 구하는 공식

            bw.write(LCM + "\n");
        }
        bw.flush();
    }

    public static int gcd(int A, int B) {
        if(A % B == 0) {
            return B;
        }

        return gcd(B, (A % B));
    }
}