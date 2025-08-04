import java.io.*;
import java.util.*;
import java.math.*;

//최대 공약수
public class BOJ2824 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        //long[] nlist = new long[N];
        List<BigInteger> nList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<N; i++) {
            //nlist[i] = Long.parseLong(st.nextToken());
            nList.add(new BigInteger(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        //long[] mlist = new long[M];
        List<BigInteger> mList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<M; i++) {
            //mlist[i] = Long.parseLong(st.nextToken());
            mList.add(new BigInteger(st.nextToken()));
        }

        int max = Math.max(N, M);
        BigInteger A = nList.get(0);
        BigInteger B = mList.get(0);
        BigInteger result = BigInteger.ONE;

        for (int i = 1; i < max; i++) {
            if (i >= N) {
                BigInteger g = gcd(A, mList.get(i));
                B = B.multiply((mList.get(i).divide(g)));
                A = A.divide(g);
                result = result.multiply(g);

            } else if (i >= M) {
                BigInteger g = gcd(B, nList.get(i));

                A = A.multiply((nList.get(i).divide(g)));
                B = B.divide(g);
                result = result.multiply(g);

            } else {
                BigInteger g = gcd(mList.get(i), nList.get(i));

                A = A.multiply((nList.get(i).divide(g)));
                B = B.multiply((mList.get(i).divide(g)));
                result = result.multiply(g);
            }
        }

        result = result.multiply(gcd(A, B));

        String s = String.valueOf(result);
        while (s.length() > 9) {
            s = s.substring(s.length()-9, s.length());
        }
        bw.write(s);
        bw.flush();
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (b != BigInteger.ZERO) {
            BigInteger temp = a.mod(b);
            a = b;
            b = temp;
        }
        return a;
    }
}
