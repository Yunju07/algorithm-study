import java.util.*;
import java.io.*;

// 여왕 개미
public class queenant {
    static List<int[]> town;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;   

        int Q = Integer.parseInt(br.readLine());
        
        // 1. 마을 건설
        town = new ArrayList<int[]>();
        town.add(new int[]{0, 1});

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        int N = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++) {
            town.add(new int[]{Integer.parseInt(st.nextToken()), 1});
            
        }
        
        // 명령어 수행
        for(int i=1; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 200) {
                town.add(new int[]{b, 1});

            } else if(a == 300) {
                int[] del = town.get(b);
                del[1] = -1;

            } else if(a == 400) {

                int t = findTime(b);
                bw.write(t+"\n");
            }
        }
        bw.flush();
    }

    // r마리의 개미가 정찰에 걸리는 최소 시간
    public static int findTime(int r) {
        int low = 0;
        int high = town.get(town.size()-1)[0];

        int mid;
        while(low < high) {
           mid = (low + high) / 2;

           if(isPossible(r, mid)) {
                high = mid;
           } else {
                low = mid+1;
           }
        }
        return low;
    }

    // r마리의 개미로 t초 안에 정찰이 가능한지 판단
    public static boolean isPossible(int r, int t) {
        int total_r = 0;
        int start = 0;
        for(int[] arr: town) {
            int e = arr[0];
            //여왕 개미의 집은 패스
            if(e == 0) {
                continue;
            }

            // 철거된 집도 패스
            if(arr[1] == -1) {
                continue;
            }

            // 첫집인 경우
            if(start == 0) {
                start = e;
                total_r++;
                continue;
            } 
            
            if(start+t < e) {
                start = e;
                total_r++;
            }

            if(total_r > r) {
                return false;
            }
        }
        return true;
    }
}
