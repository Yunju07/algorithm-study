import java.util.*;
import java.io.*;

public class BOJ20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[2 * N]; // 벨트 내구도
        boolean[] robots = new boolean[N]; // 로봇 위치

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int step = 0;
        int count = 0;

        while (true) {
            step++;

            // 1. 벨트와 로봇 회전
            rotate(A);
            rotateRobots(robots);
            robots[N - 1] = false; // 내리는 위치 로봇 제거

            // 2. 로봇 이동
            for (int i = N - 2; i >= 0; i--) {
                if (robots[i] && !robots[i + 1] && A[i + 1] > 0) {
                    robots[i] = false;
                    robots[i + 1] = true;
                    A[i + 1]--;
                    if (A[i + 1] == 0) {
                        count++;
                    }
                }
            }

            // 2-1. 내리는 위치 로봇 제거
            robots[N - 1] = false;

            // 3. 올리는 위치 로봇 추가
            if (A[0] > 0) {
                robots[0] = true;
                A[0]--;
                if (A[0] == 0) {
                    count++;
                }
            }

            // 4. 내구도 0인 칸 개수 확인
            if (count >= K) break;
        }

        System.out.println(step);
    }

    private static void rotate(int[] A) {
        int last = A[A.length - 1];
        for (int i = A.length - 1; i > 0; i--) {
            A[i] = A[i - 1];
        }
        A[0] = last;
    }

    private static void rotateRobots(boolean[] robots) {
        for (int i = robots.length - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }
        robots[0] = false;
    }
}
