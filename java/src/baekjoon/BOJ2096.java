import java.io.*;
import java.util.*;

// 내려가기
public class BOJ2096 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] max = new int[3];
        int[] min = new int[3];
        // temp
        int[] tmax = new int[3];
        int[] tmin = new int[3];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i =0; i<3; i++) {
            int n = Integer.parseInt(st.nextToken());
            max[i] = n;
            min[i] = n;
            tmax[i] = Integer.MIN_VALUE;
            tmin[i] = Integer.MAX_VALUE;
        }


        for(int i=1; i <N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j <3; j++) {
                int n = Integer.parseInt(st.nextToken());

                // 대각선 왼쪽에서 오는 경우
                if(j > 0) {
                    tmax[j] = (tmax[j] > n + max[j-1]) ? tmax[j] : (n + max[j-1]);
                    tmin[j] = (tmin[j] < n + min[j-1]) ? tmin[j] : (n + min[j-1]);

                }
                // 대각선 오른쪽에서 오는 경우
                if(j < 2) {
                    tmax[j] = (tmax[j] > n + max[j+1]) ? tmax[j] : (n + max[j+1]);
                    tmin[j] = (tmin[j] < n + min[j+1]) ? tmin[j] : (n + min[j+1]);

                }
                // 위에서 오는 경우
                tmax[j] = (tmax[j] > n + max[j]) ? tmax[j] : (n + max[j]);
                tmin[j] = (tmin[j] < n + min[j]) ? tmin[j] : (n + min[j]);
            }

            // 업데이트
            for(int j=0; j <3; j++) {
                min[j] = tmin[j];
                max[j] = tmax[j];
                tmax[j] = Integer.MIN_VALUE;
                tmin[j] = Integer.MAX_VALUE;
            }
        }
        Arrays.sort(max);
        Arrays.sort(min);

        bw.write(max[2] + " " + min[0]);
        bw.flush();

    }

}
