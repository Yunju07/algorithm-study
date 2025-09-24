import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

// 톱니바퀴
class BOJ14891 {
    static int[][] arrs;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 4개의 바퀴 - 8개의 톱니
        // 각 톱니는 N(0)/S(1) 극
        arrs = new int[4][8];
        for(int i=0; i<4; i++) {
            String[] s = br.readLine().split("");
            for(int j=0; j<8; j++) {
                arrs[i][j] = Integer.parseInt(s[j]);
            }
        }
        
        // K번 회전 - 시계(1) / 반시계(-1)
        int K = Integer.parseInt(br.readLine());
        List<int[]> rotates = new ArrayList<int[]>();
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
        
            rotates.add(new int[]{a, b});
        }

        // 회전 과정
        // 1. 각 접합부 부분에 같은 극인지 아닌지 체크한다.
        // 2. 회전하려는 톱니를 회전한다.
        // 3. 회전한 톱니로 부터 오른쪽 방향 왼쪽 방향으로 각각 나아가며 톱니들을 회전시킨다.
            // 접합부 & 접합톱니회전여부 확인
            // 접합톱니회전 방향과 반대로 회전
        boolean[] isSame = new boolean[3];
        int[] direction = new int[4];
        for(int[] r: rotates) {
            isSame = checkSame();
            int idx = r[0]-1;
            direction[idx] = r[1];
            rotate(idx, direction[idx]);

            // 왼쪽 방향으로 나아가며 회전
            for(int i = idx-1; i>=0; i--) {
                // 자기 자신의 오른쪽 톱니와 비교
                if(isSame[i]) {
                    direction[i] = 0;
                    break; 
                }
                
                if(direction[i+1] == 0) {
                    direction[i] = 0;
                    break;
                }

                // 접합부 톱니와 반대로 회전
                direction[i] = direction[i+1]*-1;
                rotate(i, direction[i]);
            } 


            // 오른쪽 방향으로
            for(int i = idx+1; i<4; i++) {
                // 자기 자신의 왼쪽 톱니와 비교
                if(isSame[i-1]) {
                    direction[i] = 0;  
                    break; 
                }
                
                if(direction[i-1] == 0) {
                    direction[i] = 0;
                    break;
                }

                // 접합부 톱니와 반대로 회전
                direction[i] = direction[i-1]*-1;
                rotate(i, direction[i]);
            }    

            // // 단계마다
            // bw.write("round: "+"\n");
            // for(int i=0; i<4; i++) {
            //     for(int j=0; j<8; j++) {
            //         bw.write(arrs[i][j]+" ");
            //     }
            //     bw.write("\n");
            // }
        }

        // 점수 환산
        int result = 0;
        result += (1 * arrs[0][0]);
        result += (2 * arrs[1][0]);
        result += (4 * arrs[2][0]);
        result += (8 * arrs[3][0]);
        

        bw.write(result+"\n");
        bw.flush();
    }

    // idx(0~3)번째 톱니를 d 방향으로 회전
    public static void rotate(int idx, int d) {
        // d - 시계(1) / 반시계(-1)

        int[] result = new int[8];
        if(d == 1) {
            result[0] = arrs[idx][7];
            for(int i=1; i<8; i++) {
                result[i] = arrs[idx][i-1];
            }    
            
        } else if(d == -1) {
            for(int i=0; i<7; i++) {
                result[i] = arrs[idx][i+1];
            }
            result[7] = arrs[idx][0];
        }
        
        arrs[idx] = result;
    }

    // 톱니들 접합부 체크 - 다를때 회전
    public static boolean[] checkSame() {
        boolean[] same = new boolean[3];

        // 1-2톱니
        if(arrs[0][2] == arrs[1][6]) {
            same[0] = true;
        }
        if(arrs[1][2] == arrs[2][6]) {
            same[1] = true;
        }
        if(arrs[2][2] == arrs[3][6]) {
            same[2] = true;
        }
        
        return same;
    }
}
