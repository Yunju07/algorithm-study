package SW_Expert_Academy;
/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solutions_2001_파리퇴치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 이중 배열 생성
            int[][] array = new int[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    array[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 가능한 갯수
            List<Integer> totalSum = new ArrayList<>();

            int num = N-M+1;
            for(int i = 0; i < num; i++){
                for(int j = 0; j < num; j++) {
                    totalSum.add(sumMatrix(array, i, j, M));
                }
            }
            int max = totalSum.stream()
                    .max(Integer::compare)
                    .orElse(Integer.MIN_VALUE);

            String result = String.format("#%d %d",test_case, max);
            System.out.println(result);
        }
    }

    public static int sumMatrix(int[][] array, int i, int j, int M) {
        int sum = 0;
        for (int a = i; a < i + M; a++) {
            for (int b = j; b < j + M; b++) {
                sum += array[a][b];
            }
        }
        return sum;
    }
}
