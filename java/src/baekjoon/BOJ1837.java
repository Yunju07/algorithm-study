import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class BOJ1837 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        BigInteger P = new BigInteger(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 에라토스테네스의 체로 모든 소수 구하기
        int max = K+1;
        boolean[] is_prime = new boolean[max];
        for(int i = 0; i < max; i++) {
            is_prime[i] = true;
        }

        is_prime[0] = false;
        is_prime[1] = false;
        is_prime[2] = true;
        for(int i = 2; i < Math.sqrt(max) + 1 ; i++) {
            if(!is_prime[i]) {
                continue;
            }

            // 소수라면 그 배수들을 모두 걸러내기
            if(is_prime[i]) {
                for(int j = i * i; j < max; j += i) {
                    is_prime[j] = false;
                }
            }
        }

        // 나쁜 암호인지 검증
        for(int num = 2; num < K; num++){
            if(is_prime[num] && P.mod(BigInteger.valueOf(num)).equals(BigInteger.ZERO)) {
                bw.write("BAD "+num);
                bw.flush();
                return;
            }
        }

        bw.write("GOOD");
        bw.flush();
    }
}