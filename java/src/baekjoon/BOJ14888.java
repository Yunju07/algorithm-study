import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

// 연산자 끼워넣기
class BOJ14888 {
    static int N;
    static int[] operator, nums, output;
    static List<int[]> ops;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // N 수열, 순서 바꾸기 불가능
        // N-1 연산자 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)
        // 식의 계산은 연산자 우선순위 무시하고 앞에서부터 진행
        // 나눗셈은 몫만 취하기
        // (음수/양수) -> 양수로 바꾼뒤 몫을 취하고 몫을 음수로 바꾸기

        // 수열 입력
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 입력
        st = new StringTokenizer(br.readLine());
        operator = new int[4];
        for(int i=0; i<4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 순서 중요 & 가진 것만 중복없이 -> 순열
        output = new int[N-1];
        ops = new ArrayList<int[]>();   // 연산자 순열들
        
        permutation(0, N-1);

        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;
        for(int[] op: ops){
            int result = nums[0];

            // 연산 수행 - 덧셈(0), 뺄셈(1), 곱셈(2), 나눗셈(3)
            for(int i=0; i<N-1; i++) {
                if(op[i] == 0){
                    result += nums[i+1];
                } else if(op[i] == 1) {
                    result -= nums[i+1];
                } else if(op[i] == 2) {
                    result *= nums[i+1];
                } else if(op[i] == 3) {
                    if(result < 0) {
                        result *= -1;
                        result /= nums[i+1];
                        result *= -1;
                        
                    } else {
                        result /= nums[i+1];
                    }
                }
            }

            mini = Math.min(mini, result);
            maxi = Math.max(maxi, result);
        }
        
        bw.write(maxi+"\n");
        bw.write(mini+"\n");
        bw.flush();
    }

    // op 배열에 남아있는 연산자들로 length길이의 수열 만들기
    public static void permutation(int depth, int length) {
        // 완성된 경우
        if(depth == length) {
            int[] arr = output.clone();
            ops.add(arr);
        }

        for(int op=0; op<4; op++){
            if(operator[op] == 0) {
                continue;
            }

            // 연산자 추가
            output[depth] = op;
            operator[op] -= 1;
            permutation(depth+1, length);
            operator[op] += 1;
        }
    }
}
